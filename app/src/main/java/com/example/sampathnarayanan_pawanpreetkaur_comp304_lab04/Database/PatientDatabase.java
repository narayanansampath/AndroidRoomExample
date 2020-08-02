package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Patient.class, Test.class}, version = 1)
public abstract class PatientDatabase extends RoomDatabase{
    //
    private static volatile PatientDatabase INSTANCE;
    private static final String DATABASE_NAME="PatientDB";
    public abstract PatientDao patientDao();
    public abstract TestDao testDao();

    //
    public static synchronized PatientDatabase getInstance(Context context){
        if(INSTANCE==null) {
            //Create database object
            synchronized (PatientDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            PatientDatabase.class, "App_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
