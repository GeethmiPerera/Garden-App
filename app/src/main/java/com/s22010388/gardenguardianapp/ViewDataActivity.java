package com.s22010388.gardenguardianapp;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ViewDataActivity extends AppCompatActivity {

    DatabaseHelper myDatabase;
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        myDatabase = new DatabaseHelper(this);
        tableLayout = findViewById(R.id.tableLayout);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.temperature);

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
            }
            return false;
        });
        displayData();
    }

    public void displayData() {
        Cursor results = myDatabase.getAllData();
        if (results.getCount() == 0) {
            Toast.makeText(ViewDataActivity.this, "No data available in the table", Toast.LENGTH_LONG).show();
            return;
        }

        // Add table headers
        TableRow headerRow = new TableRow(this);
        headerRow.addView(createTextView("ID"));
        headerRow.addView(createTextView("Name"));
        headerRow.addView(createTextView("Type"));
        headerRow.addView(createTextView("Date"));
        tableLayout.addView(headerRow);

        // Iterate through each row in the cursor
        while (results.moveToNext()) {
            TableRow tableRow = new TableRow(this);
            tableRow.addView(createTextView(String.valueOf(results.getInt(0)))); // ID
            tableRow.addView(createTextView(results.getString(1))); // Name
            tableRow.addView(createTextView(results.getString(2))); // Type
            tableRow.addView(createTextView(results.getString(3))); // Date
            tableLayout.addView(tableRow);
        }
    }

    private TextView createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(10, 10, 10, 10);
        return textView;
    }
}
