package com.cust.sammar.fyp.Activities.AppWidgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.Switch;
import android.widget.Toast;


import com.cust.sammar.fyp.Activities.Course;
import com.cust.sammar.fyp.Activities.ManualTimetable.timetableMainActivity;
import com.cust.sammar.fyp.Activities.Notes.CourseList.ChooseNoteType;
import com.cust.sammar.fyp.Activities.Notes.CourseList.CourseListDisplay;
import com.cust.sammar.fyp.Activities.Notes.CourseList.NoteEditorActivity;
import com.cust.sammar.fyp.Activities.Notes.CourseList.voiceNote.activities.VoiceNoteMainActivity;
import com.cust.sammar.fyp.Activities.Notes.ImageNotesActivity;
import com.cust.sammar.fyp.R;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Database.DatabaseHelper;

/**
 * Created by Hawkeye on 11/07/2019.
 */
public class AddNoteWidget extends AppWidgetProvider
{
    static DatabaseHelper databaseHelper;


    static void updateAppWidget(Context context,AppWidgetManager appWidgetManager, int appWidgetId)
    {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strDate = mdformat.format(calendar.getTime());
        Time currentTime = Time.valueOf(strDate);
        String courseName = "NoCourseFound";
        int Day = calendar.get(Calendar.DAY_OF_WEEK);
        String DayToday = " ";
        switch (Day) {
            case Calendar.MONDAY:
                DayToday = "Mon";
                break;
            case Calendar.TUESDAY:
                DayToday = "Tue";
                break;
            case Calendar.WEDNESDAY:
                DayToday = "Wed";
                break;
            case Calendar.THURSDAY:
                DayToday = "Thu";
                break;
            case Calendar.FRIDAY:
                DayToday = "Fri";
                break;
            case Calendar.SATURDAY:
                DayToday = "Sat";
                break;
            case Calendar.SUNDAY:
                DayToday = "Sun";
                break;

            default:
                break;
        }
        System.out.println("Current Time:" + strDate + "  Day Today:" + DayToday);
        databaseHelper = new DatabaseHelper(context);
        List<Course> courseList = databaseHelper.fetchCourses(context);
        for (Course c : courseList) {
            for (String courseDay : c.getDays()) {
                if (courseDay.equals(DayToday)) {
                    for (int i = 0; i < c.getTime_ranges().size(); i++) {
                        String[] arr = c.getTime_ranges().get(i).split("-");
                        String strt = arr[0];
                        String end = arr[1];
                        strt += ":00";
                        end += ":00";

                        Time startTime = Time.valueOf(strt);
                        Time endTime = Time.valueOf(end);

                        if (currentTime.compareTo(startTime) > 0 && currentTime.compareTo(endTime) < 0) {
                            courseName = c.getName();
                        }

                    }
                } else
                    continue;
            }
        }


        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.add_note_widget);



        Intent intentTextNote = null;
        if(courseList.isEmpty())
        {
            Toast.makeText(context, "No Courses are There to make Notes against", Toast.LENGTH_SHORT).show();
            intentTextNote = new Intent(context, timetableMainActivity.class);
        }
        else if(courseName != "NoCourseFound") {
            intentTextNote = new Intent(context, NoteEditorActivity.class);
            intentTextNote.putExtra("CourseName",courseName);
            intentTextNote.putExtra("Action","insert");

        }
        else {
            intentTextNote = new Intent(context, ChooseNoteType.class);
            intentTextNote.putExtra("CourseName",courseName);

        }
        PendingIntent pendingText = PendingIntent.getActivity(context,0,intentTextNote,PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.add_tnote_widget_btn, pendingText);

        Intent intentImageNote = null;
        if(courseList.isEmpty())
        {
            Toast.makeText(context, "No Courses are There to make Notes against", Toast.LENGTH_SHORT).show();
            intentImageNote = new Intent(context, timetableMainActivity.class);
        }
        else if(courseName != "NoCourseFound") {
            intentImageNote = new Intent(context, ImageNotesActivity.class);
            intentImageNote.putExtra("CourseName",courseName);

        }
        else {
            intentImageNote = new Intent(context, ChooseNoteType.class);
            intentImageNote.putExtra("CourseName",courseName);

        }
        PendingIntent pendingImage = PendingIntent.getActivity(context,0,intentImageNote,PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.add_inote_widget_btn, pendingImage);

        Intent intentVoiceNote = null;
        if(courseList.isEmpty())
        {
            Toast.makeText(context, "No Courses are There to make Notes against", Toast.LENGTH_SHORT).show();
            intentVoiceNote = new Intent(context, timetableMainActivity.class);
        }
        else if(courseName != "NoCourseFound") {
            intentVoiceNote = new Intent(context, VoiceNoteMainActivity.class);
            intentVoiceNote.putExtra("CourseName",courseName);

        }
        else {
            intentVoiceNote = new Intent(context, ChooseNoteType.class);
            intentVoiceNote.putExtra("CourseName",courseName);
        }
        PendingIntent pendingVoice = PendingIntent.getActivity(context,0,intentVoiceNote,PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.add_vnote_widget_btn, pendingVoice);


//Create an Intent with the AppWidgetManager.ACTION_APPWIDGET_UPDATE action//
        Intent intentUpdate = new Intent(context, AddNoteWidget.class);
        intentUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
//Update the current widget instance only, by creating an array that contains the widget’s unique ID//
        int[] idArray = new int[]{appWidgetId};
        intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray);
        intentUpdate.putExtra("begin",true);
//Wrap the intent as a PendingIntent, using PendingIntent.getBroadcast()//
        PendingIntent pendingUpdate = PendingIntent.getBroadcast(context, appWidgetId, intentUpdate,PendingIntent.FLAG_UPDATE_CURRENT);
//Send the pending intent in response to the user tapping the ‘Update’ TextView//
        try {
            pendingUpdate.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds)
        {
            updateAppWidget(context, appWidgetManager, appWidgetId);

        }
    }

    @Override
    public void onReceive(Context context, Intent intnt) {


        super.onReceive(context, intnt);
    }
}
