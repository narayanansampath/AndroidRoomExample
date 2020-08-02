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
    }
    // returns query results as LiveData object
    public LiveData<List<Patient>> getAllPatient() {
        return patientList;
    }
    public LiveData<Patient> getSelectedPatient(int patientId) {
        return patientDao.getselectedPatient(patientId);
    }
    //inserts a person asynchronously
    public void insert(Patient patient) {
        insertAsync(patient);
    }

    public void update(Patient patient) {
        updateAsync(patient);
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

    private void updateAsync(final Patient patient) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    patientDao.update(patient);
                    insertResult.postValue(1);
                } catch (Exception e) {
                    insertResult.postValue(0);
                }
            }
        }).start();
    }
}
