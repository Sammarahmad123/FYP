package com.cust.sammar.fyp.Activities.ManualTimetable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cust.sammar.fyp.Activities.ManualTimetable.fragments.SettingsFragment;
import com.cust.sammar.fyp.R;


public class SettingsActivity extends AppCompatActivity {
    public static final String
            KEY_SEVEN_DAYS_SETTING = "sevendays";
    public static final String KEY_SCHOOL_WEBSITE_SETTING = "schoolwebsite";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
