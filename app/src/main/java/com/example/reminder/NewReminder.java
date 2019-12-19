package com.example.reminder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

public class NewReminder extends AppCompatActivity {

    Context theContext;
    DatePicker datePicker;
    TimePicker timePicker;
    EditText editTextMessage;
    TextView setTimeTV;
    ImageView alarmIcon;
    Button changeTimeNRBtn, datePickerBtn, timePickerBtn, messageBtn, setRemindBtn;

    Remind temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_reminder);
        temp = new Remind();
        theContext = getApplicationContext();

        setRemindBtn = findViewById(R.id.setRemindBtn);
        alarmIcon = findViewById(R.id.alarmIcon);

        //Message

        editTextMessage = findViewById(R.id.editTextMessage);
        messageBtn = findViewById(R.id.messageBtn);
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

        datePicker = findViewById(R.id.datePicker);
        datePickerBtn = findViewById(R.id.datePickerBtn);
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


        timePicker = findViewById(R.id.timePicker);
        timePickerBtn = findViewById(R.id.timePickerBtn);
        setTimeTV = findViewById(R.id.setTimeTV);
        changeTimeNRBtn = findViewById(R.id.changeTimeNRBtn);
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
                setTimeTV.setVisibility(View.VISIBLE);
                alarmIcon.setVisibility(View.VISIBLE);
                changeTimeNRBtn.setVisibility(View.VISIBLE);

                setTimeTV.setText(temp.getAlarmDate() + " at " + temp.getAlarmTime());
            }
        });

        changeTimeNRBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextMessage.setVisibility(View.INVISIBLE);
                setRemindBtn.setVisibility(View.INVISIBLE);
                setTimeTV.setVisibility(View.INVISIBLE);
                alarmIcon.setVisibility(View.INVISIBLE);
                changeTimeNRBtn.setVisibility(View.INVISIBLE);
                datePicker.setVisibility(View.VISIBLE);
                datePickerBtn.setVisibility(View.VISIBLE);
            }
        });

        editTextMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                temp.setMessage(editTextMessage.getText().toString());
            }
        });


        setRemindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllReminders.getInstance().addToArray(temp);
                AllReminders.getInstance().saveToInternalStorage(theContext);
                Intent intent = new Intent(theContext, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
