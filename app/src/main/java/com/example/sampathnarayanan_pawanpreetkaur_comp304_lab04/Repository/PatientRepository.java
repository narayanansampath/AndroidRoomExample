package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.PatientDatabase;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.Patient;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.PatientDao;

import java.util.List;

//a class for managing multiple data sources
//
public class PatientRepository {
    private final PatientDao patientDao;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Patient>> patientList;
    private LiveData<List<Patient>> selectedPatient;
    //
    public PatientRepository(Context context) {
        //create a database object
        PatientDatabase db = PatientDatabase.getInstance(context);
        //create an interface object
        patientDao = db.patientDao();
        //call interface method
        patientList = patientDao.getAllPatient();
        //selectedPatient = patientDao.getSelectedPatient(1); // <<<<< we need to pass the selected Patient's ID value from the GUI
    }
    // returns query results as LiveData object
    public LiveData<List<Patient>> getAllPatient() {
        return patientList;
    }
    public LiveData<List<Patient>> getSelectedPatient() {
        return selectedPatient;
    }
    //inserts a person asynchronously
    public void insert(Patient patient) {
        insertAsync(patient);
    }
    // returns insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    private void insertAsync(final Patient patient) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    patientDao.insert(patient);
                    insertResult.postValue(1);
                } catch (Exception e) {
                    insertResult.postValue(0);
                }
            }
        }).start();
    }
}
