package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.Patient;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.R;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.ViewModel.PatientViewModel;

import java.util.List;


public class UpdateFragment extends Fragment {

    EditText etPatientId;
    Button viewInfo, updateInfo;
    PatientViewModel viewModel;
    boolean noMatchFound = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_update, container, false);
        etPatientId = rootView.findViewById(R.id.updatet1);
        viewInfo =  rootView.findViewById(R.id.updatetb1);
        updateInfo =  rootView.findViewById(R.id.updateb2);
        viewModel =  ViewModelProviders.of(this).get(PatientViewModel.class);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Update Patient info");  // provide compatibility to all the versions

        viewInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etPatientId.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Invalid patient id please enter a valid patient id.", Toast.LENGTH_SHORT).show();

                } else {
                    checkPatientId( Integer.valueOf(etPatientId.getText().toString()));
                    if(noMatchFound){
                        Toast.makeText(getActivity(), "No patient data found.", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(getActivity(), UpdatePatientActivity.class);
                        intent.putExtra("patientId", Integer.valueOf(etPatientId.getText().toString()));
                        intent.putExtra("isEditable", false);
                        startActivity(intent);
                    }
                }
            }
        });

        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etPatientId.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Invalid patient id please enter a valid patient id.", Toast.LENGTH_SHORT).show();

                } else {
                    checkPatientId( Integer.valueOf(etPatientId.getText().toString()));
                    if(noMatchFound){
                        Toast.makeText(getActivity(), "No patient data found.", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(getActivity(), UpdatePatientActivity.class);
                        intent.putExtra("patientId", Integer.valueOf(etPatientId.getText().toString()));
                        intent.putExtra("isEditable", false);
                        intent.putExtra("isEditable", true);
                        startActivity(intent);
                    }
                }
            }
        });

        return rootView;
    }

    private void checkPatientId(final int patientId) {
        viewModel.getAllPatient().observe(getViewLifecycleOwner(), new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                for(Patient data : patients) {
                    if(data.getPatientId() == patientId){
                        noMatchFound = false;
                    }
                }
            }
        });
    }
}
