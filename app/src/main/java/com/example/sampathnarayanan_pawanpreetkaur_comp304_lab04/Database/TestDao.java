package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TestDao  {

    @Insert
    void insertDetails(Test data);

    @Query("select * from TestDetails")
    LiveData<List<Test>> getAllTests();

    @Query("delete from TestDetails")
    void deleteAllData();

    @Query("select * from TESTDETAILS where TestId = :testId")
    LiveData<Test> getSelectedTest(int testId);
}
