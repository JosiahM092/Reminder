package com.example.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.Calendar;

public class EditReminder extends AppCompatActivity {

    int position;
    Context theContext;
    ImageView alarmIcon;
    EditText remindText;
    TextView remindActivateTime;
    DatePicker changeDatePicker;
    TimePicker changeTimePicker;
    AlarmManager alarm;
    Calendar calendar;
    PendingIntent pendingIntent;
    Button confirmTimeBtn, changeTimeBtn, saveChanges, confirmDateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_reminder);
        Intent i = getIntent();
        theContext = getApplicationContext();
        position = (int)i.getSerializableExtra("position");
        calendar = Calendar.getInstance();

        remindText = findViewById(R.id.remindText);
        remindActivateTime = findViewById(R.id.remindActivateTime);
        changeTimeBtn = findViewById(R.id.changeTimeBtn);
        saveChanges = findViewById(R.id.saveChanges);
        changeDatePicker = findViewById(R.id.changeDatePicker);
        confirmDateBtn = findViewById(R.id.confirmDateBtn);
        changeTimePicker = findViewById(R.id.changeTimePicker);
        confirmTimeBtn = findViewById(R.id.confirmTimeBtn);
        alarmIcon = findViewById(R.id.alarmIcon);

        remindText.setText(AllReminders.getInstance().getArray().get(position).getMessage());
        remindActivateTime.setText(AllReminders.getInstance().getArray().get(position).getAlarmDate() + " at " + AllReminders.getInstance().getArray().get(position).getAlarmTime());
        remindText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                AllReminders.getInstance().getArray().get(position).setMessage(remindText.getText().toString());
            }
        });


        changeTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remindText.setVisibility(View.INVISIBLE);
                remindActivateTime.setVisibility(View.INVISIBLE);
                changeTimeBtn.setVisibility(View.INVISIBLE);
                saveChanges.setVisibility(View.INVISIBLE);
                alarmIcon.setVisibility(View.INVISIBLE);


                changeDatePicker.setVisibility(View.VISIBLE);
                confirmDateBtn.setVisibility(View.VISIBLE);
            }
        });

        confirmDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllReminders.getInstance().getArray().get(position).setAlarmDate(changeDatePicker.getMonth() + "/" + changeDatePicker.getDayOfMonth() + "/" + changeDatePicker.getYear());
                calendar.set(Calendar.YEAR, changeDatePicker.getYear());
                calendar.set(Calendar.MONTH, changeDatePicker.getMonth());
                calendar.set(Calendar.DAY_OF_MONTH, changeDatePicker.getDayOfMonth());
                changeDatePicker.setVisibility(View.GONE);
                confirmDateBtn.setVisibility(View.GONE);

                changeTimePicker.setVisibility(View.VISIBLE);
                confirmTimeBtn.setVisibility(View.VISIBLE);

            }
        });

        confirmTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllReminders.getInstance().getArray().get(position).setAlarmTime(changeTimePicker.getHour() + ":" + changeTimePicker.getMinute());
                calendar.set(Calendar.HOUR_OF_DAY, changeTimePicker.getHour());
                calendar.set(Calendar.MINUTE, changeTimePicker.getMinute());
                calendar.set(Calendar.SECOND, 0);
                changeTimePicker.setVisibility(View.GONE);
                confirmTimeBtn.setVisibility(View.GONE);

                remindActivateTime.setText(AllReminders.getInstance().getArray().get(position).getAlarmDate() + " at " + AllReminders.getInstance().getArray().get(position).getAlarmTime());
                remindText.setVisibility(View.VISIBLE);
                remindActivateTime.setVisibility(View.VISIBLE);
                changeTimeBtn.setVisibility(View.VISIBLE);
                saveChanges.setVisibility(View.VISIBLE);

            }
        });

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AllReminders.getInstance().saveToInternalStorage(theContext);
                Intent intent = new Intent(theContext, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
