package com.example.myapplication;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.example.myapplication.R;

public class Hardware extends AppCompatActivity {

    private ImageView imageView3;
    private ImageView imageView4; // This is imageView4
    private BluetoothAdapter bluetoothAdapter;
    Button b15;
    Button b1;
    Button button16;
    private Matrix matrix = new Matrix();
    private float scale = 1f;
    private ScaleGestureDetector scaleGestureDetector;
Button button1;
    private static final int pic_id = 123;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hardware);

        // Initialize buttons
        b1 = findViewById(R.id.button);
        b15 = findViewById(R.id.button15);
        button16 = findViewById(R.id.button16);

        // Set OnClickListener for button to check Bluetooth status
        b1.setOnClickListener(v -> checkBluetoothStatus());

        // Set OnClickListener for button15 to check Wi-Fi status
        b15.setOnClickListener(v -> checkWifiStatus());

        // Get Bluetooth adapter
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // Initialize ImageView
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4); // Initialize imageView4

        // Set OnClickListener for button16 to trigger camera capture intent
        button16.setOnClickListener(v -> {
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera_intent, pic_id);
        });
        button1=findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a toast message when the ImageButton is clicked
                Intent intent=new Intent(Hardware.this,Sensor.class);
                startActivity(intent);
            }
        });

        // Initialize ScaleGestureDetector
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        // Set onTouchListener for imageView4
        imageView4.setOnTouchListener((v, event) -> {
            scaleGestureDetector.onTouchEvent(event);
            return true;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pic_id) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                if (extras != null && extras.containsKey("data")) {
                    Bitmap photo = (Bitmap) extras.get("data");
                    if (photo != null) {
                        imageView3.setImageBitmap(photo);
                    } else {
                        Toast.makeText(this, "Failed to capture image", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Failed to get image data", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Camera capture canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void checkBluetoothStatus() {
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth not supported", Toast.LENGTH_SHORT).show();
            return;
        }

        if (bluetoothAdapter.isEnabled()) {
            b1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
            Toast.makeText(this, "Bluetooth is on", Toast.LENGTH_SHORT).show();
        } else {
            b1.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            Toast.makeText(this, "Bluetooth is off", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkWifiStatus() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager == null) {
            Toast.makeText(this, "Wi-Fi not supported", Toast.LENGTH_SHORT).show();
            return;
        }

        if (wifiManager.isWifiEnabled()) {
            b15.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
            Toast.makeText(this, "Wi-Fi is enabled", Toast.LENGTH_SHORT).show();
        } else {
            b15.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            Toast.makeText(this, "Wi-Fi is disabled", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        scaleGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = detector.getScaleFactor();
            scale *= scaleFactor;
            scale = Math.max(0.1f, Math.min(scale, 5.0f));
            matrix.setScale(scale, scale);
            imageView4.setImageMatrix(matrix);
            return true;
        }
    }
}

