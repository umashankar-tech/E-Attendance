package com.example.collegeattendance;

public class BatchModel {
    String batch;
    String batchDesc;
    String stream;
    String getCurrentStream;
    public BatchModel(){

    }

    public BatchModel(String batch, String batchDesc, String stream,String getCurrentStream) {
        this.batch = batch;
        this.batchDesc = batchDesc;
        this.stream = stream;
        this.getCurrentStream = getCurrentStream;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getBatchDesc() {
        return batchDesc;
    }

    public void setBatchDesc(String batchDesc) {
        this.batchDesc = batchDesc;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }
    public String getGetCurrentStream() {
        return getCurrentStream;
    }

    public void setGetCurrentStream(String getCurrentStream) {
        this.getCurrentStream = getCurrentStream;
    }
}




