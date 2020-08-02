package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database;



import androidx.room.Entity;
import androidx.room.PrimaryKey;

// this is a Room entity class to describe
// a database table
@Entity(tableName = "Patient_table")
public class Patient {

    @PrimaryKey(autoGenerate = true)
    private int PatientId;
    private String FName;
    private String LName;
    private String Dept;
    private String Room;


    public int getPatientId() {
        return PatientId;
    }
    public void setPatientId(int PatientId) {
        this.PatientId = PatientId;
    }

    public String getFName() {
        return FName;
    }
    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }
    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getDept() {
        return Dept;
    }
    public void setDept(String Dept) {
        this.Dept = Dept;
    }

    public String getRoom() {
        return Room;
    }
    public void setRoom(String Room) {
        this.Room = Room;
    }


}