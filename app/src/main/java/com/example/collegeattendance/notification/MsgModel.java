package com.example.collegeattendance.notification;

public class MsgModel {
    String msgDate,msgContent;

    public MsgModel() {

    }

    public MsgModel(String msgDate, String msgContent) {
        this.msgDate = msgDate;
        this.msgContent = msgContent;
    }

    public String getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(String msgDate) {
        this.msgDate = msgDate;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }


}
