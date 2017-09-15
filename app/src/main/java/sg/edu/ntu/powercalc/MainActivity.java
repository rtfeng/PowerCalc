package sg.edu.ntu.powercalc;

import android.Manifest;
import android.os.BatteryStats;
import android.os.UserHandle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.internal.os.BatterySipper;
import com.android.internal.os.BatteryStatsHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final boolean DEBUG = true;
    public static final String TAG = "Perf";

    BatteryStatsHelper helper = new BatteryStatsHelper(this, false, true);
    BatteryStats stats = helper.getStats();

    int StatsType = BatteryStats.STATS_SINCE_CHARGED;
    public List<BatterySipper> sippers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int BatteryPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.BATTERY_STATS);
        int AccessWIFIPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE);
        int AccessFineLocationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int AccessCoarseLocationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BATTERY_STATS, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        if (DEBUG) Log.d(TAG, "Permission checking! " + BatteryPermission + " " + AccessWIFIPermission + " " + AccessFineLocationPermission + " " + AccessCoarseLocationPermission);

        initView();
        helper.create((Bundle) null);
    }

    private Button startButton, stopButton;

    private void initView() {
        startButton = (Button) findViewById(R.id.startButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int BatteryPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.BATTERY_STATS);
        int AccessWIFIPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE);
        int AccessFineLocationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int AccessCoarseLocationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (DEBUG) Log.d(TAG, "Permission checking! " + BatteryPermission + " " + AccessWIFIPermission + " " + AccessFineLocationPermission + " " + AccessCoarseLocationPermission);
        switch (v.getId()) {
            case R.id.startButton:
                Toast.makeText(MainActivity.this, "startButton", Toast.LENGTH_SHORT).show();
                sippers = helper.getUsageList();
                helper.refreshStats(StatsType, UserHandle.USER_ALL);
                if (sippers != null && sippers.size() > 0) {
                    for (int i = 0; i < sippers.size(); i++) {
//                        if (DEBUG) Log.d(TAG, String.valueOf(sippers.get(i).getUid())
//                                + ", "
//                                + String.valueOf(sippers.get(i).totalPowerMah)
//                                + ", "
//                                + String.valueOf(sippers.get(i).usagePowerMah)
//                                + ", "
//                                + String.valueOf(sippers.get(i).cpuPowerMah)
//                                + ", "
//                                + String.valueOf(sippers.get(i).cameraPowerMah)
//                                + ", "
//                                + String.valueOf(sippers.get(i).flashlightPowerMah)
//                                + ", "
//                                + String.valueOf(sippers.get(i).wakeLockPowerMah)
//                                + ", "
//                                + String.valueOf(sippers.get(i).gpsPowerMah)
//                                + ", "
//                                + String.valueOf(sippers.get(i).sensorPowerMah)
//                                + ", "
//                                + String.valueOf(sippers.get(i).wifiPowerMah)
//                                + ", "
//                                + String.valueOf(sippers.get(i).bluetoothPowerMah));
                        if (DEBUG) Log.d(TAG, String.valueOf(sippers.get(i).getUid())
                                + ", "
                                + String.valueOf(sippers.get(i).wifiRunningTimeMs)
                                + ", "
                                + String.valueOf(sippers.get(i).wifiRxBytes)
                                + ", "
                                + String.valueOf(sippers.get(i).wifiRxPackets)
                                + ", "
                                + String.valueOf(sippers.get(i).wifiTxBytes)
                                + ", "
                                + String.valueOf(sippers.get(i).wifiTxPackets));
                    }
                }
                break;
            case R.id.stopButton:
                Toast.makeText(MainActivity.this, "stopButton", Toast.LENGTH_SHORT).show();
                break;
            default:
                if (DEBUG) Log.d(TAG, "Wrong on button setting!");
                break;
        }
    }

}
