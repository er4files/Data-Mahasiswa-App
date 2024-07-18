package com.era.datamahasiswaapp.view.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.era.datamahasiswaapp.R;
import com.era.datamahasiswaapp.view.data.DataActivity;

public class DetailActivity extends AppCompatActivity {

    TextView tvDetailId, tvDetailNim, tvDetailNama, tvDetailTanggalLahir, tvDetailJenisKelamin, tvDetailAlamat;

    ImageView back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        tvDetailId = findViewById(R.id.tvDetailId);
        tvDetailNim = findViewById(R.id.tvDetailNim);
        tvDetailNama = findViewById(R.id.tvDetailNama);
        tvDetailTanggalLahir = findViewById(R.id.tvDetailTanggalLahir);
        tvDetailJenisKelamin = findViewById(R.id.tvDetailJenisKelamin);
        tvDetailAlamat = findViewById(R.id.tvDetailAlamat);

        back_button = findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, DataActivity.class);
                startActivity(intent);
                finish();
            }
        });


        Intent intent = getIntent();
        String id = intent.getStringExtra("MAHASISWA_ID");
        String nim = intent.getStringExtra("NIM");
        String nama = intent.getStringExtra("NAMA");
        String tanggal_lahir = intent.getStringExtra("TANGGAL_LAHIR");
        String jenis_kelamin = intent.getStringExtra("JENIS_KELAMIN");
        String alamat = intent.getStringExtra("ALAMAT");


        tvDetailId.setText("ID: " + id);
        tvDetailNim.setText("NIM: " + nim);
        tvDetailNama.setText("Nama: " + nama);
        tvDetailTanggalLahir.setText("Tanggal Lahir: " + tanggal_lahir);
        tvDetailJenisKelamin.setText("Jenis Kelamin: " + jenis_kelamin);
        tvDetailAlamat.setText("Alamat: " + alamat);


    }
}
