package com.era.datamahasiswaapp.view.add;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.era.datamahasiswaapp.R;
import com.era.datamahasiswaapp.database.MyDatabaseHelper;
import com.era.datamahasiswaapp.view.data.DataActivity;
import com.era.datamahasiswaapp.view.main.MainActivity;

public class AddActivity extends AppCompatActivity {

    EditText nim_input, name_input, dob_input, gender_input, address_input;
    Button add_button;
    MyDatabaseHelper myDB;
    ImageView back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nim_input = findViewById(R.id.nim_input);
        name_input = findViewById(R.id.name_input);
        dob_input = findViewById(R.id.dob_input);
        gender_input = findViewById(R.id.gender_input);
        address_input = findViewById(R.id.address_input);
        add_button = findViewById(R.id.add_button);
        back_button = findViewById(R.id.back_button);

        myDB = new MyDatabaseHelper(AddActivity.this);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nim = nim_input.getText().toString().trim();
                String name = name_input.getText().toString().trim();
                String dob = dob_input.getText().toString().trim();
                String gender = gender_input.getText().toString().trim();
                String address = address_input.getText().toString().trim();

                if (nim.equals("") || name.equals("") || dob.equals("") || gender.equals("") || address.equals("")) {
                    Toast.makeText(AddActivity.this, "Harap isi semua kolom.", Toast.LENGTH_SHORT).show();
                } else {
                    long result = myDB.addMahasiswa(nim, name, dob, gender, address);
                    if (result != -1) {
                        Toast.makeText(AddActivity.this, "Data mahasiswa berhasil ditambahkan.", Toast.LENGTH_SHORT).show();
                        clearInputs();
                    } else {
                        Toast.makeText(AddActivity.this, "Gagal menambah data mahasiswa.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, DataActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void clearInputs() {
        nim_input.setText("");
        name_input.setText("");
        dob_input.setText("");
        gender_input.setText("");
        address_input.setText("");
    }
}
