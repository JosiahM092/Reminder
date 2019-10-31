package com.example.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditReminder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_reminder_page);

        Intent i = getIntent();
        final Remind savedReminder = (Remind)i.getSerializableExtra("reminder");

        TextView remindTimeMade = (TextView)findViewById(R.id.remindTimeMade);
        remindTimeMade.setText("Time set: " + savedReminder.getTimeSet());

        final EditText remindText = (EditText)findViewById(R.id.remindText);
        remindText.setText(savedReminder.getMessage());

        TextView remindActivateTime = (TextView)findViewById(R.id.remindActivateTime);
        remindActivateTime.setText("Current alarm time: " + savedReminder.getAlarmTime());

        Button changeTimeBtn = (Button)findViewById(R.id.changeTimeBtn);

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

    }
}
