package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Remind> allReminders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allReminders = new ArrayList<Remind>();
        Remind test = new Remind("Testing", "3:00", "12:00");
        allReminders.add(test);

        MyAdapter adapter = new MyAdapter(this, allReminders);
        ListView listView = findViewById(R.id.reminderListView);
        listView.setAdapter(adapter);
    }

    public void toEditScreen(View view, ArrayList<Remind> reminders, int num) {
        Intent intent = new Intent(this, EditReminder.class);
        intent.putExtra("reminder", reminders.get(num));
        startActivity(intent);
    }

    public class MyAdapter extends BaseAdapter {

        private ArrayList<Remind> theData;
        private Context context;

        public MyAdapter(Context context, ArrayList<Remind> list){
            this.context = context;
            theData = list;
        }

        public int getCount(){
            return theData.size();
        }

        public void addItem(Remind item){
            theData.add(item);
        }

        public String getItem(int itemNum){
            return "temp";
        }

        public long getItemId(int itemIndex){
            return 0;
        }

        public View getView(final int position, View convertView, ViewGroup parent){
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.existing_reminder, parent, false);
            }

            TextView message = (TextView) convertView.findViewById(R.id.message);
            TextView alarmTime = (TextView) convertView.findViewById(R.id.alarmTime);
            Button editBtn = (Button) convertView.findViewById(R.id.editBtn);

            message.setText(theData.get(position).getMessage());
            alarmTime.setText(theData.get(position).getAlarmTime());

            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toEditScreen(view, theData, position);
                }
            });

            return convertView;
        }

    }
}