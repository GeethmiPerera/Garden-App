package com.s22010388.gardenguardianapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PlantCategoryScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_category_screen);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);


        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                return true;
            } else if (item.getItemId() == R.id.location) {
                startActivity(new Intent(getApplicationContext(), LocationScreen.class));
                finish();
                return true;
            }
            else if (item.getItemId() == R.id.temperature) {
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
        // Set click listeners for indoor and outdoor plant categories
        TextView indoorPlantTextView = findViewById(R.id.indoorPlant);
        indoorPlantTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlantCategoryScreen.this, IndoorPlantScreen.class));
            }
        });

        TextView outdoorPlantTextView = findViewById(R.id.outdoorPlant);
        outdoorPlantTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlantCategoryScreen.this, OutdoorPlantScreen.class));
            }
        });
        TextView fruitPlantTextView = findViewById(R.id.fruits);
        fruitPlantTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlantCategoryScreen.this, FruitsScreen.class));
            }
        });
        TextView vegetablePlantTextView = findViewById(R.id.vegetables);
        vegetablePlantTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlantCategoryScreen.this, VegetableScreen.class));
            }
        });

    }
}