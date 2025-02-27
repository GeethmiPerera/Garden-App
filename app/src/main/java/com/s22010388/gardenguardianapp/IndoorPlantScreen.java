package com.s22010388.gardenguardianapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.s22010388.gardenguardianapp.databinding.ActivityIndoorPlantScreenBinding;

public class IndoorPlantScreen extends AppCompatActivity {

    ActivityIndoorPlantScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIndoorPlantScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        String[] outdoorPlantName= {"Ariane Fern","Cactus","Christmas Cactus","Creeping torenia","Emerald queen fern","Emina fern",
                "English lvy","Gazania","Pancy","Silver dust"};
        int[] image = {R.drawable.ariane_fern,R.drawable.cactus1,R.drawable.christmas_cactus,R.drawable.creeping_torenia,R.drawable.emerald,R.drawable.emina,R.drawable.english,R.drawable.pancy,
                R.drawable.gazania,R.drawable.silver};

        GridAdapter gridAdapter = new GridAdapter(IndoorPlantScreen.this,outdoorPlantName,image);
        binding.gridView1.setAdapter(gridAdapter);


        binding.gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(IndoorPlantScreen.this,"You Clicked on "+ outdoorPlantName[position], Toast.LENGTH_SHORT).show();

            }
        });

    }
}