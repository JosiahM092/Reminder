package com.example.reminder;

import android.view.View;

import java.io.Serializable;

public class Remind implements Serializable {
    private static final long serialVersionUID = -558553967080513790L;



    private String message;
    private String alarmTime;
    private String alarmDate;
    private int deleteIconVisibility;

    public Remind(){
        deleteIconVisibility = View.GONE;
    }

    public Remind(String m, String ad, String at){
        message = m;
        deleteIconVisibility = View.GONE;
        alarmDate = ad;
        alarmTime = at;
    }

    public String getMessage(){
        return message;
    }
    public String getAlarmTime(){
        return alarmTime;
    }
    public String getAlarmDate(){
        return alarmDate;
    }
    public int getDeleteIconVisibility() {
        return deleteIconVisibility;
    }

    public void setDeleteIconVisibility(int deleteIconVisibility) {
        this.deleteIconVisibility = deleteIconVisibility;
    }
    public void setMessage (String m){message = m;}
    public void setAlarmDate (String ad) {alarmDate = ad;}
    public void setAlarmTime (String at) {alarmTime = at;}



}
