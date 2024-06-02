package com.example.tugasakhir.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public class ThemeUtils {

    private static final String PREFERENCES_FILE = "app_preferences";
    private static final String PREFERENCES_THEME_KEY = "theme_key";

    public static void applyTheme(boolean isDarkMode) {
        AppCompatDelegate.setDefaultNightMode(
                isDarkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );
    }

    public static void saveThemePreference(Context context, boolean isDarkMode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PREFERENCES_THEME_KEY, isDarkMode);
        editor.apply();
    }

    public static boolean loadThemePreference(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(PREFERENCES_THEME_KEY, false); // Default is false (light mode)
    }
}
