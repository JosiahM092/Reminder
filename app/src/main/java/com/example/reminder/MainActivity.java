package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    Context theContext;
    ListView listView;
    MyAdapter adapter;
    TextView message, alarmTime;
    ImageView addNewIcon, deleteIcon;

    ArrayList<Remind> temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        theContext = getApplicationContext();


        temp = new ArrayList<Remind>();
        AllReminders.getInstance().overide(readFromInternalStorage());
        AllReminders.getInstance().removeAllDeleteIcons();



        adapter = new MyAdapter(this, AllReminders.getInstance().getArray());
        listView = findViewById(R.id.reminderListView);
        listView.setAdapter(adapter);

        addNewIcon = findViewById(R.id.add_icon);
        addNewIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AllReminders.getInstance().overide(temp);
                AllReminders.getInstance().removeAllDeleteIcons();
                Intent intent = new Intent(theContext, NewReminder.class);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AllReminders.getInstance().getArray().get(position).setDeleteIconVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
                return true;
            }
        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public ArrayList<Remind> readFromInternalStorage() {
        ArrayList<Remind> toReturn = new ArrayList<Remind>();
        FileInputStream fis;
        try {
            fis = theContext.openFileInput("all");
            ObjectInputStream oi = new ObjectInputStream(fis);
            toReturn = (ArrayList)oi.readObject();
            oi.close();
        } catch (FileNotFoundException e) {
            Log.e("InternalStorage", e.getMessage());
        } catch (IOException e) {
            Log.e("InternalStorage", e.getMessage());
        } catch (ClassNotFoundException e){

        }
        return toReturn;
    }

    public void toEditScreen(int num) {
        AllReminders.getInstance().removeAllDeleteIcons();
        Intent intent = new Intent(this, EditReminder.class);
        intent.putExtra("position", num);
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
            if(theData == null) return 0;
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

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toEditScreen(position);
                }
            });

            message = convertView.findViewById(R.id.message);
            alarmTime = convertView.findViewById(R.id.alarmTime);
            deleteIcon = convertView.findViewById(R.id.delete_icon);
            message.setText(theData.get(position).getMessage());
            alarmTime.setText(theData.get(position).getAlarmDate() + " at " + theData.get(position).getAlarmTime());

            deleteIcon.setVisibility(AllReminders.getInstance().getArray().get(position).getDeleteIconVisibility());
            deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AllReminders.getInstance().getArray().remove(position);
                    adapter.notifyDataSetChanged();
                    AllReminders.getInstance().saveToInternalStorage(context);
                }
            });


            return convertView;
        }

    }
}