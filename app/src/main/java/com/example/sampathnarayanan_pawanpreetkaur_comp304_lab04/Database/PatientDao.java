package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

// this interface declares database functions
// and does the mapping of SQL queries to functions
@Dao
public interface PatientDao {
    //
   // @Insert
    //void insert(Patient person);


    //Monitoring Query Result Changes with Live Data
    @Query("select * from Patient_table ")
    LiveData<List<Patient>> getAllPatient();

    //@Query("select * from Patient where PatientId = ':PatientId' order by Fname")
    //LiveData<List<Patient>> getSelectedPatient(int PatientId);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Patient person);

    @Query("DELETE FROM Patient_table")
    void deleteAll();

   // @Query("SELECT * from Patient_table ORDER BY Lname ASC")
   // List<Patient> getAlphabetizedWords();


}