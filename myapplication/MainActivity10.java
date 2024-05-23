package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.example.myapplication.databinding.ActivityMain10Binding;

public class MainActivity10 extends AppCompatActivity {

    //private AppBarConfiguration appBarConfiguration;
   // private ActivityMain10Binding binding;
    ListView listView;
    String[] items = {"Tomato plant", "Potato plant","Money Plant","Pumpkin"};
    int[] imageIds = {R.drawable.tomato, R.drawable.potato,R.drawable.img_2,R.drawable.pumpkin};
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
        setTitle("View Plants");
        listView = findViewById(R.id.listView);
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("text", items[i]);
            item.put("image", imageIds[i]);
            data.add(item);
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] from = {"text", "image"};
        int[] to = {R.id.textView, R.id.imageView1};
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.list_item, from, to);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);


        // Handle item clicks
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            // Get the name of the clicked item
            String itemName = items[i];
            Toast.makeText(MainActivity10.this, itemName + " clicked", Toast.LENGTH_SHORT).show();
        });
        // Create an ArrayAdapter to populate the ListView with the dummy data
        /*ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);

        // Register the ListView for the context menu
        registerForContextMenu(listView);

        // Handle item clicks
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Display Toast message when item is clicked
                String itemName = items[i];
                Toast.makeText(MainActivity10.this, itemName + " clicked", Toast.LENGTH_SHORT).show();
            }
        });*/



        registerForContextMenu(listView);

        //binding = ActivityMain10Binding.inflate(getLayoutInflater());


        /*setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main10);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu10, menu);
        return true;
    }

    // Handle options menu item clicks
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        // Handle menu item clicks and display a Toast message
        switch (id) {
            case R.id.action_settings:
                Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_about:
                Toast.makeText(this, "About clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Inflate context menu
    @Override
    public void onCreateContextMenu(android.view.ContextMenu menu, View v, android.view.ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    // Handle context menu item clicks
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        // Handle context menu item clicks and display a Toast message
        switch (id) {
            case R.id.context_item_delete:
                Toast.makeText(this, "Delete clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.context_item_edit:
                Toast.makeText(this, "Edit clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

   /* @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main10);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }*/
}