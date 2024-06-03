package com.example.tugasakhir.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

    private static final String PREF_NAME = "HadithLibraryPref";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    public PreferenceManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}
