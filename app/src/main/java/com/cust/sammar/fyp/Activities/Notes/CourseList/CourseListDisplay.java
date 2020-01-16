package com.cust.sammar.fyp.Activities.Notes.CourseList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.cust.sammar.fyp.Activities.Course;
import com.cust.sammar.fyp.R;

import java.util.List;

import Database.DatabaseHelper;

public class CourseListDisplay extends AppCompatActivity {

    ListView listView;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list_display);
        listView=findViewById(R.id.course_display_listview);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        List<Course> courseList=databaseHelper.get_Courses(getApplicationContext());

        CoursesListviewAdapter adapter=new CoursesListviewAdapter(this,R.layout.courses_listview_adapter,courseList);

        listView.setAdapter(adapter);






    }
}
