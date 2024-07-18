package com.era.datamahasiswaapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Datamahasiswaapp.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_MAHASISWA = "mahasiswa";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NIM = "nim";
    private static final String COLUMN_NAME = "nama";
    private static final String COLUMN_DOB = "tanggal_lahir";
    private static final String COLUMN_GENDER = "jenis_kelamin";
    private static final String COLUMN_ADDRESS = "alamat";

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_FULLNAME = "fullname";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_USER_DOB = "dob";
    public static final String COLUMN_USER_GENDER = "gender";
    public static final String COLUMN_USER_ADDRESS = "address";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createMahasiswaTable =
                "CREATE TABLE " + TABLE_MAHASISWA +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NIM + " TEXT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_DOB + " TEXT, " +
                        COLUMN_GENDER + " TEXT, " +
                        COLUMN_ADDRESS + " TEXT);";
        db.execSQL(createMahasiswaTable);

        String createUsersTable =
                "CREATE TABLE " + TABLE_USERS + " (" +
                        COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_FULLNAME + " TEXT," +
                        COLUMN_USERNAME + " TEXT," +
                        COLUMN_EMAIL + " TEXT," +
                        COLUMN_PASSWORD + " TEXT," +
                        COLUMN_USER_DOB + " TEXT," +
                        COLUMN_USER_GENDER + " TEXT," +
                        COLUMN_USER_ADDRESS + " TEXT)";
        db.execSQL(createUsersTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAHASISWA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // mahasiswa


    public long addMahasiswa(String nim, String name, String dob, String gender, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NIM, nim);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DOB, dob);
        cv.put(COLUMN_GENDER, gender);
        cv.put(COLUMN_ADDRESS, address);

        long result = db.insert(TABLE_MAHASISWA, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
        return result;
    }


    public Cursor readAllMahasiswaData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_MAHASISWA;
        return db.rawQuery(query, null);
    }

    public boolean updateMahasiswa(String id, String nim, String name, String dob, String gender, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NIM, nim);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DOB, dob);
        values.put(COLUMN_GENDER, gender);
        values.put(COLUMN_ADDRESS, address);

        int rowsAffected = db.update(TABLE_MAHASISWA, values, COLUMN_ID + "=?", new String[]{id});
        return rowsAffected > 0;
    }

    public int deleteMahasiswa(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_MAHASISWA, COLUMN_ID + "=?", new String[]{id});
    }

    // user
    public long insertUser(String fullname, String username, String email,
                           String password, String dob, String gender, String address) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_FULLNAME, fullname);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_USER_DOB, dob);
        values.put(COLUMN_USER_GENDER, gender);
        values.put(COLUMN_USER_ADDRESS, address);

        long newRowId = db.insert(TABLE_USERS, null, values);
        db.close();

        return newRowId;
    }
}




