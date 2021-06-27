package com.example.collegeattendance;

public class StudentModel {
    String batch;
    String dob;
    String name;
    String rollno;

   boolean isSelected;



    public  StudentModel(){

    }

    public StudentModel(String batch, String dob, String name, String rollno,boolean isSelected) {
        this.batch = batch;
        this.dob = dob;
        this.name = name;
        this.rollno = rollno;
        this.isSelected = isSelected;
    }


    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }
    public boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
