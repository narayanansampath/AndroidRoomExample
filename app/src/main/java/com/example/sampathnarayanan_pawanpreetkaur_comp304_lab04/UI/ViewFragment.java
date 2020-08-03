package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.UI;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.Patient;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.Test;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.R;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.ViewModel.PatientViewModel;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.ViewModel.TestViewModel;

import java.util.List;

public class ViewFragment extends Fragment {

    TestViewModel viewModel;
    private Button viewInfoButton;
    private TextView testIdEditText, patientIdEditText, nurseIdEditText, bplEditText, bphEditText, temperatureEditText, leftVisionEditText, rightVisionEditText;
    EditText testId;
    boolean isVisible = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View rootView = inflater.inflate(R.layout.fragment_view, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("View Test Info");  // provide compatibility to all the versions


        patientIdEditText = (TextView) rootView.findViewById(R.id.viewt2);
        testIdEditText = rootView.findViewById(R.id.viewt1);
        nurseIdEditText = rootView.findViewById(R.id.viewt3);
        bplEditText = rootView.findViewById(R.id.viewt4);
        bphEditText = rootView.findViewById(R.id.viewt5);
        temperatureEditText = rootView.findViewById(R.id.viewt6);
        leftVisionEditText = rootView.findViewById(R.id.viewt7);
        rightVisionEditText = rootView.findViewById(R.id.viewt8);
        viewInfoButton = (Button) rootView.findViewById(R.id.viewb1);
        testId = rootView.findViewById(R.id.viewET1);
        viewModel =  ViewModelProviders.of(this).get(TestViewModel.class);

        if(!isVisible) {
            patientIdEditText.setVisibility(View.INVISIBLE);
            testIdEditText.setVisibility(View.INVISIBLE);
            nurseIdEditText.setVisibility(View.INVISIBLE);
            bplEditText.setVisibility(View.INVISIBLE);
            bphEditText.setVisibility(View.INVISIBLE);
            temperatureEditText.setVisibility(View.INVISIBLE);
            leftVisionEditText.setVisibility(View.INVISIBLE);
            rightVisionEditText.setVisibility(View.INVISIBLE);
        }

        viewInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              viewModel.getSpecificTest(Integer.parseInt(testId.getText().toString())).observe(getViewLifecycleOwner(), new Observer<Test>() {
                  @Override
                  public void onChanged(Test test) {
                      if(test != null){
                          isVisible = true;
                          patientIdEditText.setVisibility(View.VISIBLE);
                          testIdEditText.setVisibility(View.VISIBLE);
                          nurseIdEditText.setVisibility(View.VISIBLE);
                          bplEditText.setVisibility(View.VISIBLE);
                          bphEditText.setVisibility(View.VISIBLE);
                          temperatureEditText.setVisibility(View.VISIBLE);
                          leftVisionEditText.setVisibility(View.VISIBLE);
                          rightVisionEditText.setVisibility(View.VISIBLE);
                          patientIdEditText.setText("Patient ID:    " + String.valueOf(test.getPatientId()));
                          testIdEditText.setText("Test ID:         " + String.valueOf(test.getTestId()));
                          nurseIdEditText.setText("Nurse ID:     " + String.valueOf(test.getNurseId()));
                          bplEditText.setText("BPL:              " + test.getBPL());
                          bphEditText.setText("BPH:              " + test.getBPH());
                          temperatureEditText.setText("Temperature:" + test.getTemperature());
                          leftVisionEditText.setText("left vision:      " + test.getLeftVision());
                          rightVisionEditText.setText("right Vision:    " + test.getRightVision());
                      } else {
                          patientIdEditText.setVisibility(View.INVISIBLE);
                          testIdEditText.setVisibility(View.INVISIBLE);
                          nurseIdEditText.setVisibility(View.INVISIBLE);
                          bplEditText.setVisibility(View.INVISIBLE);
                          bphEditText.setVisibility(View.INVISIBLE);
                          temperatureEditText.setVisibility(View.INVISIBLE);
                          leftVisionEditText.setVisibility(View.INVISIBLE);
                          rightVisionEditText.setVisibility(View.INVISIBLE);
                          Toast.makeText(getActivity(), "No data found for this test ID", Toast.LENGTH_SHORT).show();
                      }
                  }
              });
            }
        });

         return rootView;
    }
}
