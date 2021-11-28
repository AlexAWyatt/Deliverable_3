package com.example.deliverable1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassDatabase extends SQLiteOpenHelper {
    private static final String TAG = "ClassDatabase.db";
    private static final String DATABASE_NAME = "ClassDatabase";

    private static final String KEY_ID = "ID";
    private static final String INSTRUCTOR_CLASSES = "instructorClasses";
    private static final String COLUMN_INSTRUCTOR_NAME = "instructorName";
    private static final String COLUMN_CLASS_TYPE = "classType";
    private static final String COLUMN_CLASS_DAYS = "classDays";
    private static final String COLUMN_CLASS_HOURS = "classHours";
    private static final String COLUMN_CLASS_DIFFICULTY = "classDiff";
    private static final String COLUMN_CLASS_CAPACITY = "classCap";
    private static final String COLUMN_CLASS_START_TIME = "startTime";

    public static final String ENROLL_TABLE = "enrollment";
    public static final String ENROLL_ID = "enrollmentID";
    public static final String COLUMN_ENROLL_USER = "username";


    public ClassDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists classes(className TEXT primary key, classDesc TEXT)");

        //For debugging
        String createTableStatement = "CREATE TABLE IF NOT EXISTS " + INSTRUCTOR_CLASSES + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_INSTRUCTOR_NAME + " TEXT, " + COLUMN_CLASS_TYPE
                + " TEXT, " + COLUMN_CLASS_DAYS + " TEXT, " + COLUMN_CLASS_HOURS + " INTEGER, " + COLUMN_CLASS_DIFFICULTY + " TEXT, " +
                COLUMN_CLASS_CAPACITY + " INTEGER, " + COLUMN_CLASS_START_TIME + " TIMESTAMP)";

        db.execSQL(createTableStatement);

        /* this single table will keep track of all classes enrolled in.
         * - By querying on username one can identify all classes a single user is enrolled in.
         * - By querying on username, hours and time, one can identify time conflicts.
         * - By querying on classType and Day, one can identify the number enrolled in a class
         */
        db.execSQL("create table if not exists " + ENROLL_TABLE + "(" + ENROLL_ID + " INTEGER primary key autoincrement, "
                + COLUMN_ENROLL_USER + " TEXT, " + COLUMN_INSTRUCTOR_NAME + " TEXT, " + COLUMN_CLASS_TYPE + " TEXT, " +
                COLUMN_CLASS_DAYS + " TEXT, " + COLUMN_CLASS_HOURS + " INTEGER, " + COLUMN_CLASS_DIFFICULTY + " TEXT, " +
                COLUMN_CLASS_CAPACITY + " INTEGER, " + COLUMN_CLASS_START_TIME + " TIMESTAMP)");

    }

    public void onUpgrade(SQLiteDatabase db, int i, int j) {
        db.execSQL("drop Table if exists classes");

        db.execSQL("DROP TABLE IF EXISTS " + INSTRUCTOR_CLASSES);

    }

    public List<String> getAllClassTypes(){

        List<String> classTypes = new ArrayList<String>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select className from classes", null);

        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()) {

                classTypes.add(cursor.getString(0));

                cursor.moveToNext();
            }

        }

        cursor.close();

        return classTypes;
    }

    //Check if a class already exists at a day.
    public String itemExists(String className, String day){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + INSTRUCTOR_CLASSES, null);

        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()) {

                if (cursor.getString(2).equals(className) && cursor.getString(3).equals(day)){
                    String instructor = cursor.getString(1);
                    cursor.close();

                    return instructor;
                }

                cursor.moveToNext();
            }
        }

        cursor.close();

        return null;
    }

    public boolean checkFullClass(String classType, String day){//function to check if class is full or not

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select classCap from instructorClasses where classType = ? and classDays= ?", new String[] {classType,day});
        if (cursor.moveToFirst()){
            int c = cursor.getInt(0);
            if(c==0) return true;//true if class is full
        }
        return false;
    }

    public boolean checkConflict(String userName, String startTime, String day, int hours){// function to check if there is any time conflicts between selected class and other enrolled classes
        SQLiteDatabase db = this.getWritableDatabase();
        Timestamp t= Timestamp.valueOf(startTime);//casting start time into timestamp to add hours
        t.setHours(t.getHours()+hours);//adding class hours to figure out end time of class
        String end=t.toString();
        Cursor cursor = db.rawQuery("Select * from enrollment where username= ? and classDays=? and startTime between ? and ? "
                , new String[] {userName,day,startTime,end});
        return cursor.getCount()>0;//true of there is class which is enrolled having start time between start and end time of selected class
    }

    /*public static createMemberTable(String memberTable) {
        MainActivity
    }*/

}
