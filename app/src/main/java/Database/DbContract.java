package Database;

public final class DbContract {

    private DbContract(){}

    public static class Course
    {
        public static final String TABLE_NAME = "Courses";
        public static final String COLUMN_NAME_COURSE_TITLE = "CourseTitle";
        public static final String COLUMN_NAME_COURSE_TEACHER = "CourseTeacher";
        public static final String COLUMN_NAME_COURSE_SECTION = "courseSection";
        public static final String COLUMN_NAME_COLOR="color";

        public static final String CREATE_TABLE_STRING = "CREATE TABLE " + Course.TABLE_NAME + " (" +
                Course.COLUMN_NAME_COURSE_TITLE + " varchar(50) not null," +
                Course.COLUMN_NAME_COURSE_TEACHER + " varchar(50) not null," +
                Course.COLUMN_NAME_COURSE_SECTION + " int(5) not null," +
                Course.COLUMN_NAME_COLOR+" INTEGER,"+
                "primary key("+Course.COLUMN_NAME_COURSE_TITLE+"));";
    }

    public static class TimeTable{
        public static final String TABLE_NAME = "timetable";
        public static final String COLUMN_NAME_DAY= "Day";
        public static final String COLUMN_NAME_COURSE_TITLE = "CourseTitle";
        public static final String COLUMN_NAME_LOCATION = "Location";
        public static final String COLUMN_NAME_TIME = "Time";

        public static final String CREATE_TABLE_STRING = "CREATE TABLE " + TimeTable.TABLE_NAME+ " (" +
                TimeTable.COLUMN_NAME_DAY+ " varchar(50) not null," +
                TimeTable.COLUMN_NAME_COURSE_TITLE + " varchar(50) null," +
                TimeTable.COLUMN_NAME_LOCATION+ " varchar(5) null," +
                TimeTable.COLUMN_NAME_TIME+ " Time null," +
                "PRIMARY KEY("+TimeTable.COLUMN_NAME_DAY+","+TimeTable.COLUMN_NAME_COURSE_TITLE+","+TimeTable.COLUMN_NAME_TIME+")," +
                "FOREIGN Key("+TimeTable.COLUMN_NAME_COURSE_TITLE+") REFERENCES "+Course.TABLE_NAME+"("+Course.COLUMN_NAME_COURSE_TITLE+"));";


    }
    public static class Notes{
        public static final String TABLE_NOTES="notes";
        public static final String NOTE_ID="id";
        public static final String NOTE_TITLE="noteTitle";
        public static final String COURSE_TITLE = "courseTitle";
        public static final String NOTE_TEXT="description";
        public static final String NOTE_TYPE="noteType";
        public static final String NOTE_CREATED="noteCreated";

        public static final String[] AllColumns={NOTE_ID,NOTE_TITLE,NOTE_TYPE,NOTE_CREATED,NOTE_TEXT,COURSE_TITLE};

        public static final String CREATE_TABLE_NOTES =
                "CREATE TABLE "+TABLE_NOTES+" ("+
                        NOTE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        NOTE_TITLE+" TEXT, "+
                        COURSE_TITLE+" varchar(50) null,"+
                        NOTE_TEXT+" varchar(200) null, "+
                        NOTE_TYPE+" TEXT, "+
                        NOTE_CREATED+" TEXT default (datetime('now','localtime')),"+
                        "FOREIGN Key("+Notes.COURSE_TITLE+") REFERENCES "+Course.TABLE_NAME+"("+Course.COLUMN_NAME_COURSE_TITLE+")"+
                        "); ";
    }




}
