package com.hackeru.saar.stepcounter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String SHARED_PREFS_FILE = "sharedPrefs";
    private static final String SENSOR_STEPS = "sensorSteps";
    private static final String NEW_DAY_STEPS = "newDaySteps";
    private static final String REBOOT_STEPS = "rebootSteps";

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    TextView sensorStepTV;
    TextView newDayStepsTV;
    TextView rebootStepsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, MyStepCounterService.class));

        sensorStepTV = (TextView) findViewById(R.id.sensor_steps);
        newDayStepsTV = (TextView) findViewById(R.id.new_day_steps);
        rebootStepsTV = (TextView) findViewById(R.id.reboot_steps);

        settings = getSharedPreferences(SHARED_PREFS_FILE, 0);
        editor = settings.edit();


    }

    @Override
    protected void onResume() {
        super.onResume();

        float sensorStep = settings.getFloat(SENSOR_STEPS, 0);
        float newDaySteps = settings.getFloat(NEW_DAY_STEPS, 0);
        float rebootSteps = settings.getFloat(REBOOT_STEPS, 0);

        sensorStepTV.setText(sensorStep - newDaySteps + rebootSteps + "");
        newDayStepsTV.setText(newDaySteps + "");
        rebootStepsTV.setText(rebootSteps + "");
    }
}
