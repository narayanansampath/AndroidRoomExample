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
import androidx.lifecycle.ViewModelProviders;

import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.Patient;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.Test;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.R;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.ViewModel.TestViewModel;

public class TestFragment extends Fragment {

    TestViewModel viewModel;
    private Button submitButton;
    private EditText testIdEditText, patientIdEditText, nurseIdEditText, bplEditText, bphEditText, temperatureEditText, leftVisionEditText, rightVisionEditText;
    int nurseId, patientId, testId;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_test, container, false);

        patientIdEditText = (EditText) rootView.findViewById(R.id.testt2);
        testIdEditText = rootView.findViewById(R.id.testt1);
        nurseIdEditText = rootView.findViewById(R.id.testt3);
        bplEditText = rootView.findViewById(R.id.testt4);
        bphEditText = rootView.findViewById(R.id.testt5);
        temperatureEditText = rootView.findViewById(R.id.testt6);
        leftVisionEditText = rootView.findViewById(R.id.testt7);
        rightVisionEditText = rootView.findViewById(R.id.testt8);
        submitButton = (Button) rootView.findViewById(R.id.testb1);
        viewModel =  ViewModelProviders.of(this).get(TestViewModel.class);

        viewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(getActivity(), "Test data successfully saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Error saving test data", Toast.LENGTH_SHORT).show();
                }
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testId = Integer.parseInt(testIdEditText.getText().toString());
                patientId = Integer.parseInt(patientIdEditText.getText().toString());
                nurseId = Integer.parseInt(nurseIdEditText.getText().toString());
                Test testData = new Test();
                testData.setTestId(testId);
                testData.setPatientId(patientId);
                testData.setNurseId(nurseId);
                testData.setBPH(bphEditText.getText().toString());
                testData.setBPL(bplEditText.getText().toString());
                testData.setTemperature(temperatureEditText.getText().toString());
                testData.setRightVision(rightVisionEditText.getText().toString());
                testData.setLeftVision(leftVisionEditText.getText().toString());
                viewModel.insert(testData);
            }
        });

        return rootView;
    }
}
