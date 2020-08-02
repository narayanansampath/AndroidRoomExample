package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEditText = findViewById(R.id.logint1);
        passwordEditText = findViewById(R.id.logint2);
        Button loginButton = findViewById(R.id.loginb1);
        Button signupButton = findViewById(R.id.loginb2);
        LoginViewModel viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginData = new Nurse();

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
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid login details, please try again.", Toast.LENGTH_SHORT).show();
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
}
