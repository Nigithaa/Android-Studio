package com.example.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class SQLite extends AppCompatActivity {

    private EditText editTextPlantName, editTextReview, editTextTips;
    private Button buttonAdd, buttonUpdate, buttonDelete, buttonShow;
    private DatabaseHelper dbHelper;
    private ListView listViewPlants;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> plantList;
    private long selectedPlantId = -1; // Default value to indicate no plant is selected

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        editTextPlantName = findViewById(R.id.editTextPlantName);
        editTextReview = findViewById(R.id.editTextReview);
        editTextTips = findViewById(R.id.editTextTips);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonShow = findViewById(R.id.buttonShow);
        listViewPlants = findViewById(R.id.listViewPlants);

        dbHelper = new DatabaseHelper(this);
        plantList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, plantList);
        listViewPlants.setAdapter(adapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlant();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePlant();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePlant();
            }
        });

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPlants();
            }
        });

        listViewPlants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // Extract plant ID from the selected item text
                long plantId = extractPlantId(selectedItemText);
                // Update the ID of the selected plant
                selectedPlantId = plantId;
            }
        });
    }
    private long extractPlantId(String plantDetails) {
        String[] detailsArray = plantDetails.split("\n");
        String idString = detailsArray[0].split(": ")[1];
        return Long.parseLong(idString);
    }

    private void addPlant() {
        String plantName = editTextPlantName.getText().toString();
        String review = editTextReview.getText().toString();
        String tips = editTextTips.getText().toString();
        boolean inserted = dbHelper.insertPlant(plantName, review, tips);
        if (inserted) {
            Toast.makeText(this, "Plant details saved", Toast.LENGTH_SHORT).show();
            editTextPlantName.setText("");
            editTextReview.setText("");
            editTextTips.setText("");
        } else {
            Toast.makeText(this, "Failed to save plant details", Toast.LENGTH_SHORT).show();
        }
    }

    private void updatePlant() {
        if (selectedPlantId != -1) { // Check if a plant is selected
            String plantName = editTextPlantName.getText().toString();
            String review = editTextReview.getText().toString();
            String tips = editTextTips.getText().toString();
            int rowsAffected = dbHelper.updatePlant(selectedPlantId, plantName, review, tips);
            if (rowsAffected > 0) {
                Toast.makeText(this, "Plant updated successfully", Toast.LENGTH_SHORT).show();
                showPlants();
            } else {
                Toast.makeText(this, "Failed to update plant", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No plant selected for updating", Toast.LENGTH_SHORT).show();
        }
    }



    private void deletePlant() {
        if (selectedPlantId != -1) { // Check if a plant is selected
            dbHelper.deletePlant(selectedPlantId); // Call deletePlant with the selected plant ID
            Toast.makeText(this, "Plant deleted", Toast.LENGTH_SHORT).show();
            showPlants(); // Refresh the plant list after deletion
        } else {
            Toast.makeText(this, "No plant selected for deletion", Toast.LENGTH_SHORT).show();
        }
    }


    private void showPlants() {
        plantList.clear();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_PLANTS, null);
        if (cursor.moveToFirst()) {
            int plantNameIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_PLANT_NAME);
            int reviewIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_REVIEW);
            int tipsIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TIPS);
            int idIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
            do {
                String plantName = cursor.getString(plantNameIndex);
                String review = cursor.getString(reviewIndex);
                String tips = cursor.getString(tipsIndex);
                long id = cursor.getLong(idIndex);
                if (plantNameIndex != -1 && reviewIndex != -1 && tipsIndex != -1 && idIndex != -1) {
                    String plantDetails = "Plant ID: " + id + "\nPlant Name: " + plantName + "\nReview: " + review + "\nTips: " + tips;
                    plantList.add(plantDetails);
                } else {
                    // Handle the case where column index is -1 (column not found)
                    // Log an error or perform appropriate action
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }

    private long getIdFromSelectedPlant() {
        int selectedItemPosition = listViewPlants.getCheckedItemPosition();
        if (selectedItemPosition != ListView.INVALID_POSITION) {
            String plantDetails = adapter.getItem(selectedItemPosition);
            // Extracting the ID from the plantDetails string
            String[] detailsArray = plantDetails.split("\n");
            String idString = detailsArray[0].split(": ")[1];
            return Long.parseLong(idString);
        }
        return -1; // Return -1 if no plant is selected
    }


}
