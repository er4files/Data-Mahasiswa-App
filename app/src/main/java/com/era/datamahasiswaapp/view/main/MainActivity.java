package com.era.datamahasiswaapp.view.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.era.datamahasiswaapp.R;
import com.era.datamahasiswaapp.view.add.AddActivity;
import com.era.datamahasiswaapp.view.data.DataActivity;
import com.era.datamahasiswaapp.view.information.InformationActivity;
import com.era.datamahasiswaapp.view.welcome.WelcomeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.logout_icon).setOnClickListener(v -> logout());
        findViewById(R.id.input_data_button).setOnClickListener(v -> openAddActivity());
        findViewById(R.id.view_data_button).setOnClickListener(v -> openDataActivity());
        findViewById(R.id.information_button).setOnClickListener(v -> openInformationActivity());
    }

    private void logout() {
        SharedPreferences.Editor editor = getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();

        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void openAddActivity() {
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        startActivity(intent);
    }

    private void openDataActivity() {
        Intent intent = new Intent(MainActivity.this, DataActivity.class);
        startActivity(intent);
    }

    private void openInformationActivity() {
        Intent intent = new Intent(MainActivity.this, InformationActivity.class);
        startActivity(intent);
    }
}
