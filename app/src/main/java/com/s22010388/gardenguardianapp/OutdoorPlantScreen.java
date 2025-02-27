package com.s22010388.gardenguardianapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.s22010388.gardenguardianapp.databinding.ActivityOutdoorPlantScreenBinding;

public class OutdoorPlantScreen extends AppCompatActivity {

    ActivityOutdoorPlantScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOutdoorPlantScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        String[] outdoorPlantName= {"Bird of paradise","Rose flowers","Tree philodendron","Boston Fern","Fiddle leaf fig","Water lili",
                "Banana leaf plant","Crotons","variegated fiddle leaf fig","Rubber tree"};
        int[] image = {R.drawable.outdoor_plant2,R.drawable.outdoor_plant10,R.drawable.outdoor_plant3,R.drawable.outdoorplant4,R.drawable.outdoor_plant5,R.drawable.outdoor_plant11,R.drawable.outdoor_plant7,R.drawable.outdoor_plant8,
                R.drawable.outdoor_plant9,R.drawable.outdoor_plant12};

        GridAdapter gridAdapter = new GridAdapter(OutdoorPlantScreen.this,outdoorPlantName,image);
        binding.gridView1.setAdapter(gridAdapter);


        binding.gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(OutdoorPlantScreen.this,"You Clicked on "+ outdoorPlantName[position], Toast.LENGTH_SHORT).show();

            }
        });

    }
}