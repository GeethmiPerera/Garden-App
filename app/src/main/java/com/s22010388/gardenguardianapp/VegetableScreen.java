package com.s22010388.gardenguardianapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.s22010388.gardenguardianapp.databinding.ActivityVegetableScreenBinding;

public class VegetableScreen extends AppCompatActivity {

    ActivityVegetableScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVegetableScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        String[] outdoorPlantName = {"Beetroot", "Cabbage", "Carrot", "Cucumber", "Eggplant", "Lady finger",
                "Leeks", "Pumpkin", "Tomato", "Water spinach"};
        int[] image = {R.drawable.beetroot, R.drawable.cabbage, R.drawable.carrot, R.drawable.cucumber, R.drawable.eggplant, R.drawable.lady_finger, R.drawable.leeks, R.drawable.pumpkin,
                R.drawable.tomato, R.drawable.water_spinach};

        GridAdapter gridAdapter = new GridAdapter(VegetableScreen.this, outdoorPlantName, image);
        binding.gridView1.setAdapter(gridAdapter);


        binding.gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(VegetableScreen.this, "You Clicked on " + outdoorPlantName[position], Toast.LENGTH_SHORT).show();

            }
        });
    }
}