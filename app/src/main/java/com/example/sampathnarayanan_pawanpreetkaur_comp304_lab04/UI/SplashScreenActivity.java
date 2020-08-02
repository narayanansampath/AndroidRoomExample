package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.R;

import static com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.UI.LoginActivity.MY_PREFS_NAME;


public class SplashScreenActivity extends AppCompatActivity {

    private int SLEEP_TIMER = 3;
    //currently it will return false everytime, use sharedpreference to update this value
    boolean isUserLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_splash);
        getSupportActionBar().hide();
        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();

    }

    private class LogoLauncher extends Thread {
        public void run(){
            try{
                sleep(1000 * SLEEP_TIMER);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            Intent intent;
            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            isUserLoggedIn  = prefs.getBoolean("isLoggedIn", false);
            if(isUserLoggedIn){
                intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            } else {
                intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
            }
            startActivity(intent);
            SplashScreenActivity.this.finish();
        }
    }
}