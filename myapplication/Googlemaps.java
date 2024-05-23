package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Googlemaps extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap googleMap;
    Button searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlemaps);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        EditText searchEditText = findViewById(R.id.searchEditText);

        searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchString = searchEditText.getText().toString().trim();
                if (!searchString.isEmpty()) {
                    Geocoder geocoder = new Geocoder(Googlemaps.this, Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocationName(searchString, 1);
                        if (addresses != null && addresses.size() > 0) {
                            Address address = addresses.get(0);
                            LatLng location = new LatLng(address.getLatitude(), address.getLongitude());
                            googleMap.clear(); // Clear previous markers
                            googleMap.addMarker(new MarkerOptions().position(location).title(searchString));
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
                        } else {
                            Toast.makeText(Googlemaps.this, "Location not found", Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(Googlemaps.this, "Please enter an address", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        // Initialize default location (e.g., your user's location)
        LatLng defaultLocation = new LatLng(9.8821, 78.0816); // Example coordinates (Mountain View, CA)

        // Add marker to default location
        googleMap.addMarker(new MarkerOptions().position(defaultLocation).title("Thiagarajar college of enginering"));

        // Move camera to default location
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 15));

        // Enable user interaction with the map
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        // Reverse geocoding to fetch complete address
        Geocoder geocoder = new Geocoder(Googlemaps.this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(defaultLocation.latitude, defaultLocation.longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                String completeAddress = address.getAddressLine(0); // Get the first line of the address
                Log.d("ReverseGeocode", "Complete Address: " + completeAddress); // Add this line for logging
                Toast.makeText(Googlemaps.this, "Complete Address: " + completeAddress, Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}