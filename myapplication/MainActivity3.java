package com.example.myapplication;
import java.util.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import android.widget.Toast;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityMain3Binding;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
   // private ActivityMain3Binding binding;
    private Button botonist;
    private Button button11;
    private Button button12;
    private Button button1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main3);
        String[] plantNames = {"Watering", "Progress Update", "Add Fertilizer", "Misting", "Check Humidity","Repotting"};

        // Create an ArrayAdapter to populate the ListView with plant names
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, plantNames);

        // Find the ListView in the layout
        ListView listView = findViewById(R.id.listView);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);

        // Set a click listener for items in the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item from the adapter
                String selectedPlant = (String) parent.getItemAtPosition(position);

                // Show a toast message with the selected plant name
                Toast.makeText(MainActivity3.this, "You have clicked " + selectedPlant + " successfully", Toast.LENGTH_SHORT).show();
            }
        });

        botonist=findViewById(R.id.botonist);
        botonist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(MainActivity3.this,MainActivity4.class);
                startActivity(intent);
            }

        });
        button11=findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a toast message when the ImageButton is clicked
                Intent intent=new Intent(MainActivity3.this,SettingsActivity.class);
                startActivity(intent);
            }
        });
        button1=findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a toast message when the ImageButton is clicked
                Intent intent=new Intent(MainActivity3.this,Mediaplayer.class);
                startActivity(intent);
            }
        });
        button12=findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a toast message when the ImageButton is clicked
                Intent intent=new Intent(MainActivity3.this,Hardware.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main3);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}