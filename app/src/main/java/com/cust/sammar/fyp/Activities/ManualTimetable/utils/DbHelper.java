package com.cust.sammar.fyp.Activities.ManualTimetable.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.cust.sammar.fyp.Activities.Course;
import com.cust.sammar.fyp.Activities.Notes.CourseList.Note;
import com.cust.sammar.fyp.Activities.ManualTimetable.model.Week;

import java.util.ArrayList;
import java.util.HashMap;

import Database.DatabaseHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 6;
    private static final String DB_NAME = "timetabledb";
    private static final String TIMETABLE = "timetable";
    private static final String WEEK_ID = "id";
    private static final String WEEK_SUBJECT = "subject";
    private static final String WEEK_FRAGMENT = "fragment";
    private static final String WEEK_TEACHER = "teacher";
    private static final String WEEK_ROOM = "room";
    private static final String WEEK_FROM_TIME = "fromtime";
    private static final String WEEK_TO_TIME = "totime";
    private static final String WEEK_COLOR = "color";


    private static final String NOTES = "notes";
    private static final String NOTES_ID = "id";
    private static final String NOTES_TITLE = "title";
    private static final String NOTES_TEXT = "text";
    private static final String NOTES_COLOR = "color";

    String[] allcolors;
    ArrayList<String> titles = new ArrayList<String>();
    HashMap<String,String> title_color_mapping = new HashMap<String, String>();
    static int rgb;
    Context c;

    DatabaseHelper helper;

    public DbHelper(Context context)
    {
        super(context , DB_NAME, null, DB_VERSION);
        c=context;
    }

     public void onCreate(SQLiteDatabase db) {
        String CREATE_TIMETABLE = "CREATE TABLE " + TIMETABLE + "("
                + WEEK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + WEEK_SUBJECT + " TEXT,"
                + WEEK_FRAGMENT + " TEXT,"
                + WEEK_TEACHER + " TEXT,"
                + WEEK_ROOM + " TEXT,"
                + WEEK_FROM_TIME + " TEXT,"
                + WEEK_TO_TIME + " TEXT,"
                + WEEK_COLOR + " INTEGER" +  ")";

        String CREATE_NOTES = "CREATE TABLE " + NOTES + "("
                + NOTES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NOTES_TITLE + " TEXT,"
                + NOTES_TEXT + " TEXT,"
                + NOTES_COLOR + " INTEGER" + ")";



        db.execSQL(CREATE_TIMETABLE);
        db.execSQL(CREATE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                db.execSQL("DROP TABLE IF EXISTS " + TIMETABLE);

            case 2:
                db.execSQL("DROP TABLE IF EXISTS " + NOTES);
                break;
        }
        onCreate(db);
    }

    public void removeConflicts(Week week)
    {
        switch (week.getFragment())
        {
            case "Mon":
            {
                week.setFragment("MondayFragment");
                break;
            }
            case "Tue":
            {
                week.setFragment("TuesdayFragment");
                break;
            }
            case "Wed":
            {
                week.setFragment("WednesdayFragment");
                break;
            }
            case "Thu":
            {
                week.setFragment("ThursdayFragment");
                break;
            }
            case "Fri":
            {
                week.setFragment("FridayFragment");
                break;
            }
            case "Sat":
            {
                week.setFragment("SaturdayFragment");
                break;
            }
            case "Sun":
            {
                week.setFragment("SundayFragment");
                break;
            }
            default:
            {
                insertWeek(week);
            }

        }
        insertWeekfromPortal(week);
    }
    public void insertWeek(Week week){
        helper=new DatabaseHelper(c);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(WEEK_SUBJECT, week.getSubject());
        contentValues.put(WEEK_FRAGMENT, week.getFragment());
        contentValues.put(WEEK_TEACHER, week.getTeacher());
        contentValues.put(WEEK_ROOM, week.getRoom());
        contentValues.put(WEEK_FROM_TIME, week.getFromTime());
        contentValues.put(WEEK_TO_TIME, week.getToTime());
        contentValues.put(WEEK_COLOR, week.getColor());
        db.insert(TIMETABLE,null, contentValues);
        db.update(TIMETABLE, contentValues, WEEK_FRAGMENT, null);
        db.close();
        Course course=new Course();
        course.setColor(week.getColor());
        course.getDays().add(week.getFragment().substring(0,3));
        course.setLecturer_name(week.getTeacher());
        course.getLocations().add(week.getRoom());
        course.setName(week.getSubject());
        course.getTime_ranges().add(week.getFromTime()+"-"+week.getToTime());
        helper.addCoursefromWeeks(course);
        helper.addCourseToTablefromWeek(course);
    }
    public void insertWeekfromPortal(Week week)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(WEEK_SUBJECT, week.getSubject());
        contentValues.put(WEEK_FRAGMENT, week.getFragment());
        contentValues.put(WEEK_TEACHER, week.getTeacher());
        contentValues.put(WEEK_ROOM, week.getRoom());
        contentValues.put(WEEK_FROM_TIME, week.getFromTime());
        contentValues.put(WEEK_TO_TIME, week.getToTime());
        contentValues.put(WEEK_COLOR, week.getColor());
        db.insert(TIMETABLE,null, contentValues);
        db.update(TIMETABLE, contentValues, WEEK_FRAGMENT, null);
        db.close();

    }

    public void deleteWeekById(Week week) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+WEEK_FRAGMENT+" FROM "+TIMETABLE +" WHERE "+ WEEK_ID+" = "+week.getId(),null);
        while (cursor.moveToNext())
        {
            week.setFragment(cursor.getString(cursor.getColumnIndex(WEEK_FRAGMENT)));
        }

        db.delete(TIMETABLE, WEEK_ID + " = ? ", new String[]{String.valueOf(week.getId())});

        String courseName = week.getSubject();
        String day = week.getFragment().substring(0,3);
        String time = week.getFromTime().concat("-"+week.getToTime());
        int loc = courseName.indexOf("(");
        if (loc != -1)
            courseName = courseName.substring(0, loc - 1);




        helper = new DatabaseHelper(c);
        helper.deleteCoursefromtable(courseName,day,time);
        db.close();
    }

    public void updateWeek(Week week)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(WEEK_SUBJECT, week.getSubject());
        contentValues.put(WEEK_TEACHER, week.getTeacher());
        contentValues.put(WEEK_ROOM, week.getRoom());
        contentValues.put(WEEK_FROM_TIME,week.getFromTime());
        contentValues.put(WEEK_TO_TIME, week.getToTime());
        contentValues.put(WEEK_COLOR, week.getColor());
        db.update(TIMETABLE, contentValues, WEEK_ID + " = " + week.getId(), null);
        Cursor cursor = db.rawQuery("SELECT "+WEEK_FRAGMENT+" FROM "+TIMETABLE +" WHERE "+ WEEK_ID+" = "+week.getId(),null);
        while (cursor.moveToNext())
        {
            week.setFragment(cursor.getString(cursor.getColumnIndex(WEEK_FRAGMENT)));
        }
        if(week.getFragment()!=null)
        {
            Course course = new Course();
            course.setName(week.getSubject());
            course.setColor(week.getColor());
            if (week.getFragment() != null)
                course.getDays().add(week.getFragment().substring(0, 3));
            course.setLecturer_name(week.getTeacher());
            course.getLocations().add(week.getRoom());

            String split = course.getName();
            int loc = split.indexOf("(");
            if (loc != -1)
                split = split.substring(0, loc - 1);
            course.setName(split);
            course.getTime_ranges().add(week.getFromTime() + "-" + week.getToTime());
            helper = new DatabaseHelper(c);
            helper.updateCourses(course);
            helper.updateCoursesToTable(course);
        }
        db.close();
    }

    public boolean checkDB()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " +TIMETABLE, null);
        Boolean rowExists;

        if (mCursor.moveToFirst())
        {
            // DO SOMETHING WITH CURSOR
            rowExists = true;

        } else
        {
            // I AM EMPTY
            rowExists = false;
        }
        return rowExists;
    }
    public ArrayList<Week> getWeek(String fragment){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Week> weeklist = new ArrayList<>();


        Week week;
        Cursor cursor = db.rawQuery("SELECT * FROM ( SELECT * FROM "+TIMETABLE+" ORDER BY " + WEEK_FROM_TIME + " ) WHERE "+ WEEK_FRAGMENT +" LIKE '"+fragment+"%'",null);
        while (cursor.moveToNext()){
            week = new Week();
            week.setId(cursor.getInt(cursor.getColumnIndex(WEEK_ID)));
            week.setSubject(cursor.getString(cursor.getColumnIndex(WEEK_SUBJECT)));
            week.setTeacher(cursor.getString(cursor.getColumnIndex(WEEK_TEACHER)));
            week.setRoom(cursor.getString(cursor.getColumnIndex(WEEK_ROOM)));
            week.setFromTime(cursor.getString(cursor.getColumnIndex(WEEK_FROM_TIME)));
            week.setToTime(cursor.getString(cursor.getColumnIndex(WEEK_TO_TIME)));
            week.setColor(cursor.getInt(cursor.getColumnIndex(WEEK_COLOR)));
            weeklist.add(week);
        }
        return  weeklist;
    }

    public void getDatafromOtherDB( ArrayList<Course> courseArrayList)
    {
        Week week=new Week();
        for(Course c:courseArrayList)
        {
            week.setSubject(c.getName() + " (" + c.getSection() + ")");
            week.setTeacher(c.getLecturer_name());
            for (int i = 0; i < c.getTime_ranges().size(); i++)
            {
                week.setFragment(c.getDays().get(i));
                String[] startEnd = c.getTime_ranges().get(i).split("-", 2);
                week.setToTime(startEnd[1]);
                week.setFromTime(startEnd[0]);
                week.setRoom(c.getLocations().get(i));
                week.setColor(c.getColor());
                removeConflicts(week);
            }
        }
    }
    /**
     * Methods for Notes activity
     **/
