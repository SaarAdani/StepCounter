package com.hackeru.saar.stepcounter;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;

public class MyStepCounterService extends Service implements SensorEventListener {

    private static final String SHARED_PREFS_FILE = "sharedPrefs";
    private static final String SENSOR_STEPS = "sensorSteps";
    SensorManager sensorManager;
    Sensor stepCounterSensor;

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    public MyStepCounterService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        settings = getSharedPreferences(SHARED_PREFS_FILE, 0);
        editor = settings.edit();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_UI);



        return START_STICKY;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        editor.putFloat(SENSOR_STEPS, sensorEvent.values[0]).commit();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
