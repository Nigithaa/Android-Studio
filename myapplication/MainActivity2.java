package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMain2Binding binding;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button pc;
    private ProgressBar progressBar;
    private TextView batteryText;
    AirplaneMode airplaneMode=new AirplaneMode();
    BluetoothMode bluetoothMode=new BluetoothMode();
    BatteryLevel batteryLevel=new BatteryLevel();
    BootReceiver bootReceiver=new BootReceiver();
    protected void onStart() {
        super.onStart();
        IntentFilter airplaneFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(airplaneMode,airplaneFilter);
        IntentFilter bluetoothfilter = new IntentFilter(android.bluetooth.BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(bluetoothMode,bluetoothfilter);
        IntentFilter batteryfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryLevel, batteryfilter);
        IntentFilter bootFilter = new IntentFilter(Intent.ACTION_BOOT_COMPLETED);
        registerReceiver(bootReceiver,bootFilter);
    }
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(airplaneMode);
        unregisterReceiver(bluetoothMode);
        unregisterReceiver(batteryLevel);
        unregisterReceiver(bootReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main2);
        b1=findViewById(R.id.b1);
        progressBar=findViewById(R.id.progressbar);
        batteryText=findViewById(R.id.battery);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2.this, "Check for Humidity", Toast.LENGTH_SHORT).show();
            }
        });

        b2=findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2.this, "Water Regularly", Toast.LENGTH_SHORT).show();
            }
        });
        b3=findViewById(R.id.b3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2.this, "Use Organic Fertilizers", Toast.LENGTH_SHORT).show();
            }
        });
        b4=findViewById(R.id.b4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2.this, "Maintain Warm Temperature", Toast.LENGTH_SHORT).show();
            }
        });
        pc=findViewById(R.id.pc);
        pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(intent);
            }

        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main2);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
    private class BatteryLevel extends BroadcastReceiver {
        private final static String BATTERY_LEVEL = "level";
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BATTERY_LEVEL, 0);
            progressBar.setProgress(level);
            batteryText.setText("Battery Level is "+  level);
        }
    }
}

