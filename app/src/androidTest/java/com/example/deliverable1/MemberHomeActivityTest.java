package com.example.deliverable1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.core.app.ApplicationProvider;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.view.View;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.core.app.ActivityScenario;
import android.app.Activity;

@RunWith(AndroidJUnit4.class)
public class MemberHomeActivityTest {
    private ClassDatabase db;
    private MemberDatabase members;
    private MemberHomeActivity home;
    //private Button nextButton;
    private MainActivity main;
    private MemberHomeActivity member;
    private ActivityScenario  scenario1;

    @Rule
    public ActivityScenarioRule<MemberHomeActivity> mActivityRule =
            new ActivityScenarioRule<>(MemberHomeActivity.class);

    @Before
    public void create() {
        //scenario1 = mActivityRule.getScenario();
        //ActivityScenario scenario2 = memberRule.getScenario();

        Context classes = ApplicationProvider.getApplicationContext();
        db = new ClassDatabase(classes);

        members = new MemberDatabase(classes);

        //main = new MainActivity();

        //member = new MemberHomeActivity();

        setUpDatabases();

        ArrayList<String> cars = new ArrayList<String>();
        cars.add("Jared");
        cars.add("Yoga");
        cars.add("Friday");
        cars.add("1.5 hours");
        cars.add("Beginner");
        cars.add("3");
        cars.add("10:30");

        home.setBundle("Joan", cars);


    }

    @After
    public void closeDb() {
        db.close();
        members.close();
    }

    //@After
    //public void tearDown() throws Exception {
    //    if (scenario1 != null) {
    //        scenario1.close();
    //    }
    //    scenario1 = null;
    //}

    @Test
    public void testEnroll() {
        // tests writing to the database and conflict detection (itemExists() method)
        //ActivityScenario  scenario = mActivityRule.getScenario();

        

        ActivityScenario<MemberHomeActivity> activated = ActivityScenario.launch(MemberHomeActivity.class);


        //member = new MemberHomeActivity();


        //home = new MemberHomeActivity();

        SQLiteDatabase write = db.getWritableDatabase();

        assertEquals(-1, member.enroll());
    }

    /*public void createInteractiveButton(String buttonId) {
        String buttonCall = new String("R.id." + buttonId);
        nextButton = (Button) findViewById(buttonCall);
    }*/

    //@Override
    //public ActivityScenario getScenario() {
    //    if (scenario1 == null) {
    //        scenario1 = ActivityScenario.launch(MainActivity.class);
    //    }
    //    return tryAcquireScenarioActivity(scenario1);
    //}

    /*protected static ActivityScenario tryAcquireScenarioActivity(ActivityScenario activityScenario) {
        Semaphore activityResource = new Semaphore(0);
        Activity[] scenarioActivity = new Activity[1];
        activityScenario.onActivity(activity -> {
            scenarioActivity[0] = activity;
            activityResource.release();
        });
        try {
            activityResource.tryAcquire(15000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Assert.fail("Failed to acquire activity scenario semaphore");
        }
        Assert.assertNotNull("Scenario Activity should be non-null", scenarioActivity[0]);
        return scenarioActivity[0];
    }*/

    private void setUpDatabases() {

        SQLiteDatabase sq = db.getWritableDatabase();

        ContentValues baseClass1 = new ContentValues();

        baseClass1.put("className", "Yoga");
        baseClass1.put("classDesc", "Relaxing");

        sq.insert("classes", null, baseClass1);

        ContentValues baseClass2 = new ContentValues();

        baseClass2.put("className", "Pilates");
        baseClass2.put("classDesc", "Group Exercise");

        sq.insert("classes", null, baseClass2);

        ContentValues baseClass3 = new ContentValues();

        baseClass3.put("className", "Biking");
        baseClass3.put("classDesc", "Exhilarating");

        sq.insert("classes", null, baseClass3);

        ContentValues class1 = new ContentValues();

        class1.put("classType", "Yoga");
        class1.put("instructorName", "Jared");
        class1.put("classDays", "Friday");
        class1.put("classHours", "1.5 hours");
        class1.put("classDiff", "Beginner");
        class1.put("classCap", "3");
        class1.put("startTime", "10:30");

        sq.insert("instructorClasses", null, class1);

        ContentValues class2 = new ContentValues();

        class2.put("classType", "Pilates");
        class2.put("instructorName", "Jared");
        class2.put("classDays", "Thursday");
        class2.put("classHours", "2 hours");
        class2.put("classDiff", "Intermediate");
        class2.put("classCap", "2");
        class2.put("startTime", "16:30");

        sq.insert("instructorClasses", null, class2);

        ContentValues class3 = new ContentValues();

        class3.put("classType", "Pilates");
        class3.put("instructorName", "Sasha");
        class3.put("classDays", "Friday");
        class3.put("classHours", "2.5 hours");
        class3.put("classDiff", "Advanced");
        class3.put("classCap", "2");
        class3.put("startTime", "08:30"); // overlaps by half an hour with Jared's Friday Yoga Class

        sq.insert("instructorClasses", null, class3);

        ContentValues class4 = new ContentValues();

        class4.put("classType", "Biking");
        class4.put("instructorName", "Sasha");
        class4.put("classDays", "Monday");
        class4.put("classHours", "3 hours");
        class4.put("classDiff", "Beginner");
        class4.put("classCap", "1");
        class4.put("startTime", "18:00");

        sq.insert("instructorClasses", null, class4);

        ContentValues class5 = new ContentValues();

        class5.put("classType", "Yoga");
        class5.put("instructorName", "Sasha");
        class5.put("classDays", "Thursday");
        class5.put("classHours", "0.5 hours");
        class5.put("classDiff", "Beginner");
        class5.put("classCap", "4");
        class5.put("startTime", "16:00");

        sq.insert("instructorClasses", null, class5);
    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("MemberHomeActivityTest");
    }

}