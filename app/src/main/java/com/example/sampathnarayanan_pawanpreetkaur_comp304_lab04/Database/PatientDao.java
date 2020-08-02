package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// this interface declares database functions
// and does the mapping of SQL queries to functions
@Dao
public interface PatientDao {

    //Monitoring Query Result Changes with Live Data
    @Query("select * from Patient_table ")
    LiveData<List<Patient>> getAllPatient();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Patient person);

    @Update
    void update(Patient person);

    @Query("DELETE FROM Patient_table")
    void deleteAll();

    @Query("select * from Patient_table where PatientId = :pId")
    LiveData<Patient> getselectedPatient(int pId);

    // @Query("SELECT * from Patient_table ORDER BY Lname ASC")
   // List<Patient> getAlphabetizedWords();


}