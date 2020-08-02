package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NurseDao {

    @Insert
    void insertDetails(Nurse data);

    @Query("select * from Nurse")
    LiveData<List<Nurse>> getDetails();

    @Query("delete from Nurse")
    void deleteAllData();

}