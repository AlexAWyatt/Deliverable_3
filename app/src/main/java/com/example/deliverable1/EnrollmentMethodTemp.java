package com.example.deliverable1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

public class EnrollmentMethodTemp {

    Bundle extras;
    // remember to put username into bundle!
    // bundle.putString("username", username); (into the openViewClassDialog() method)

    ClassDatabase classDatabase;


    public long enroll() {
        classDatabase = MainActivity.getClassDatabase();
        SQLiteDatabase db = classDatabase.getWritableDatabase();

        Bundle bundle = getArguments();
        ArrayList<String> items = bundle.getStringArrayList("items");

        ContentValues values = new ContentValues();
        values.put("username", bundle.getString("username"));
        values.put("classType", items.get(1));
        values.put("instructorName", items.get(0));
        values.put("classDays", items.get(2));
        values.put("classHours", items.get(3));
        values.put("classDiff", items.get(4));
        values.put("classCap", items.get(5));
        values.put("startTime", items.get(6));

        long num = db.insert("enrollment", null, values);

        return num;
        /* Add this below part to the dialogue opening class so when the enrollment button is pressed, user will get a message
        about whether enrollment is successful when the dialogue closes (or can do it on the view class page -- so within this method - will depend on implementation of member
        activity page)

        if (num != -1) {
            Toast.makeText(this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
        }*/
    }

    public int unenroll() throws Exception { // this will need to take place on the "view all enrolled classes" page
        classDatabase = MainActivity.getClassDatabase();
        SQLiteDatabase db = classDatabase.getWritableDatabase();

        // assuming there will be a listview with a corresponding "arrayHashes" similar to how it is in instructors
        // will need to get USERNAME to the page where this is called -- should be easy as we will be looking at a specific
        // usernames enrolled classes

        String delClassType = ; // assign classType with relevant command
        String delInstructorName = ; // assign instructorName with relevant command
        String delDay = ; // assign classDays with relevant command

        int numRemoved = db.delete("enrollment", "username = ? AND classType = ? AND instructorName = ? AND classType = ?",
                new String[] {username, delClassType, delInstructorName, delDay});

        arrayHashes.remove(index); // change to relevant command to remove from collection item used to populate listview
        simpleAdapter.notifyDataSetChanged(); // or other relevant command
        listview.setAdapter(simpleAdapter);
        Toast.makeText(/*current class name*/.this, "Successfully Unenrolled from Class", Toast.LENGTH_SHORT).show();

        // TESTING / CHECKING
        if (numRemoved > 1) {
            throw new Exception("More than one class was deleted from the enrollment database!");
        }
        else if (numRemoved == 0) {
            throw new Exception("There were no classes removed from the enrollment database!");
        }

        return numRemoved;
    }
}
