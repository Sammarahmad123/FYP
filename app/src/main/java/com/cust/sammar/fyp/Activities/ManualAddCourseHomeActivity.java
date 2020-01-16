package com.cust.sammar.fyp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cust.sammar.fyp.R;

public class ManualAddCourseHomeActivity extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_add_course_home);

        btn=(Button)findViewById(R.id.addCourse);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(ManualAddCourseHomeActivity.this,AddCourseDetailsActivity.class);
                startActivity(in);

            }
        });
    }
}
