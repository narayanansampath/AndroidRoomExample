package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.Nurse;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Repository.NurseRepository;

import java.util.List;

public class NurseViewModel extends AndroidViewModel {
    // calling repository tasks and
    // sending the results to the Activity
    private NurseRepository nurseRepository;
    private LiveData<Integer> insertResult;
    private LiveData<List<Nurse>> allNurse;
    //
    public NurseViewModel(@NonNull Application application) {
        super(application);
        nurseRepository = new NurseRepository(application);
        insertResult = nurseRepository.getInsertResult();
        allNurse = nurseRepository.getAllData();
    }
    //calls repository to insert a patient
    public void insert(Nurse nurse) {
        nurseRepository.insertData(nurse);
    }
    //gets insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }
    //returns query results as live data object
    LiveData<List<Nurse>> getAllPatient() { return allNurse; }
}
