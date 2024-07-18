package com.era.datamahasiswaapp.view.data;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.era.datamahasiswaapp.R;
import com.era.datamahasiswaapp.database.MyDatabaseHelper;
import com.era.datamahasiswaapp.view.adapter.CustomAdapter;
import com.era.datamahasiswaapp.view.add.AddActivity;
import com.era.datamahasiswaapp.view.main.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    ArrayList<String> mahasiswa_id, nim, nama, tanggal_lahir, jenis_kelamin, alamat;
    MyDatabaseHelper myDB;
    ImageView back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        back_button = findViewById(R.id.back_button);

        recyclerView = findViewById(R.id.recyclerView);
        myDB = new MyDatabaseHelper(DataActivity.this);
        mahasiswa_id = new ArrayList<>();
        nim = new ArrayList<>();
        nama = new ArrayList<>();
        tanggal_lahir = new ArrayList<>();
        jenis_kelamin = new ArrayList<>();
        alamat = new ArrayList<>();

        customAdapter = new CustomAdapter(DataActivity.this, mahasiswa_id, nim, nama, tanggal_lahir, jenis_kelamin, alamat);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(DataActivity.this));

        fetchData();

        FloatingActionButton addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void fetchData() {
        Cursor cursor = myDB.readAllMahasiswaData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                mahasiswa_id.add(cursor.getString(0));
                nim.add(cursor.getString(1));
                nama.add(cursor.getString(2));
                tanggal_lahir.add(cursor.getString(3));
                jenis_kelamin.add(cursor.getString(4));
                alamat.add(cursor.getString(5));
            }
            customAdapter.notifyDataSetChanged();
        }
    }
}
