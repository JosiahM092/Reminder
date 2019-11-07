package com.example.reminder;

import java.io.Serializable;

public class Remind implements Serializable {

    private String message;

    private String alarmTime;
    private String alarmDate;
    private String timeSet;
    private String dateSet;

    public Remind(String m, String a, String t){
        message = m;
        alarmTime = a;
        timeSet = t;
    }

    public Remind(String m, String ds, String ts, String ad, String at){
        message = m;
        dateSet = ds;
        timeSet = ts;
        alarmDate = ad;
        alarmTime = at;
    }

    public String getMessage(){return message;}
    public String getAlarmTime(){return alarmTime;}
    public String getAlarmDate(){return alarmDate;}
    public String getTimeSet(){return timeSet;}
    public String getDateSet(){return dateSet;}

    public void setMessage (String m){message = m;}
    public void setAlarmDate (String ad) {alarmDate = ad;}
    public void setAlarmTime (String at) {alarmTime = at;}

}
