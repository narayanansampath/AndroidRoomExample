package com.example.sampathnarayanan_pawanpreetkaur_comp304_lab04.Database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "TestDetails")
public class Test {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "PatientId")
    private int patientId;

    @ColumnInfo(name = "TestId")
    private int testId;

    @ColumnInfo(name = "NurseId")
    private int nurseId;

    @ColumnInfo(name = "BPL")
    private String BPL;

    @ColumnInfo(name = "BPH")
    private String BPH;

    @ColumnInfo(name = "VisionLeft")
    private String leftVision;

    @ColumnInfo(name = "VisionRight")
    private String rightVision;

    @ColumnInfo(name = "Temperature")
    private String temperature;

    public Test() {

    }

    public Test(int testId, int patientId, int nurseId, String bpl, String bph, String leftVision, String rightVision, String temperature) {

        this.testId=testId;
        this.patientId=patientId;
        this.nurseId = nurseId;

        this.BPL = bpl;
        this.BPH = bph;
        this.leftVision = leftVision;
        this.rightVision = rightVision;
        this.temperature = temperature;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }


    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getBPL() {
        return BPL;
    }

    public void setBPL(String BPL) {
        this.BPL = BPL;
    }

    public String getBPH() {
        return BPH;
    }

    public void setBPH(String BPH) {
        this.BPH = BPH;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getLeftVision() {
        return leftVision;
    }

    public void setLeftVision(String leftVision) {
        this.leftVision = leftVision;
    }

    public String getRightVision() {
        return rightVision;
    }

    public void setRightVision(String rightVision) {
        this.rightVision = rightVision;
    }
}
