package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.Nurse;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Repository.NurseRepository;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {

    private NurseRepository repository;
    private LiveData<List<Nurse>> getAllData;
    private LiveData<Integer> insertResult;

    public LoginViewModel(@NonNull Application application) {
        super(application);

        repository = new NurseRepository(application);
        getAllData = repository.getAllData();
        insertResult = repository.getInsertResult();
    }

    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    public void insert(Nurse data) {
        repository.insertData(data);
    }

    public LiveData<List<Nurse>> getGetAllData() {
        return getAllData;
    }

}

