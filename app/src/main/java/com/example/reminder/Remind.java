package com.example.reminder;

import android.view.View;

import java.io.Serializable;

public class Remind implements Serializable {
    private static final long serialVersionUID = -558553967080513790L;

    private String message;
    private String mAlarmTime;
    private String nAlarmTime;
    private String alarmDate;
    private int deleteIconVisibility;

    public Remind(){
        deleteIconVisibility = View.GONE;
    }

    public String getMessage(){
        return message;
    }
    public String getAlarmTime(){
        return nAlarmTime;
    }
    public String getAlarmDate(){
        return alarmDate;
    }
    public String getMAlarmTime(){
        return mAlarmTime;
    }
    public int getDeleteIconVisibility() {
        return deleteIconVisibility;
    }

    public void setDeleteIconVisibility(int deleteIconVisibility) {
        this.deleteIconVisibility = deleteIconVisibility;
    }
    public void setMessage (String m){
        message = m;
    }
    public void setAlarmDate (String ad) { alarmDate = ad; }
    public void setAlarmTime (String at) {
        mAlarmTime = at;
        if(Integer.parseInt(at.substring(0, at.indexOf(":"))) > 12) nAlarmTime = Integer.parseInt(at.substring(0, at.indexOf(":"))) - 12 + at.substring(at.indexOf(":")) + "pm";
        else nAlarmTime = at + "am";
    }
}
