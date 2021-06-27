package com.example.collegeattendance.studentpart;

public class pdfModel {
    String year;
    String batch;
    String fileName;
    String filePath;
    String semester;
    String stream;

    public pdfModel() {
    }

    public pdfModel(String year, String batch, String fileName, String filePath, String semester, String stream) {
        this.year = year;
        this.batch = batch;
        this.fileName = fileName;
        this.filePath = filePath;
        this.semester = semester;
        this.stream = stream;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
}