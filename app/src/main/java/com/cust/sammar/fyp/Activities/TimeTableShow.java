package com.cust.sammar.fyp.Activities;

import android.content.Intent;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.cust.sammar.fyp.Activities.ManualTimetable.SettingsActivity;
import com.cust.sammar.fyp.Activities.ManualTimetable.model.Week;
import com.cust.sammar.fyp.Activities.ManualTimetable.timetableMainActivity;
import com.cust.sammar.fyp.Activities.ManualTimetable.utils.DbHelper;
import com.cust.sammar.fyp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Database.DatabaseHelper;

public class TimeTableShow extends AppCompatActivity {
    private WeekView weekView;
    private DatabaseHelper databaseHelper;
    private ArrayList<Course> courses;
    Toolbar toolbar;
    Week week = new Week();
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_show);
        databaseHelper = new DatabaseHelper(this);
        dbHelper = new DbHelper(getApplicationContext());
        courses = databaseHelper.fetchCourses(this);
        if(!dbHelper.checkDB())
        {
            dbHelper.getDatafromOtherDB(courses);
        }
        toolbar = (Toolbar) findViewById(R.id.toolbarr);
        toolbar.setTitle("Your Time Table");
        setSupportActionBar(toolbar);
        weekView = findViewById(R.id.weekView);
        weekView.setOnEventClickListener(new WeekView.EventClickListener() {
            @Override
            public void onEventClick(WeekViewEvent event, RectF eventRect) {

            }
        });


        weekView.setMonthChangeListener(new MonthLoader.MonthChangeListener() {
            @Override
            public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
                List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
                int z = 0;
                int j = 0;
                for (Course course : courses) {
                    z++;
                    for (int x = 0; x < course.getDays().size(); x++) {
                        j++;
                        Log.d("FYP_DE", "For course: " + course.getName());
                        Log.d("FYP_DE", "Time is: " + course.getTime_ranges().get(x));
                        Calendar startTime = Calendar.getInstance();
                        startTime.set(Calendar.DAY_OF_WEEK, getIntFromDay(course.getDays().get(x)));
                        startTime.set(Calendar.HOUR_OF_DAY, getStartHour(course.getTime_ranges().get(x)));
                        startTime.set(Calendar.MINUTE, getStartMinute(course.getTime_ranges().get(x)));
                        startTime.set(Calendar.MONTH, newMonth);
                        startTime.set(Calendar.YEAR, newYear);

                        Calendar endTime = Calendar.getInstance();
                        endTime.set(Calendar.DAY_OF_WEEK, getIntFromDay(course.getDays().get(x)));
                        endTime.set(Calendar.HOUR_OF_DAY, getEndHour(course.getTime_ranges().get(x)));
                        endTime.set(Calendar.MINUTE, getEndMinute(course.getTime_ranges().get(x)));
                        endTime.set(Calendar.MONTH, newMonth);
                        endTime.set(Calendar.YEAR, newYear);

                        WeekViewEvent event = new WeekViewEvent(z * j, course.getName(), startTime, endTime);
                        event.setColor(course.getColor());
                        events.add(event);
                    }
                }
                return events;
            }
        });

        weekView.setEventLongPressListener(new WeekView.EventLongPressListener() {
            @Override
            public void onEventLongPress(WeekViewEvent event, RectF eventRect) {

            }
        });

//        showT=(TextView)findViewById(R.id.timetable);
//        String portalText=getIntent().getStringExtra("portaldata");
//        showT.append(portalText);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu men) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, men);

        return super.onCreateOptionsMenu(men);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.menuSettings) {
            Intent settings = new Intent(this, SettingsActivity.class);
            startActivity(settings);
            return true;
        } else if (id == R.id.list_show) {
            Intent timetable = new Intent(this, timetableMainActivity.class);
            startActivity(timetable);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private int getIntFromDay(String day) {
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        for (int i = 0; i < days.length; i++) {
            if (days[i].equals(day)) {
                Log.d("FYP_DE", "Int from day is: " + String.valueOf(i + 2));
                return i + 2;
            }
        }

        return 0;
    }

    private int getStartHour(String time) {
        String tm = time.replaceAll("\\s", "");
        int ihour = 0;
        int add = 0;
        String hour = tm.substring(0, 2);
        ihour = Integer.valueOf(hour);
        if (time.contains("AM") || time.contains("PM")) {
            String am_pm = tm.substring(5, 7);
            if (am_pm.equals("PM") && ihour != 12) {
                add = 12;
            }
        }
        Log.d("FYP_DE", "start hour is " + String.valueOf(ihour + add));
        return ihour + add;

    }

    private int getStartMinute(String time) {
        String tm = time.replaceAll("\\s", "");
        String minute = tm.substring(3, 5);
        Log.d("FYP_DE", "Start minute: " + minute);
        return Integer.valueOf(minute);
    }

    private int getEndHour(String time) {
        String tm = time.replaceAll("\\s", "");
        String[] startend = tm.split("-", 2);
        tm = startend[1];
        String hour = tm.substring(0, 2);
        int ihour = Integer.valueOf(hour);
        int add = 0;
        if (tm.contains("AM") || tm.contains("PM")) {
            String am_pm = tm.substring(5, 7);
            if (am_pm.equals("PM") && ihour != 12) {
                add = 12;
            }
        }
        Log.d("FYP_DE", "End hour: " + String.valueOf(ihour + add));
        return ihour + add;
    }

    private int getEndMinute(String time) {
        String tm = time.replaceAll("\\s", "");
        String[] startend = tm.split("-", 2);
        tm = startend[1];
        String minute = tm.substring(3, 5);
        Log.d("FYP_DE", "End Minute: " + minute);
        return Integer.valueOf(minute);

    }

}
