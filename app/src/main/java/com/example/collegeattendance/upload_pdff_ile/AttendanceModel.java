package com.example.collegeattendance.upload_pdff_ile;

public class AttendanceModel {
    String AttStatus,Date,Name,RollNo,Stream,Subject,Time,Year,Section,Semester;

    public AttendanceModel(String attStatus, String date, String name, String rollNo, String stream, String subject, String time, String year, String section, String semester) {
        AttStatus = attStatus;
        Date = date;
        Name = name;
        RollNo = rollNo;
        Stream = stream;
        Subject = subject;
        Time = time;
        Year = year;
        Section = section;
        Semester = semester;
    }

    public String getAttStatus() {
        return AttStatus;
    }

    public void setAttStatus(String attStatus) {
        AttStatus = attStatus;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRollNo() {
        return RollNo;
    }

    public void setRollNo(String rollNo) {
        RollNo = rollNo;
    }

    public String getStream() {
        return Stream;
    }

    public void setStream(String stream) {
        Stream = stream;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }
}
