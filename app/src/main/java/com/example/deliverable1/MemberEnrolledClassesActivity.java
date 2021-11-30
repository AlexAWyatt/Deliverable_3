package com.example.deliverable1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MemberEnrolledClassesActivity extends AppCompatActivity {

    ListView enrolledClassesList;

    String user;

    SimpleAdapter adapter;

    ArrayList<HashMap<String, String>> arrayHashes;

    ClassDatabase classDatabase;
    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_member_enrolled_classes);

        enrolledClassesList = findViewById(R.id.Listview_enrolledClasses);

        arrayHashes = new ArrayList<HashMap<String, String>>();

        user = this.getIntent().getStringExtra("username");

        adapter = new SimpleAdapter(this, arrayHashes, R.layout.list_item4, new String[]{"classType", "instructorName", "classDays"}, new int[]{R.id.text1111, R.id.text2222, R.id.text3333});

        classDatabase = MainActivity.getClassDatabase();

        db = classDatabase.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from enrollment WHERE username = ?", new String[]{user});

        String instructorName;
        String className;
        String classDay;

        if (cursor.moveToFirst()){

            while (!cursor.isAfterLast()){

                instructorName = cursor.getString(2);
                className = cursor.getString(3);
                classDay = cursor.getString(4);

                HashMap<String, String> resultsMap = new HashMap<>();
                resultsMap.put("classType", className);
                resultsMap.put("instructorName", instructorName);
                resultsMap.put("classDays", classDay);

                arrayHashes.add(resultsMap);

                cursor.moveToNext();
            }

            enrolledClassesList.setAdapter(adapter);
        }
        cursor.close();


        enrolledClassesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });


    }
}
