package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Nurse")
public class Nurse {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "nurseId")
    private int nurseId;

    @ColumnInfo(name = "FirstName")
    private String firstName;

    @ColumnInfo(name = "LastName")
    private String lastName;

    @ColumnInfo(name = "Department")
    private String department;

    @ColumnInfo(name = "Password")
    private String Password;


    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int NurseId) {
        this.nurseId = NurseId;
    }

    public String getFirstName() {return firstName;}

    public void setFirstName(String FirstName) {firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String LastName) {lastName = lastName;}

    public String getDepartment() {return department;}

    public void setDepartment(String Department) {department= department;}

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
