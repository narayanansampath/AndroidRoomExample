package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.Patient;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.R;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.ViewModel.PatientViewModel;


public class PatientFragment extends Fragment {
    PatientViewModel viewModel;
    private Button submitButton,viewInformationButton;
    private EditText patientIdEditText, firstNameEditText, lastNameEditText, departmentEditText, nurseIdEditText, roomEditText;
    String firstName, lastName, department, room;
    int nurseId, patientId;
    boolean doesMatch = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_patient, container, false);
        patientIdEditText = (EditText) rootView.findViewById(R.id.patientt1);
        firstNameEditText = rootView.findViewById(R.id.patientt2);
        lastNameEditText = rootView.findViewById(R.id.patientt3);
        departmentEditText = rootView.findViewById(R.id.patientt4);
        nurseIdEditText = rootView.findViewById(R.id.patientt5);
        roomEditText = rootView.findViewById(R.id.patientt6);
        submitButton = (Button) rootView.findViewById(R.id.patientb1);
        viewInformationButton = rootView.findViewById(R.id.patientb2);
        viewModel =  ViewModelProviders.of(this).get(PatientViewModel.class);

        viewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(getActivity(), "Patient successfully saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Error saving user", Toast.LENGTH_SHORT).show();
                }
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patientId = Integer.parseInt(patientIdEditText.getText().toString());
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
                viewModel.insert(patientData);
            }
        });

        return rootView;


    }
}

