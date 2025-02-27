package com.s22010388.gardenguardianapp;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TemperatureScreen extends AppCompatActivity implements SensorEventListener {
    private TextView textView, textView2;
    private SensorManager sensorManager;
    private Sensor tempSensor;
    private Boolean isTemperatureSensorAvailable;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_screen);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Check if ambient temperature sensor is available
        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isTemperatureSensorAvailable = true;
            textView.setText("Change the temperature");

        } else {
            // Inform user if sensor is not available
            textView.setText("Sensor is not available");
            isTemperatureSensorAvailable = false;
        }
        mediaPlayer = new MediaPlayer();

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
            }else if (item.getItemId() == R.id.temperature) {
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

    @Override
    public void onSensorChanged(SensorEvent event) {
        textView.setText(event.values[0] + " °C");
        // Display planting advice based on temperature
        if (event.values[0] < 30) {
            textView2.setText("Weather is great for planting");
        } else {
            textView2.setText("Weather is not great for planting");
        }

        // Play audio  if temperature is below 30.
        if (event.values[0] < 30 && !isPlaying) {
            isPlaying = true;
            try {
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this, R.raw.weather_voice);  //get the voice record
                mediaPlayer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isTemperatureSensorAvailable) {
            sensorManager.registerListener(this, tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isTemperatureSensorAvailable) {
            sensorManager.unregisterListener(this);
        }
    }
}
