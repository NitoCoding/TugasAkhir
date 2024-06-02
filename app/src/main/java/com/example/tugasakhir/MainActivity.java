package com.example.tugasakhir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.tugasakhir.fragment.BookFragment;
import com.example.tugasakhir.fragment.ChapterFragment;
import com.example.tugasakhir.fragment.MarkFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Fragment homeFragment = new BookFragment();
    private Fragment markFragment = new MarkFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(com.google.android.material.R.style.Base_Theme_AppCompat_Light);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        homeFragment = new BookFragment();
        markFragment = new MarkFragment();

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.nav_home); // Set default selected item

        // Load default fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, homeFragment).commit();
    }

    @Override
    public boolean
    onNavigationItemSelected(@NonNull MenuItem item)
    {

        if (item.getItemId() == R.id.nav_home) {


                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_container, homeFragment)
                        .commit();

            return true;
        } else if (item.getItemId() == R.id.nav_mark) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, markFragment)
                    .commit();
            return true;
        }
        return false;
    }
}

