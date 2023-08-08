package com.example.chatapp.models;

public class chatmodel {
    String message,userid,messageid;
    long timestamp;

    public chatmodel(String message, String userid, String messageid, long timestamp) {
        this.message = message;
        this.userid = userid;
        this.messageid = messageid;
        this.timestamp = timestamp;
    }

    public chatmodel(String message, String userid) {
        this.message = message;
        this.userid = userid;


    }
public chatmodel(){

}
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }


    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}