//    public void insertNote(Note note) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(NOTES_TITLE, note.getTitle());
//        contentValues.put(NOTES_TEXT, note.getText());
//        contentValues.put(NOTES_COLOR, note.getColor());
//        db.insert(NOTES, null, contentValues);
//        db.close();
//    }

//    public void updateNote(Note note)  {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(NOTES_TITLE, note.getTitle());
//        contentValues.put(NOTES_TEXT, note.getText());
//        contentValues.put(NOTES_COLOR, note.getColor());
//        db.update(NOTES, contentValues, NOTES_ID + " = " + note.getId(), null);
//        db.close();
//    }

    public void deleteNoteById(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(NOTES, NOTES_ID + " =? ", new String[] {String.valueOf(note.getId())});
        db.close();
    }

//    public ArrayList<Note> getNote() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ArrayList<Note> notelist = new ArrayList<>();
//        Note note;
//        Cursor cursor = db.rawQuery("SELECT * FROM " + NOTES, null);
//        while (cursor.moveToNext()) {
//            note = new Note();
//            note.setId(cursor.getInt(cursor.getColumnIndex(NOTES_ID)));
//            note.setTitle(cursor.getString(cursor.getColumnIndex(NOTES_TITLE)));
//            note.setText(cursor.getString(cursor.getColumnIndex(NOTES_TEXT)));
//            note.setColor(cursor.getInt(cursor.getColumnIndex(NOTES_COLOR)));
//            notelist.add(note);
//        }
//        cursor.close();
//        db.close();
//        return notelist;
//    }
}
