package com.cust.sammar.fyp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.cust.sammar.fyp.Activities.ManualTimetable.timetableMainActivity;
import com.cust.sammar.fyp.Activities.SemesterDuration.SemesterDurationActivity;
import com.cust.sammar.fyp.R;

import java.util.ArrayList;

import Database.DatabaseHelper;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 10000;
    private ProgressBar mProgress;
    private DatabaseHelper databaseHelper;
    private ArrayList<Course> courses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        databaseHelper = new DatabaseHelper(this);
        courses = databaseHelper.fetchCourses(this);


        mProgress=(ProgressBar)findViewById(R.id.splash_screen_progress_bar);


        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();

    }
    private void doWork() {
        for (int progress=0; progress<100; progress+=20) {
            try {
                Thread.sleep(200);
                mProgress.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
                //Timber.e(e.getMessage());
            }
        }
    }
    private void startApp() {
        /*Intent intent = new Intent(SplashActivity.this, SemesterDurationActivity.class);
        startActivity(intent);*/


        if(courses.isEmpty())
        {
            Intent intent = new Intent(SplashActivity.this, SemesterDurationActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(SplashActivity.this, timetableMainActivity.class);
            startActivity(intent);
        }

    }




}

