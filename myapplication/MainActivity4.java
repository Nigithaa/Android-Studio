package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;



public class MainActivity4 extends AppCompatActivity {
    private Button openWebPageButton;
    private Button button2;
    private Button button3;
    private Button button4;
private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;

    //private AppBarConfiguration appBarConfiguration;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main4);

        ImageButton imageButton = findViewById(R.id.imageButton);

        // Set a click listener for the ImageButton
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a toast message when the ImageButton is clicked
                Toast.makeText(MainActivity4.this, "How can i help you?", Toast.LENGTH_SHORT).show();
            }
        });
        Button openWebPageButton = findViewById(R.id.openWebPageButton);
        openWebPageButton.setOnClickListener(view -> {
            // Open the website
            openWebsite("https://www.ugaoo.com/");
        });
        button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a toast message when the ImageButton is clicked
                Intent intent=new Intent(MainActivity4.this,MainActivity10.class);
                startActivity(intent);
            }
        });
        button3=findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a toast message when the ImageButton is clicked
                Intent intent=new Intent(MainActivity4.this,MainActivity7_menu.class);
                startActivity(intent);
            }
        });

        button4=findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a toast message when the ImageButton is clicked
                Intent intent=new Intent(MainActivity4.this,MainActivity11.class);
                startActivity(intent);
            }
        });
        button5=findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a toast message when the ImageButton is clicked
                Intent intent=new Intent(MainActivity4.this,Googlemaps.class);
                startActivity(intent);
            }
        });
        button6=findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a toast message when the ImageButton is clicked
                Intent intent=new Intent(MainActivity4.this,SQLite.class);
                startActivity(intent);
            }
        });
        button7=findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a toast message when the ImageButton is clicked
                Intent intent=new Intent(MainActivity4.this,SMSsender.class);
                startActivity(intent);
            }
        });
        button8=findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a toast message when the ImageButton is clicked
                Intent intent=new Intent(MainActivity4.this,Graphics.class);
                startActivity(intent);
            }
        });
        button9=findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a toast message when the ImageButton is clicked
                Intent intent=new Intent(MainActivity4.this,Animationmy.class);
                startActivity(intent);
            }
        });
        button10=findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a toast message when the ImageButton is clicked
                Intent intent=new Intent(MainActivity4.this,Login.class);
                startActivity(intent);
            }
        });


    }

    private void openWebsite(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    /*@Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main4);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }*/
}