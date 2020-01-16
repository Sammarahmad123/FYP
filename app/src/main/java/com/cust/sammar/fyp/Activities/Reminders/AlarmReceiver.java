package com.cust.sammar.fyp.Activities.Reminders;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cust.sammar.fyp.Activities.ManualTimetable.model.Week;
import com.cust.sammar.fyp.Activities.ManualTimetable.utils.DbHelper;
import com.cust.sammar.fyp.Activities.Notes.CourseList.TextNoteMainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AlarmReceiver extends BroadcastReceiver {

    String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                LocalData localData = new LocalData(context);
                NotificationScheduler.setReminder(context, AlarmReceiver.class, localData.get_hour(), localData.get_min());
                return;
            }
        }

        String cont = LocalData.content;

        if (cont.isEmpty())
        {
            Log.d(TAG, "onReceive: ");

            DbHelper db = new DbHelper(context);
            ArrayList<Week> w = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
            Date d = new Date();
            String dayOfTheWeek = sdf.format(d);
            Log.d(TAG, dayOfTheWeek);
            dayOfTheWeek = dayOfTheWeek + "Fragment";
            w = db.getWeek(dayOfTheWeek);

            if (w.size() == 0) {
                NotificationScheduler.showNotification(context, ReminderActivity.class,
                        "Reminder", "You have no class today");
            } else {
                Log.d(TAG, "Notification generated...");
                String content = "";
                for (int i = 0; i < w.size(); i++) {

                    content += w.get(i).getSubject() + "\n" +
                            w.get(i).getFromTime() + "\n" + w.get(i).getRoom() + "\n";
                }
                NotificationScheduler.showNotification(context, ReminderActivity.class,
                        "Today's classes", content);
            }
        }
        else
        {
            NotificationScheduler.showNotification(context, TextNoteMainActivity.class,"Note Reminder",cont);
        }
    }
}
