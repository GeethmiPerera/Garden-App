package com.s22010388.gardenguardianapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class TaskScreen extends AppCompatActivity {

    private EditText editTextTask;
    private Button buttonAdd;
    private ListView listViewTasks;
    private ArrayList<Task> taskList;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_screen);

        editTextTask = findViewById(R.id.editTextTask);
        buttonAdd = findViewById(R.id.buttonAdd);
        listViewTasks = findViewById(R.id.listViewTasks);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.task);

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
                startActivity(new Intent(getApplicationContext(),TemperatureScreen.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.updates) {
                startActivity(new Intent(getApplicationContext(), KeepTrackOfPlantScreen.class));
                finish();
                return true;
            }else if (item.getItemId() == R.id.task) {
                finish();
                return true;
            }
            return false;
        });

        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, taskList);
        listViewTasks.setAdapter(taskAdapter);

        buttonAdd.setOnClickListener(v -> {
            String taskText = editTextTask.getText().toString();
            if (!taskText.isEmpty()) {
                taskList.add(new Task(taskText));
                taskAdapter.notifyDataSetChanged();
                editTextTask.setText("");
            }
        });
    }
}
