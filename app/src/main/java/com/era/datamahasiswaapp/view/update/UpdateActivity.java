package com.era.datamahasiswaapp.view.update;

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

public class UpdateActivity extends AppCompatActivity {

    EditText nim_input, name_input, dob_input, gender_input, address_input;
    String mahasiswa_id, nim, name, dob, gender, address;
    MyDatabaseHelper myDB;
    Button updateButton;
    ImageView back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nim_input = findViewById(R.id.nim_input);
        name_input = findViewById(R.id.name_input);
        dob_input = findViewById(R.id.dob_input);
        gender_input = findViewById(R.id.gender_input);
        address_input = findViewById(R.id.address_input);
        updateButton = findViewById(R.id.update_button);
        back_button = findViewById(R.id.back_button);

        myDB = new MyDatabaseHelper(this);

        mahasiswa_id = getIntent().getStringExtra("MAHASISWA_ID");
        nim = getIntent().getStringExtra("NIM");
        name = getIntent().getStringExtra("NAMA");
        dob = getIntent().getStringExtra("TANGGAL_LAHIR");
        gender = getIntent().getStringExtra("JENIS_KELAMIN");
        address = getIntent().getStringExtra("ALAMAT");


        nim_input.setText(nim);
        name_input.setText(name);
        dob_input.setText(dob);
        gender_input.setText(gender);
        address_input.setText(address);


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nim = nim_input.getText().toString().trim();
                String name = name_input.getText().toString().trim();
                String dob = dob_input.getText().toString().trim();
                String gender = gender_input.getText().toString().trim();
                String address = address_input.getText().toString().trim();

                // Validation: Check if all fields are filled
                if (nim.equals("") || name.equals("") || dob.equals("") || gender.equals("") || address.equals("")) {
                    Toast.makeText(UpdateActivity.this, "Harap isi semua kolom.", Toast.LENGTH_SHORT).show();
                    return;
                }


                boolean isUpdated = myDB.updateMahasiswa(mahasiswa_id, nim, name, dob, gender, address);
                if (isUpdated) {
                    Toast.makeText(UpdateActivity.this, "Data mahasiswa berhasil diperbarui.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(UpdateActivity.this, "Gagal memperbarui data mahasiswa.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
