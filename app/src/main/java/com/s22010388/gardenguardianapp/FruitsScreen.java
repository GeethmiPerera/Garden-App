package com.s22010388.gardenguardianapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.s22010388.gardenguardianapp.databinding.ActivityFruitsScreenBinding;

public class FruitsScreen extends AppCompatActivity {

    ActivityFruitsScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFruitsScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        String[] outdoorPlantName= {"Avocado","Cashew","Chinese Gooseberry","Dragon Fruit","Guava","Mango",
                "Pomegranate","Rambutan","Strawberry","Watermelon"};
        int[] image = {R.drawable.avocado,R.drawable.cashewt,R.drawable.gooseberry,R.drawable.dragon_fruit,R.drawable.guava,R.drawable.mango,R.drawable.pomegranate,R.drawable.rambutan,
                R.drawable.strawberry,R.drawable.watermelon};

        GridAdapter gridAdapter = new GridAdapter(FruitsScreen.this,outdoorPlantName,image);
        binding.gridView1.setAdapter(gridAdapter);


        binding.gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(FruitsScreen.this,"You Clicked on "+ outdoorPlantName[position], Toast.LENGTH_SHORT).show();

            }
        });

    }
}