package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.UI.LoginActivity.MY_PREFS_NAME;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new PatientFragment()).commit();
        }
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            SharedPreferences.Editor prefs = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            prefs.remove("isLoggedIn");
            prefs.apply();
           Intent intent = new Intent(this, LoginActivity.class);
           startActivity(intent);
           finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_patient:
                            selectedFragment = new PatientFragment();
                            break;
                        case R.id.nav_test:
                            selectedFragment = new TestFragment();
                            break;
                        case R.id.nav_view:
                            selectedFragment = new ViewFragment();
                            break;
                        case R.id.nav_update:
                            selectedFragment = new UpdateFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };
}