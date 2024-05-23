package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity7_menu extends AppCompatActivity {


    private ImageView image_view_plant;
    private EditText edittextcaption;
    private Button btnUpload;
    private TextView tvSelectedDateTime;
    private ProgressBar progress_bar;

    private Calendar selectedDateTime;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main_activity7_menu);
        setTitle("Plant Social Network");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        image_view_plant = findViewById(R.id.image_view_plant);
        edittextcaption= findViewById(R.id.edittextcaption);
        btnUpload = findViewById(R.id.btnUpload);
        tvSelectedDateTime = findViewById(R.id.tv_selected_datetime);
        progress_bar = findViewById(R.id.progress_bar);

        // Initialize selectedDateTime with current date and time
        selectedDateTime = Calendar.getInstance();
        // Set click listener for the upload button
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPlant();
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show DatePicker
                showDatePicker();
            }
        });
    }
    private void showDatePicker() {
        // Get current year, month, and day
        int year = selectedDateTime.get(Calendar.YEAR);
        int month = selectedDateTime.get(Calendar.MONTH);
        int dayOfMonth = selectedDateTime.get(Calendar.DAY_OF_MONTH);

        // Create DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Update selectedDateTime
                        selectedDateTime.set(year, monthOfYear, dayOfMonth);
                        showTimePicker();
                    }
                }, year, month, dayOfMonth);

        // Show DatePickerDialog
        datePickerDialog.show();
    }

    private void showTimePicker() {
        // Get current hour and minute
        int hour = selectedDateTime.get(Calendar.HOUR_OF_DAY);
        int minute = selectedDateTime.get(Calendar.MINUTE);

        // Create TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Update selectedDateTime
                        selectedDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        selectedDateTime.set(Calendar.MINUTE, minute);
                        updateSelectedDateTimeTextView();
                    }
                }, hour, minute, false);

        // Show TimePickerDialog
        timePickerDialog.show();
    }

    private void updateSelectedDateTimeTextView() {
        // Format selectedDateTime and set text to TextView
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy 'at' hh:mm a", Locale.getDefault());
        String formattedDateTime = sdf.format(selectedDateTime.getTime());
        tvSelectedDateTime.setText(formattedDateTime);
    }
    private void uploadPlant() {
        // Get caption text
        String caption = edittextcaption.getText().toString().trim();

        // Perform validation
        if (caption.isEmpty()) {
            Toast.makeText(this, "Please enter a caption", Toast.LENGTH_SHORT).show();
            return;
        }


        Toast.makeText(this, "Plant uploaded with caption: " + caption, Toast.LENGTH_SHORT).show();
        // Clear input fields after upload
        edittextcaption.setText("");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_profile:
                // Handle My Plant Profile
                openPlantProfile();
                return true;
            case R.id.menu_gallery:
                // Handle Plant Gallery
                openPlantGallery();
                return true;
            case R.id.menu_share:
                // Handle Share Plant
                sharePlant();
                return true;
            case R.id.menu_challenges:
                // Handle Plant Challenges
                openPlantChallenges();
                return true;
            case R.id.menu_achievements:
                // Handle My Achievements
                openAchievements();
                return true;
            case R.id.menu_leaderboard:
                // Handle Leaderboard
                openLeaderboard();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openPlantProfile() {
        // Navigate to Plant Profile activity
        Toast.makeText(this, "Opening My Plant Profile", Toast.LENGTH_SHORT).show();

    }

    private void openPlantGallery() {
        // Navigate to Plant Gallery activity
        Toast.makeText(this, "Opening Plant Gallery", Toast.LENGTH_SHORT).show();

    }

    private void sharePlant() {
        // Handle Share Plant action
        Toast.makeText(this, "Sharing Plant", Toast.LENGTH_SHORT).show();
        // Implement sharing functionality here
    }

    private void openPlantChallenges() {
        // Navigate to Plant Challenges activity
        Toast.makeText(this, "Opening Plant Challenges", Toast.LENGTH_SHORT).show();

    }

    private void openAchievements() {
        // Navigate to Achievements activity
        Toast.makeText(this, "Opening My Achievements", Toast.LENGTH_SHORT).show();
        // Intent intent = new Intent(this, AchievementsActivity.class);
        // startActivity(intent);
    }

    private void openLeaderboard() {
        // Navigate to Leaderboard activity
        Toast.makeText(this, "Opening Leaderboard", Toast.LENGTH_SHORT).show();
        // Intent intent = new Intent(this, LeaderboardActivity.class);
        // startActivity(intent);
    }



}