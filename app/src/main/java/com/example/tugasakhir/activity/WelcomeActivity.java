package com.example.tugasakhir.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.tugasakhir.MainActivity;
import com.example.tugasakhir.R;
import com.example.tugasakhir.utils.PreferenceManager;

public class WelcomeActivity extends AppCompatActivity {

    private PreferenceManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefManager = new PreferenceManager(this);
        // Checking for first time launch - before calling setContentView()
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        setContentView(R.layout.activity_welcome);

//        getSupportActionBar().hide();

        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(v -> {
            prefManager.setFirstTimeLaunch(false);
            launchHomeScreen();
        });
    }

    private void launchHomeScreen() {
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        finish();
    }
}