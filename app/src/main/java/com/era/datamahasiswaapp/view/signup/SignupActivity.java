package com.era.datamahasiswaapp.view.signup;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.era.datamahasiswaapp.R;
import com.era.datamahasiswaapp.database.MyDatabaseHelper;
import com.era.datamahasiswaapp.view.login.LoginActivity;

import java.util.Calendar;

public class SignupActivity extends AppCompatActivity {

    private EditText editTextFullName, editTextUsername, editTextEmail, editTextPassword,
            editTextDOB, editTextAddress;
    private Spinner genderSpinner;
    private Button signupButton;
    private MyDatabaseHelper dbHelper;

    private TextView signInTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dbHelper = new MyDatabaseHelper(this);

        editTextFullName = findViewById(R.id.fullNameEditText);
        editTextUsername = findViewById(R.id.usernameEditText);
        editTextEmail = findViewById(R.id.emailEditText);
        editTextPassword = findViewById(R.id.passwordEditText);
        editTextDOB = findViewById(R.id.dobEditText);
        editTextAddress = findViewById(R.id.addressEditText);
        genderSpinner = findViewById(R.id.genderSpinner);
        signupButton = findViewById(R.id.signupButton);
        signInTextView = findViewById(R.id.signUpTextView);

        editTextDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLogin();
            }
        });
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                SignupActivity.this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    editTextDOB.setText(selectedDate);
                },
                year, month, day);

        datePickerDialog.show();
    }

    private void registerUser() {
        String fullName = editTextFullName.getText().toString();
        String username = editTextUsername.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String dob = editTextDOB.getText().toString();
        String address = editTextAddress.getText().toString();
        String gender = genderSpinner.getSelectedItem().toString();

        if (fullName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || dob.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else {
            long result = dbHelper.insertUser(fullName, username, email, password, dob, gender, address);

            if (result == -1) {
                Toast.makeText(this, "Sign Up failed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Sign Up successful", Toast.LENGTH_SHORT).show();
                navigateToLogin();
            }
        }
    }

    private void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
