package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.NurseDao;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.NurseDatabase;
import com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database.Nurse;

import java.util.List;

public class NurseRepository {

    private NurseDao nurseDao;
    private LiveData<List<Nurse>> allData;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();

    public NurseRepository(Application application) {

        NurseDatabase db = NurseDatabase.getDatabase(application);
        nurseDao = db.nurseDao();
        allData = nurseDao.getDetails();

    }

    public void deleteData() {
        nurseDao.deleteAllData();
    }

    public LiveData<List<Nurse>> getAllData() {
        return allData;
    }

    public void insertData(Nurse data) {
        try {
            new NurseInsert(nurseDao).execute(data);
            insertResult.postValue(1);
        } catch (Exception e) {
            insertResult.postValue(0);
        }
    }

    public LiveData<Integer> getInsertResult(){
        return insertResult;
    }
    private static class NurseInsert extends AsyncTask<Nurse, Void, Void> {

        private NurseDao nurseDao1;

        private NurseInsert(NurseDao loginDao) {

            this.nurseDao1 = loginDao;

        }

        @Override
        protected Void doInBackground(Nurse... data) {

            nurseDao1.deleteAllData();

            nurseDao1.insertDetails(data[0]);
            return null;

        }

    }

}