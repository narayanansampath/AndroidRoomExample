package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.PatientDatabase;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.Test;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.TestDao;

import java.util.List;

public class TestRepository {
    private final TestDao testDao;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Test>> testList;
    private LiveData<List<Test>> selectedTest;
    //
    public TestRepository(Context context) {
        //create a database object
        PatientDatabase db = PatientDatabase.getInstance(context);
        //create an interface object
        testDao = db.testDao();
        //call interface method
        testList = testDao.getAllTests();
        //selectedPatient = patientDao.getSelectedPatient(1); // <<<<< we need to pass the selected Patient's ID value from the GUI
    }
    // returns query results as LiveData object
    public LiveData<List<Test>> getAllTest() {
        return testList;
    }
    public LiveData<List<Test>> getSelectedTest() {
        return selectedTest;
    }
    //inserts a person asynchronously
    public void insert(Test test) {
        insertAsync(test);
    }
    // returns insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    private void insertAsync(final Test test) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    testDao.insertDetails(test);
                    insertResult.postValue(1);
                } catch (Exception e) {
                    insertResult.postValue(0);
                }
            }
        }).start();
    }
}
