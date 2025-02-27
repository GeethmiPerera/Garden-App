package com.s22010388.gardenguardianapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LocationScreen extends AppCompatActivity {
    private EditText location;
    private EditText destination;
    private Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_screen);

        location = findViewById(R.id.location);
        destination = findViewById(R.id.destination);
        searchBtn = findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(v -> {
            String startingPoint = location.getText().toString();
            String endPoint = destination.getText().toString();

            //Check if both fields are empty show toast message
            if (startingPoint.equals("") || endPoint.equals("")) {
                Toast.makeText(this, "Please enter starting location & destination", Toast.LENGTH_SHORT).show();
            } else {
                getPath(startingPoint, endPoint);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.location);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                startActivity(new Intent(getApplicationContext(), PlantCategoryScreen.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.location) {
                return true;
            }else if (item.getItemId() == R.id.temperature) {
                startActivity(new Intent(getApplicationContext(), TemperatureScreen.class));
                finish();
                return true;
            }else if (item.getItemId() == R.id.updates) {
                startActivity(new Intent(getApplicationContext(), KeepTrackOfPlantScreen.class));
                finish();
                return true;
            }else if (item.getItemId() == R.id.task) {
                startActivity(new Intent(getApplicationContext(),TaskScreen.class));
                finish();
                return true;
            }
            return false;
        });
    }
   // Function to handle getting directions and launching Google Maps app
    private void getPath(String startingPoint, String endPoint) {
        try {
            Uri uri = Uri.parse("https://www.google.com/maps/dir/" + startingPoint + "/" + endPoint);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException exception) {
            //If Google Maps app is not installed, open Play Store to install it
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps&hl=en&gl=US");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
