package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.Nurse;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.R;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.ViewModel.LoginViewModel;

public class SignUpActivity extends AppCompatActivity {
    private LoginViewModel viewModel;
    private Button registerButton;
    private EditText nurseIdEditText, firstNameEditText, lastNameEditText, departmentEditText, passwordEditText, confirmPassowrdEditText;
    String nurseId, password, firstName, lastName, departament, confirmPassowrd;
    Nurse loginData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().setTitle("Registration");


        nurseIdEditText = findViewById(R.id.signupt1);
        firstNameEditText = findViewById(R.id.signupt2);
        lastNameEditText = findViewById(R.id.signupt3);
        departmentEditText = findViewById(R.id.signupt4);
        passwordEditText = findViewById(R.id.signupt5);
        confirmPassowrdEditText = findViewById(R.id.signupt6);
        registerButton = findViewById(R.id.signupb1);
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginData = new Nurse();

        viewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(SignUpActivity.this, "User successfully saved", Toast.LENGTH_SHORT).show();
                } else {
                    showErrorDialog("Error creating account, please try again later.");
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nurseId = nurseIdEditText.getText().toString();
                password = passwordEditText.getText().toString();
                firstName = firstNameEditText.getText().toString();
                lastName = lastNameEditText.getText().toString();
                departament = departmentEditText.getText().toString();
                confirmPassowrd = confirmPassowrdEditText.getText().toString();
                if(!confirmPassowrd.equals(password)){
                    showErrorDialog("Password does not match, please recheck and try again.");
                }
                else if(!nurseId.isEmpty() && !password.isEmpty()) {
                    loginData.setNurseId((Integer.parseInt(nurseId)));
                    loginData.setPassword(password);
                    loginData.setFirstName(firstName);
                    loginData.setLastName(lastName);
                    loginData.setDepartment(departament);
                    viewModel.insert(loginData);
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    showErrorDialog("Invalid signup details, please try again.");
                }
            }
        });
    }

    void showErrorDialog(String message) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Error");
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
