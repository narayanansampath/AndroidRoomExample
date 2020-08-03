package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.UI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.Nurse;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.R;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.ViewModel.LoginViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;
    String username, password;
    Nurse loginData;
    boolean doesMatch = false;
    Map<Integer, String> userDetails = new HashMap<>();
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Login");

        usernameEditText = findViewById(R.id.logint1);
        passwordEditText = findViewById(R.id.logint2);
        Button loginButton = findViewById(R.id.loginb1);
        Button signupButton = findViewById(R.id.loginb2);
        LoginViewModel viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginData = new Nurse();
        editor = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        viewModel.getGetAllData().observe(this, new Observer<List<Nurse>>() {
            @Override
            public void onChanged(List<Nurse> loginTables) {
                for(Nurse data : loginTables) {
                    userDetails.put(data.getNurseId(), data.getPassword());
                }
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();
                for(Integer key : userDetails.keySet()){
                    if(String.valueOf(key).equals(username) && userDetails.get(key).equals(password)){
                        doesMatch = true;
                    }
                }

                if(doesMatch) {
                    editor.putBoolean("isLoggedIn", true);
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    showErrorDialog("Invalid login details, please try again.");
                }
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    void showErrorDialog(String message) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);
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
