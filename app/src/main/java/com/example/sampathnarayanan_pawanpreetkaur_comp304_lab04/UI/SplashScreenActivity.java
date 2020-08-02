package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.R;


public class SplashScreenActivity extends AppCompatActivity {

    private int SLEEP_TIMER = 3;
    //currently it will return false everytime, use sharedpreference to update this value
    boolean isUserLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


       // requestWindowFeature(Window.FEATURE_NO_TITLE);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.acitivity_home);
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