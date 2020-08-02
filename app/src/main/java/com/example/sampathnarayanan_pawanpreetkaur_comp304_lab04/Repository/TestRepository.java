package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.PatientDatabase;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.Test;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.TestDao;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.TestDatabase;

import java.util.List;

public class TestRepository {
    private final TestDao testDao;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Test>> testList;
    private LiveData<List<Test>> selectedTest;
    //
    public TestRepository(Context context) {
        //create a database object
        TestDatabase db = TestDatabase.getDatabase(context);
        //create an interface object
        testDao = db.testDao();
        //call interface method
        testList = testDao.getAllTests();
    }
    // returns query results as LiveData object
    public LiveData<List<Test>> getAllTest() {
        return testList;
    }
    public LiveData<Test> getSelectedTest(int testId)
    {
        return testDao.getSelectedTest(testId);
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
