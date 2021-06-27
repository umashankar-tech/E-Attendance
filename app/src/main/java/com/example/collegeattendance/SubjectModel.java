package com.example.collegeattendance;

public class SubjectModel {
    String batch ,semester,stream,subjectcode,subjectname,year;
    public  SubjectModel(){

    }

    public SubjectModel(String batch, String semester, String stream, String subjectcode, String subjectname, String year) {
        this.batch = batch;
        this.semester = semester;
        this.stream = stream;
        this.subjectcode = subjectcode;
        this.subjectname = subjectname;
        this.year = year;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getSubjectcode() {
        return subjectcode;
    }

    public void setSubjectcode(String subjectcode) {
        this.subjectcode = subjectcode;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
