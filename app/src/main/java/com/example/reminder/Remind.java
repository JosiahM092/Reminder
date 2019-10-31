package com.example.reminder;

import java.io.Serializable;

public class Remind implements Serializable {

    private String message;

    //May be another value depending on what the calender class can return
    //Also might be an arraylist if we add the ability to add multiple times
    private String alarmTime;
    private String timeSet;

    public Remind(String m, String a, String t){
        message = m;
        alarmTime = a;
        timeSet = t;
    }

    public String getMessage(){return message;}
    public String getAlarmTime(){return alarmTime;}
    public String getTimeSet(){return timeSet;}

    public void setMessage (String m){message = m;}

}
