package com.era.datamahasiswaapp.view.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.era.datamahasiswaapp.R;
import com.era.datamahasiswaapp.database.MyDatabaseHelper;
import com.era.datamahasiswaapp.view.detail.DetailActivity;
import com.era.datamahasiswaapp.view.update.UpdateActivity;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> mahasiswa_id, nim, nama, tanggal_lahir, jenis_kelamin, alamat;
    private MyDatabaseHelper myDB;

    public CustomAdapter(Context context, ArrayList<String> mahasiswa_id, ArrayList<String> nim,
                         ArrayList<String> nama, ArrayList<String> tanggal_lahir,
                         ArrayList<String> jenis_kelamin, ArrayList<String> alamat) {
        this.context = context;
        this.mahasiswa_id = mahasiswa_id;
        this.nim = nim;
        this.nama = nama;
        this.tanggal_lahir = tanggal_lahir;
        this.jenis_kelamin = jenis_kelamin;
        this.alamat = alamat;
        this.myDB = new MyDatabaseHelper(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id_txt.setText(mahasiswa_id.get(position));
        holder.nama_mahasiswa_txt.setText(nama.get(position));

        holder.update_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(context, UpdateActivity.class);
                    intent.putExtra("MAHASISWA_ID", mahasiswa_id.get(adapterPosition));
                    intent.putExtra("NIM", nim.get(adapterPosition));
                    intent.putExtra("NAMA", nama.get(adapterPosition));
                    intent.putExtra("TANGGAL_LAHIR", tanggal_lahir.get(adapterPosition));
                    intent.putExtra("JENIS_KELAMIN", jenis_kelamin.get(adapterPosition));
                    intent.putExtra("ALAMAT", alamat.get(adapterPosition));
                    context.startActivity(intent);
                }
            }
        });

        holder.delete_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    confirmDeleteDialog(adapterPosition);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("MAHASISWA_ID", mahasiswa_id.get(adapterPosition));
                    intent.putExtra("NIM", nim.get(adapterPosition));
                    intent.putExtra("NAMA", nama.get(adapterPosition));
                    intent.putExtra("TANGGAL_LAHIR", tanggal_lahir.get(adapterPosition));
                    intent.putExtra("JENIS_KELAMIN", jenis_kelamin.get(adapterPosition));
                    intent.putExtra("ALAMAT", alamat.get(adapterPosition));
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mahasiswa_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_txt, nama_mahasiswa_txt;
        ImageView update_icon, delete_icon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id_txt);
            nama_mahasiswa_txt = itemView.findViewById(R.id.nama_mahasiswa_txt);
            update_icon = itemView.findViewById(R.id.update_icon);
            delete_icon = itemView.findViewById(R.id.delete_icon);
        }
    }

    private void confirmDeleteDialog(final int position) {
        new AlertDialog.Builder(context)
                .setTitle("Konfirmasi Hapus")
                .setMessage("Apakah Anda yakin ingin menghapus mahasiswa ini?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int deletedRows = myDB.deleteMahasiswa(mahasiswa_id.get(position));
                        if (deletedRows > 0) {
                            Toast.makeText(context, "Data mahasiswa berhasil dihapus.", Toast.LENGTH_SHORT).show();
                            mahasiswa_id.remove(position);
                            nim.remove(position);
                            nama.remove(position);
                            tanggal_lahir.remove(position);
                            jenis_kelamin.remove(position);
                            alamat.remove(position);
                            notifyItemRemoved(position);
                        } else {
                            Toast.makeText(context, "Gagal menghapus data mahasiswa.", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Tidak", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
