package com.example.student.crystalball;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView answerText;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private double acceleration;
    private  double currentAcceleration;
    private double previousAcceleration;

    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            double x  = event.values[0];
            double y  = event.values[1];
            double z  = event.values[2];

            previousAcceleration = currentAcceleration;
            currentAcceleration = Math.sqrt(x * x + y * y + z * z);
            double delta = currentAcceleration - previousAcceleration;
            acceleration = acceleration * 0.9f + delta;

            if (acceleration > 15) {
                Toast toast = Toast.makeText(getApplication(), "Device has shaken", Toast.LENGTH_SHORT);
                toast.show();

            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        acceleration = 0.0f;
        currentAcceleration = SensorManager.GRAVITY_EARTH;
        previousAcceleration = sensorManager.GRAVITY_EARTH;

        answerText = (TextView) findViewById(R.id.answerText);
        answerText.setText(Predictions.get().getPrediction());
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorListener, accelerometer, sensorManager.SENSOR_DELAY_NORMAL);
    }
}
