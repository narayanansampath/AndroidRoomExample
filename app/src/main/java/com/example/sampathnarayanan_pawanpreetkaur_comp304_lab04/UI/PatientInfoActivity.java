package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.Nurse;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.Patient;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.R;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.ViewModel.LoginViewModel;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.ViewModel.PatientViewModel;

import java.util.ArrayList;
import java.util.List;

public class PatientInfoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Patient> patientsList = new ArrayList<Patient>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PatientViewModel viewModel = ViewModelProviders.of(this).get(PatientViewModel.class);

        viewModel.getAllPatient().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                Log.e("Patientsss", patients.toString());
                patientsList.addAll(patients);
                PatientAdapter adapter = new PatientAdapter(getApplication(), patientsList);
                //setting adapter to recyclerview
                recyclerView.setAdapter(adapter);
            }
        });

    }
}