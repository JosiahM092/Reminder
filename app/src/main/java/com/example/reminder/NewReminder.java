package com.example.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

public class NewReminder extends AppCompatActivity {

    //DatePicker
    DatePicker datePicker;
    Button datePickerBtn;

    //TimePicker
    TimePicker timePicker;
    Button timePickerBtn;

    //Message
    EditText editTextMessage;
    Button messageBtn;

    //Confirm Remind
    Button setRemindBtn;
    TextView datePickerRemind;
    Button timePickerRemind;

    //Remind Object
    Remind temp;


    //Make Object to store dates and time



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_reminder);
        temp = new Remind();

        setRemindBtn = (Button)findViewById(R.id.setRemindBtn);

        //Message

        editTextMessage = (EditText)findViewById(R.id.editTextMessage);
        messageBtn = (Button)findViewById(R.id.messageBtn);
        messageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp.setMessage( editTextMessage.getText().toString());
                messageBtn.setVisibility(View.GONE);
                editTextMessage.setVisibility(View.GONE);

                datePicker.setVisibility(View.VISIBLE);
                datePickerBtn.setVisibility(View.VISIBLE);
            }
        });

        //DatePicker

        datePicker =(DatePicker)findViewById(R.id.datePicker);
        datePickerBtn = (Button)findViewById(R.id.datePickerBtn);
        datePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               temp.setAlarmDate(datePicker.getMonth() + "/" + datePicker.getDayOfMonth() + "/" + datePicker.getYear());

               datePicker.setVisibility(View.GONE);
               datePickerBtn.setVisibility(View.GONE);
               timePicker.setVisibility(View.VISIBLE);
               timePickerBtn.setVisibility(View.VISIBLE);
            }
        });



        //TimePicker


        timePicker = (TimePicker)findViewById(R.id.timePicker);
        timePickerBtn = (Button)findViewById(R.id.timePickerBtn);
        datePickerRemind = (TextView)findViewById(R.id.datePickerRemind);
        timePickerRemind = (Button  ) findViewById(R.id.timePickerRemind);
        timePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(timePicker.getMinute() < 10){
                    temp.setAlarmTime(timePicker.getHour() + ":" + "0" + timePicker.getMinute());
                }
                else {
                    temp.setAlarmTime(timePicker.getHour() + ":" + timePicker.getMinute());
                }

                timePicker.setVisibility(View.GONE);
                timePickerBtn.setVisibility(View.GONE);
                editTextMessage.setVisibility(View.VISIBLE);
                setRemindBtn.setVisibility(View.VISIBLE);
                datePickerRemind.setVisibility(View.VISIBLE);
                timePickerRemind.setVisibility(View.VISIBLE);

                datePickerRemind.setText(temp.getAlarmDate() + " at " + temp.getAlarmTime());
                //broke here
            }
        });

    }

}
