package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.Date;

public class SMSsender extends AppCompatActivity {

    private Button sendSMSButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smssender);

        sendSMSButton = findViewById(R.id.button_send_sms);

        sendSMSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS();
            }
        });
    }

    // Method to send SMS to multiple recipients
    private void sendSMS() {
        String[] phoneNumbers = {"7397782720","9080511265"}; // Replace with the phone numbers you want to send SMS to
        String message = "Hey! This is from PLANT PULSE";
        sendSMS(phoneNumbers, message); // Call sendSMS method to send immediate SMS


        long timestamp = System.currentTimeMillis() + 120000; //  2 min from now
        scheduleSMS("7397782720", "Scheduled message", timestamp);

    }

    private void sendSMS(String[] phoneNumbers, String message) {

        StringBuilder recipients = new StringBuilder();
        for (String phoneNumber : phoneNumbers) {
            recipients.append(phoneNumber).append(";");
        }

        // Create intent with action type
        Intent defaultSmsAppIntent = new Intent(Intent.ACTION_SENDTO);
        defaultSmsAppIntent.setData(Uri.parse("sms:" + recipients.toString()));
        defaultSmsAppIntent.putExtra("sms_body", message);

        try {
            startActivity(defaultSmsAppIntent);
        } catch (ActivityNotFoundException e) {
            // Handle if no messaging app is available
            Toast.makeText(this, "Messaging app not found", Toast.LENGTH_SHORT).show();
        }
    }




    private void scheduleSMS(String phoneNumber, String message, long timestamp) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent intent = new Intent(this, SMSSenderReceiver.class);
        intent.setAction("SEND_SMS");
        intent.putExtra("phone_number", phoneNumber);
        intent.putExtra("sms_body", message);

        // Generate a unique requestCode using System.currentTimeMillis()
        int requestCode = (int) System.currentTimeMillis();

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timestamp, pendingIntent);


        Log.d("SMSsender", "SMS scheduled for: " + new Date(timestamp).toString());


        Toast.makeText(getApplicationContext(), "SMS scheduled for sending later", Toast.LENGTH_SHORT).show();
    }




}
