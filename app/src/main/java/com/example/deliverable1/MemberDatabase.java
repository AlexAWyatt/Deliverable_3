package com.example.deliverable1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MemberDatabase extends SQLiteOpenHelper {
    private static final String TAG = "MemberDatabase.db";
    public static final String TABLE_NAME = "MemberDatabase";

    public MemberDatabase(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists members(memberID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT not null)");

        ContentValues memberAccount= new ContentValues();

        memberAccount.put("username", "James");
        memberAccount.put("password", "Smith");

        db.insert("members", null, memberAccount);

        ContentValues memberAccount2 = new ContentValues();

        memberAccount2.put("username", "Joan");
        memberAccount2.put("password", "Myth");

        db.insert("members", null, memberAccount2);
    }

    public void onUpgrade(SQLiteDatabase db, int i, int j) {
        db.execSQL("drop Table if exists members");

        db.execSQL("drop table if exists enrollment");
    }

    /*/** DONT DELETE YET, INITIAL IDEA THAT SEEMS INEFFICIENT, KEEPING FOR LATER POSSIBLE APPLICATION/INSPIRATION
     *
     *
     * Used as insert message ONLY WHEN A NEW MEMBER IS CREATED. Creates a separate table named after that members username which
     * tracks all classes they are enrolled in
     *
     * @param db database to insert credentials into
     * @param table Name of table to insert credentials into ("members")
     * @param nullColumnHack null
     * @param values values to insert into table "members"
     * @param username username of new member, used to create member database of same name
     * @return long returned from regular database insert operation
     *
    public long insertNewMember(SQLiteDatabase db, ContentValues values, String username) {
        long rtn = db.insert("members", null, values);

        // creates table inside database to track a members enrolled classes
        db.execSQL("create table if not exists " + username + "(memberClassID INTEGER primary key autoincrement, instructorName TEXT," +
                " classType TEXT, classDays TEXT, classHours TEXT, classDiff TEXT, classCap TEXT, startTime TEXT)");

        return rtn; // states whether the info was added to the member CREDENTIAL database
    }*/



    public Boolean checkMember(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from members where username = ? and password = ?", new String[] {username, password});
        return cursor.getCount() > 0;
    }


}
