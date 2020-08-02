package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.Patient;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Repository.PatientRepository;

import java.util.List;

//provides data to the UI and acts as a communication center
// between the Repository and the UI.
public class PatientViewModel extends AndroidViewModel {
    // calling repository tasks and
    // sending the results to the Activity
    private PatientRepository patientRepository;
    private LiveData<Integer> insertResult;
    private LiveData<List<Patient>> allPatient;
    //
    public PatientViewModel(@NonNull Application application) {
        super(application);
        patientRepository = new PatientRepository(application);
        insertResult = patientRepository.getInsertResult();
        allPatient = patientRepository.getAllPatient();
    }
    //calls repository to insert a patient
    public void insert(Patient patient) {
        patientRepository.insert(patient);
    }
    //gets insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }
    //returns query results as live data object
    LiveData<List<Patient>> getAllPatient() { return allPatient; }

}