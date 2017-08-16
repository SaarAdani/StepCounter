package com.hackeru.saar.stepcounter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class MyNewDayReceiver extends BroadcastReceiver {

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

        editor.putFloat(NEW_DAY_STEPS, settings.getFloat(SENSOR_STEPS, 0)).commit();

        editor.putFloat(REBOOT_STEPS, 0).commit();

    }
}
