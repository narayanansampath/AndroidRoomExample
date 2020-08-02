package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.Test;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Repository.TestRepository;

import java.util.List;

public class TestViewModel extends AndroidViewModel {
    // calling repository tasks and
    // sending the results to the Activity
    private TestRepository testRepository;
    private LiveData<Integer> insertResult;
    private LiveData<List<Test>> allTest;
    //
    public TestViewModel(@NonNull Application application) {
        super(application);
        testRepository = new TestRepository(application);
        insertResult = testRepository.getInsertResult();
        allTest = testRepository.getAllTest();
    }
    //calls repository to insert a patient
    public void insert(Test test) {
        testRepository.insert(test);
    }
    //gets insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }
    //returns query results as live data object
    LiveData<List<Test>> getAllPatient() { return allTest; }
}
