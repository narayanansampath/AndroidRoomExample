package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.Nurse;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.Patient;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.R;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.ViewModel.PatientViewModel;

import java.util.List;

public class UpdatePatientActivity extends AppCompatActivity {

    PatientViewModel viewModel;
    private Button updateButton;
    private EditText patientIdEditText, firstNameEditText, lastNameEditText, departmentEditText, nurseIdEditText, roomEditText;
    String firstName, lastName, department, room;
    int nurseId, patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_patient);

        Intent intent = getIntent();
        boolean isEditable = intent.getBooleanExtra("isEditable", false);
        patientId = intent.getIntExtra("patientId",111);
        patientIdEditText = (EditText) findViewById(R.id.etPatientID);
        firstNameEditText = findViewById(R.id.etPatientFName);
        lastNameEditText = findViewById(R.id.etPatientLName);
        departmentEditText = findViewById(R.id.etPatientDept);
        nurseIdEditText = findViewById(R.id.etNurseID);
        roomEditText = findViewById(R.id.etPatientFRoom);
        updateButton = (Button) findViewById(R.id.btnUpdate);
        viewModel =  ViewModelProviders.of(this).get(PatientViewModel.class);

        if(!isEditable){
            patientIdEditText.setFocusable(false);
            firstNameEditText.setFocusable(false);
            lastNameEditText.setFocusable(false);
            departmentEditText.setFocusable(false);
            nurseIdEditText.setFocusable(false);
            roomEditText.setFocusable(false);
        } else {
            patientIdEditText.setFocusable(false);
            firstNameEditText.setFocusableInTouchMode(true);
            lastNameEditText.setFocusableInTouchMode(true);
            departmentEditText.setFocusableInTouchMode(true);
            nurseIdEditText.setFocusableInTouchMode(true);
            roomEditText.setFocusableInTouchMode(true);
            updateButton.setVisibility(View.VISIBLE);
        }

        patientIdEditText.setText(String.valueOf(patientId));

        viewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(getApplication(), "Patient successfully saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplication(), "Error saving user", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.getAllPatient().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                for(Patient data : patients) {
                    if(data.getPatientId() == patientId){
                        firstNameEditText.setText(data.getFName());
                        lastNameEditText.setText(data.getLName());
                        departmentEditText.setText(data.getDept());
                        nurseIdEditText.setText(String.valueOf(data.getNurseId()));
                        roomEditText.setText(data.getRoom());
                    }
                }
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = firstNameEditText.getText().toString();
                lastName = lastNameEditText.getText().toString();
                department = departmentEditText.getText().toString();
                nurseId = Integer.parseInt(nurseIdEditText.getText().toString());
                room = roomEditText.getText().toString();
                Patient patientData = new Patient();
                patientData.setFName(firstName);
                patientData.setLName(lastName);
                patientData.setDept(department);
                patientData.setPatientId(patientId);
                patientData.setRoom(room);
                viewModel.update(patientData);
            }
        });
    }
}