package com.s22010388.gardenguardianapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class KeepTrackOfPlantScreen extends AppCompatActivity {
    DatabaseHelper myDatabase;
    EditText editTextId,editTextName, editTextType, editTextDate;
    Button buttonAdd, buttonViewAll, buttonUpdate, buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_track_of_plant_screen);

        // Create new DatabaseHelper instance
        myDatabase = new DatabaseHelper(this);

        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        editTextType = findViewById(R.id.editTextType);
        editTextDate = findViewById(R.id.editTextDate);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonViewAll = findViewById(R.id.buttonViewAll);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.updates);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                startActivity(new Intent(getApplicationContext(), PlantCategoryScreen.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.location) {
                startActivity(new Intent(getApplicationContext(), LocationScreen.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.temperature) {
                finish();
                return true;
            } else if (item.getItemId() == R.id.updates) {
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

        buttonAdd.setOnClickListener(view -> insertData());
        buttonViewAll.setOnClickListener(view -> viewAll());
        buttonUpdate.setOnClickListener(view -> updateData());
        buttonDelete.setOnClickListener(view -> deleteData());
    }

    // Method to handle data insertion
    public void insertData() {
        buttonAdd.setOnClickListener(view -> {
            String name = editTextName.getText().toString();
            String type = editTextType.getText().toString();
            String date = editTextDate.getText().toString();

            if (!name.isEmpty() && !type.isEmpty() && !date.isEmpty()) {
                boolean isDataInserted = myDatabase.insertData(name, type, date);
                if (isDataInserted) {
                    Toast.makeText(KeepTrackOfPlantScreen.this, "Data added successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(KeepTrackOfPlantScreen.this, "Data not added", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(KeepTrackOfPlantScreen.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to view all data
    public void viewAll() {
        buttonViewAll.setOnClickListener(view -> {
            Intent intent = new Intent(KeepTrackOfPlantScreen.this, ViewDataActivity.class);
            startActivity(intent);
        });
    }

    // Method to update data
    private void updateData() {
        String idStr = editTextId.getText().toString();
        String name = editTextName.getText().toString();
        String type = editTextType.getText().toString();
        String date = editTextDate.getText().toString();

        if (!idStr.isEmpty() && !name.isEmpty() && !type.isEmpty() && !date.isEmpty()) {
            int id = Integer.parseInt(idStr);
            boolean isUpdate = myDatabase.updateData(idStr, name, type, date);
            if (isUpdate) {
                Toast.makeText(KeepTrackOfPlantScreen.this, "Data Updated", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(KeepTrackOfPlantScreen.this, "Data not updated", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(KeepTrackOfPlantScreen.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to delete data
    private void deleteData() {
        String idStr = editTextId.getText().toString();
        if (!idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            Integer deleteDataRows = myDatabase.deleteDataById(idStr);
            if (deleteDataRows > 0) {
                Toast.makeText(KeepTrackOfPlantScreen.this, "Data Deleted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(KeepTrackOfPlantScreen.this, "Data not deleted", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(KeepTrackOfPlantScreen.this, "Please enter an ID", Toast.LENGTH_SHORT).show();
        }
    }
}
