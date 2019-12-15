package com.example.reminder;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AllReminders {
    private static AllReminders mInstance;
    private ArrayList<Remind> list = new ArrayList<Remind>();

    public static AllReminders getInstance() {
        if(mInstance == null)
            mInstance = new AllReminders();

        return mInstance;
    }
    private AllReminders() {
        list = new ArrayList<Remind>();
    }
    // retrieve array from anywhere
    public ArrayList<Remind> getArray() {
        return this.list;
    }
    //Add element to array
    public void addToArray(Remind value) {
        list.add(value);
    }

    public void overide(ArrayList<Remind> temp){
        list = temp;
    }

    public void removeAllDeleteIcons(){
        for(int i = 0; i < list.size(); i++){
            list.get(i).setDeleteIconVisibility(View.GONE);
        }
    }

    public void saveToInternalStorage(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput("all", Context.MODE_PRIVATE);
            ObjectOutputStream of = new ObjectOutputStream(fos);
            of.writeObject(list);
            of.flush();
            of.close();
            fos.close();
        } catch (Exception e) {
            Log.e("InternalStorage", e.getMessage());
        }
    }
}
