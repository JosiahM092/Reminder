package com.example.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

public class EditReminder extends AppCompatActivity {

    private Remind savedReminder;
    private TextView remindTimeMade;
    private EditText remindText;
    private TextView remindActivateTime;
    private Button changeTimeBtn;

    private DatePicker changeDatePicker;
    private Button confirmDateBtn;
    private TimePicker changeTimePicker;
    private Button confirmTimeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_reminder_page);
        Intent i = getIntent();
        savedReminder = (Remind)i.getSerializableExtra("reminder");

        remindTimeMade = findViewById(R.id.remindTimeMade);
        remindText = findViewById(R.id.remindText);
        remindActivateTime = findViewById(R.id.remindActivateTime);
        changeTimeBtn = findViewById(R.id.changeTimeBtn);

        changeDatePicker = findViewById(R.id.changeDatePicker);
        confirmDateBtn = findViewById(R.id.confirmDateBtn);
        changeTimePicker = findViewById(R.id.changeTimePicker);
        confirmTimeBtn = findViewById(R.id.confirmTimeBtn);

        remindText.setText(savedReminder.getMessage());
        remindActivateTime.setText("Current alarm time: " + savedReminder.getAlarmTime());
        remindTimeMade.setText("Time set: " + savedReminder.getTimeSet());
        remindText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                savedReminder.setMessage(remindText.getText().toString());
            }
        });


        changeTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remindTimeMade.setVisibility(View.INVISIBLE);
                remindText.setVisibility(View.INVISIBLE);
                remindActivateTime.setVisibility(View.INVISIBLE);
                changeTimeBtn.setVisibility(View.INVISIBLE);

                changeDatePicker.setVisibility(View.VISIBLE);
                confirmDateBtn.setVisibility(View.VISIBLE);
                confirmDateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        savedReminder.setAlarmDate(changeDatePicker.getMonth() + " " + changeDatePicker.getDayOfMonth() + ", " + changeDatePicker.getYear());
                        changeDatePicker.setVisibility(View.GONE);
                        confirmDateBtn.setVisibility(View.GONE);

                        changeTimePicker.setVisibility(View.VISIBLE);
                        confirmTimeBtn.setVisibility(View.VISIBLE);

                        confirmTimeBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                savedReminder.setAlarmTime(changeTimePicker.getHour() + ":" + changeTimePicker.getMinute());
                                changeTimePicker.setVisibility(View.GONE);
                                confirmTimeBtn.setVisibility(View.GONE);

                                remindActivateTime.setText("Current alarm time: " + savedReminder.getAlarmTime());
                                remindTimeMade.setVisibility(View.VISIBLE);
                                remindText.setVisibility(View.VISIBLE);
                                remindActivateTime.setVisibility(View.VISIBLE);
                                changeTimeBtn.setVisibility(View.VISIBLE);

                            }
                        });
                    }
                });
            }
        });
    }
}
