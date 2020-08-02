package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Nurse.class}, version = 1, exportSchema = false)
public abstract class NurseDatabase extends RoomDatabase {

    public abstract NurseDao nurseDao();

    private static NurseDatabase INSTANCE;

    public static NurseDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {

            synchronized (NurseDatabase.class) {

                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(
                            context, NurseDatabase.class, "NURSE_DATABASE")
                            .fallbackToDestructiveMigration()
                            .build();

                }

            }

        }

        return INSTANCE;

    }

}
