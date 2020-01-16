package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.cust.sammar.fyp.Activities.Course;
import com.cust.sammar.fyp.Activities.ManualTimetable.model.Week;
import com.cust.sammar.fyp.Activities.ManualTimetable.utils.DbHelper;
import com.cust.sammar.fyp.Activities.Notes.CourseList.Note;

import java.io.File;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    //database name
//    public static final String STUDY_MATE = "StudyMate";

    //version number
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Studymate.db";
    DbHelper dbHelper;
    Week week = new Week();

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        dbHelper = new DbHelper(context);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("FYP_DB", DbContract.Course.CREATE_TABLE_STRING);
        Log.d("FYP_DB", DbContract.TimeTable.CREATE_TABLE_STRING);
        db.execSQL(DbContract.Course.CREATE_TABLE_STRING);
        db.execSQL(DbContract.TimeTable.CREATE_TABLE_STRING);
        db.execSQL(DbContract.Notes.CREATE_TABLE_NOTES);
        // db.execSQL(CREATE_TABLE_TIMETABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.Course.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.TimeTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.Notes.TABLE_NOTES);
        onCreate(db);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean insertNote(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(DbContract.Notes.COURSE_TITLE, note.getCoursetitle().trim());
        cValues.put(DbContract.Notes.NOTE_TITLE, note.getTitle());
        cValues.put(DbContract.Notes.NOTE_TEXT, note.getNoteText());
        cValues.put(DbContract.Notes.NOTE_TYPE, note.getNoteType());
        long id = db.insert(DbContract.Notes.TABLE_NOTES, null, cValues);
        db.close();
        return id > 0;

}

    public boolean updateNote(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cValues = new ContentValues();
        if(note.getCoursetitle()!=null)
            cValues.put(DbContract.Notes.COURSE_TITLE, note.getCoursetitle().trim());
        cValues.put(DbContract.Notes.NOTE_TITLE, note.getTitle());
        cValues.put(DbContract.Notes.NOTE_TEXT, note.getNoteText());
        cValues.put(DbContract.Notes.NOTE_TYPE, note.getNoteType());
        long id = db.update(DbContract.Notes.TABLE_NOTES, cValues, DbContract.Notes.NOTE_ID + " = " + note.getId(), null);
        db.close();
        return id > 0;
    }

    public boolean deleteNote(int Id) {
        SQLiteDatabase db = getWritableDatabase();
        long id = db.delete(DbContract.Notes.TABLE_NOTES, DbContract.Notes.NOTE_ID + " = " + Id, null);
        db.close();
        return id > 0;

    }
    public ArrayList<Note> fetchNotes(String courseTitle)
    {
        if(courseTitle!=null) {
            courseTitle = courseTitle.trim();
            Log.d("CourseTitle", courseTitle);
            SQLiteDatabase db = getWritableDatabase();
            ArrayList<Note> notesList = new ArrayList<>();
            Log.d("SqlQuery", " Select * from " + DbContract.Notes.TABLE_NOTES + " where " + DbContract.Notes.COURSE_TITLE + " = \"" + courseTitle + "\"");
            Cursor cursor = db.rawQuery(" Select * from " + DbContract.Notes.TABLE_NOTES + " where " + DbContract.Notes.COURSE_TITLE + "='" + courseTitle + "'", null);
            while (cursor.moveToNext()) {
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndex(DbContract.Notes.NOTE_ID)));
                note.setCoursetitle(cursor.getString(cursor.getColumnIndex(DbContract.Notes.COURSE_TITLE)));
                note.setNoteText(cursor.getString(cursor.getColumnIndex(DbContract.Notes.NOTE_TEXT)));
                note.setNoteType(cursor.getString(cursor.getColumnIndex(DbContract.Notes.NOTE_TYPE)));
                note.setTitle(cursor.getString(cursor.getColumnIndex(DbContract.Notes.NOTE_TITLE)));
                note.setNoteCreated(cursor.getString(cursor.getColumnIndex(DbContract.Notes.NOTE_CREATED)));
                notesList.add(note);

            }
            db.close();

            return notesList;
        }
        return null;
    }

    public boolean addCoursefromWeeks(Course course) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cvalues = new ContentValues();
        cvalues.put(DbContract.Course.COLUMN_NAME_COURSE_TITLE, course.getName());
        cvalues.put(DbContract.Course.COLUMN_NAME_COURSE_TEACHER, course.getLecturer_name());
        cvalues.put(DbContract.Course.COLUMN_NAME_COURSE_SECTION, course.getSection());
        cvalues.put(DbContract.Course.COLUMN_NAME_COLOR, course.getColor());
        long id = db.insert(DbContract.Course.TABLE_NAME, null, cvalues);
        db.close();
        return id > 0;
    }

    public boolean addCourseToTablefromWeek(Course course) {
        SQLiteDatabase db = getWritableDatabase();

        int success_count = 0;
        for (int i = 0; i < course.getTime_ranges().size(); i++) {
            ContentValues tvalues = new ContentValues();
            tvalues.put(DbContract.TimeTable.COLUMN_NAME_COURSE_TITLE, course.getName());
            tvalues.put(DbContract.TimeTable.COLUMN_NAME_TIME, course.getTime_ranges().get(i));
            tvalues.put(DbContract.TimeTable.COLUMN_NAME_DAY, course.getDays().get(i));
            tvalues.put(DbContract.TimeTable.COLUMN_NAME_LOCATION, course.getLocations().get(i));
            long id = db.insert(DbContract.TimeTable.TABLE_NAME, null, tvalues);
            if (id > 0) {
                success_count++;
            }
        }
        db.close();
        return success_count == course.getTime_ranges().size();
    }

    public boolean addCourse(Course course) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cvalues = new ContentValues();
        cvalues.put(DbContract.Course.COLUMN_NAME_COURSE_TITLE, course.getName());
        cvalues.put(DbContract.Course.COLUMN_NAME_COURSE_TEACHER, course.getLecturer_name());
        cvalues.put(DbContract.Course.COLUMN_NAME_COURSE_SECTION, course.getSection());
        cvalues.put(DbContract.Course.COLUMN_NAME_COLOR, course.getColor());
        long id = db.insert(DbContract.Course.TABLE_NAME, null, cvalues);
        week.setSubject(course.getName() + " (" + course.getSection() + ")");
        week.setTeacher(course.getLecturer_name());
        db.close();
        return id > 0;
    }


    public boolean addCourseToTable(Course course)
    {
        SQLiteDatabase db = getWritableDatabase();

        int success_count = 0;
        for (int i = 0; i < course.getTime_ranges().size(); i++) {
            ContentValues tvalues = new ContentValues();
            tvalues.put(DbContract.TimeTable.COLUMN_NAME_COURSE_TITLE, course.getName().trim());
            tvalues.put(DbContract.TimeTable.COLUMN_NAME_TIME, course.getTime_ranges().get(i));
            tvalues.put(DbContract.TimeTable.COLUMN_NAME_DAY, course.getDays().get(i));
            tvalues.put(DbContract.TimeTable.COLUMN_NAME_LOCATION, course.getLocations().get(i));
            long id = db.insert(DbContract.TimeTable.TABLE_NAME, null, tvalues);
            week.setFragment(course.getDays().get(i));
            String[] startEnd = course.getTime_ranges().get(i).split("-", 2);
            week.setToTime(startEnd[1]);
            week.setFromTime(startEnd[0]);
            week.setRoom(course.getLocations().get(i));
            week.setColor(course.getColor());
            dbHelper.removeConflicts(week);
            if (id > 0) {
                success_count++;
            }
        }
        db.close();
        return success_count == course.getTime_ranges().size();
    }

    public boolean updateCourses(Course course) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cvalues = new ContentValues();
        cvalues.put(DbContract.Course.COLUMN_NAME_COURSE_TEACHER, course.getLecturer_name());
        cvalues.put(DbContract.Course.COLUMN_NAME_COURSE_SECTION, course.getSection());
        cvalues.put(DbContract.Course.COLUMN_NAME_COLOR, course.getColor());
        long id = db.update(DbContract.Course.TABLE_NAME, cvalues, DbContract.Course.COLUMN_NAME_COURSE_TITLE + " = '" + course.getName() + "'", null);
        db.close();
        return id > 0;
    }
    public boolean deleteCoursefromtable(String courseName,String day,String time)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DbContract.TimeTable.TABLE_NAME, DbContract.TimeTable.COLUMN_NAME_COURSE_TITLE + " = ? AND " + DbContract.TimeTable.COLUMN_NAME_DAY + " = ? AND " + DbContract.TimeTable.COLUMN_NAME_TIME + " = ? ", new String[]{courseName,day,time});

        Cursor cursor = db.rawQuery("Select * from " + DbContract.TimeTable.TABLE_NAME + " WHERE " + DbContract.TimeTable.COLUMN_NAME_COURSE_TITLE + " = '" + courseName +"'", null);

        if(!cursor.moveToFirst())
        {
            db.delete(DbContract.Notes.TABLE_NOTES,DbContract.Notes.COURSE_TITLE + " = ? ",new String[]{courseName});
            db.delete(DbContract.Course.TABLE_NAME,DbContract.Course.COLUMN_NAME_COURSE_TITLE + " = ? ",new String[]{courseName});
            File folder = new File(Environment.getExternalStorageDirectory() + "/StudyMate/"+courseName);
            if(folder.exists())
            {
                if(folder.isDirectory())
                {
                    File[] listFiles = folder.listFiles();
                    for(File file : listFiles){
                        System.out.println("Deleting "+file.getName());
                        file.delete();
                    }
                    folder.delete();
                }

            }

        }

        return true;
    }

    public boolean updateCoursesToTable(Course course) {
        SQLiteDatabase db = getWritableDatabase();

        int success_count = 0;
        for (int i = 0; i < course.getTime_ranges().size(); i++) {
            ContentValues tvalues = new ContentValues();
            tvalues.put(DbContract.TimeTable.COLUMN_NAME_TIME, course.getTime_ranges().get(i));
            tvalues.put(DbContract.TimeTable.COLUMN_NAME_LOCATION, course.getLocations().get(i));
            long id = db.update(DbContract.TimeTable.TABLE_NAME, tvalues, DbContract.TimeTable.COLUMN_NAME_COURSE_TITLE + " = '" + course.getName() + "' AND " + DbContract.TimeTable.COLUMN_NAME_DAY + " = '" + course.getDays().get(0) + "'", null);
            db.close();
            if (id > 0) {
                success_count++;
            }
        }
        db.close();
        return success_count == course.getTime_ranges().size();
    }

    public ArrayList<Course> fetchCourses(Context context) {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Course> courses = new ArrayList<Course>();
        ArrayList<Course> courses1 = new ArrayList<Course>();

//        ArrayList<String> titles = new ArrayList<String>();
//        HashMap<String,String> title_color_mapping = new HashMap<String, String>();
//        String[] allcolors = context.getResources().getStringArray(R.array.event_colors);
        Cursor cursor1 = db.rawQuery("Select * from " + DbContract.Course.TABLE_NAME + ";", null);
        while (cursor1.moveToNext()) {
            Course course = new Course();
            course.setName(cursor1.getString(cursor1.getColumnIndex(DbContract.Course.COLUMN_NAME_COURSE_TITLE)));
            course.setLecturer_name(cursor1.getString(cursor1.getColumnIndex(DbContract.Course.COLUMN_NAME_COURSE_TEACHER)));
            course.setSection(cursor1.getInt(cursor1.getColumnIndex(DbContract.Course.COLUMN_NAME_COURSE_SECTION)));
            course.setColor(cursor1.getInt(cursor1.getColumnIndex(DbContract.Course.COLUMN_NAME_COLOR)));
            courses1.add(course);
        }
        Cursor cursor = db.rawQuery("Select * from " + DbContract.TimeTable.TABLE_NAME + ";", null);
        while (cursor.moveToNext()) {
            Course course = new Course();
            String course_title = cursor.getString(cursor.getColumnIndex(DbContract.TimeTable.COLUMN_NAME_COURSE_TITLE));
            String course_day = cursor.getString(cursor.getColumnIndex(DbContract.TimeTable.COLUMN_NAME_DAY));
            String course_time = cursor.getString(cursor.getColumnIndex(DbContract.TimeTable.COLUMN_NAME_TIME));
            String course_location = cursor.getString(cursor.getColumnIndex(DbContract.TimeTable.COLUMN_NAME_LOCATION));
            course.setName(course_title);
            for (Course c : courses1) {
                if (c.getName().equals(course.getName())) {
                    course.setColor(c.getColor());
                    course.setSection(c.getSection());
                    course.setLecturer_name(c.getLecturer_name());
                }
            }
            course.getDays().add(course_day);
            course.getLocations().add(course_location);
            course.getTime_ranges().add(course_time);
            courses.add(course);
        }
        cursor.close();

//        for(Course c: courses){
//            if(!titles.contains(c.getName())){
//                titles.add(c.getName());
//            }
//        }
//        for(int i=0; i<titles.size(); i++){
//            title_color_mapping.put(titles.get(i), allcolors[i]);
//        }
//        for(Course c: courses){
//            c.setColor(Color.parseColor(title_color_mapping.get(c.getName())));
//        }
        db.close();
        return courses;
    }

    public int getLastNoteId()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(" Select MAX("+DbContract.Notes.NOTE_ID+")as mas from " + DbContract.Notes.TABLE_NOTES, null);
        int noteId=0;
        while (cursor.moveToNext())
        {
            noteId = cursor.getInt(cursor.getColumnIndex("mas"));
        }

        return noteId;
    }

    public ArrayList<Course> get_Courses(Context context)
    {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Course> courses = new ArrayList<Course>();

        Cursor cursor1 = db.rawQuery("Select * from " + DbContract.Course.TABLE_NAME + ";", null);
        while (cursor1.moveToNext())
        {
            Course course = new Course();
            course.setName(cursor1.getString(cursor1.getColumnIndex(DbContract.Course.COLUMN_NAME_COURSE_TITLE)));
            course.setLecturer_name(cursor1.getString(cursor1.getColumnIndex(DbContract.Course.COLUMN_NAME_COURSE_TEACHER)));
            course.setSection(cursor1.getInt(cursor1.getColumnIndex(DbContract.Course.COLUMN_NAME_COURSE_SECTION)));
            course.setColor(cursor1.getInt(cursor1.getColumnIndex(DbContract.Course.COLUMN_NAME_COLOR)));
            courses.add(course);
        }
        cursor1.close();
        db.close();
        return courses;
    }

    public String[] getCourseNames(Context con) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from courses";
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<String> spinnerContent = new ArrayList<String>();

        if (cursor.moveToFirst()) {
            do {
                String word = cursor.getString(cursor.getColumnIndexOrThrow("CourseTitle"));
                spinnerContent.add(word);
            } while (cursor.moveToNext());
        }

        cursor.close();

        String[] allSpinner = new String[spinnerContent.size()];
        allSpinner = spinnerContent.toArray(allSpinner);


        return allSpinner;


    }
}
