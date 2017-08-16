package com.hackeru.saar.stepcounter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class MyBootReceiver extends BroadcastReceiver {

    private static final String SHARED_PREFS_FILE = "sharedPrefs";
    private static final String SENSOR_STEPS = "sensorSteps";
    private static final String NEW_DAY_STEPS = "newDaySteps";
    private static final String REBOOT_STEPS = "rebootSteps";

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    @Override
    public void onReceive(Context context, Intent intent) {

        settings = context.getSharedPreferences(SHARED_PREFS_FILE, 0);
        editor = settings.edit();

        float sensorSteps = settings.getFloat(SENSOR_STEPS, 0);
        float newDaySteps = settings.getFloat(NEW_DAY_STEPS, 0);

        editor.putFloat(REBOOT_STEPS, sensorSteps - newDaySteps).commit();

        editor.putFloat(SENSOR_STEPS, 0).commit();
        editor.putFloat(NEW_DAY_STEPS, 0).commit();

        context.startService(new Intent(context, MyStepCounterService.class));

    }
}
