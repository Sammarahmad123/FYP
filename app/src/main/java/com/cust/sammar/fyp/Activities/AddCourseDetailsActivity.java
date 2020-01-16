package com.cust.sammar.fyp.Activities;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.cust.sammar.fyp.R;

public class AddCourseDetailsActivity extends AppCompatActivity {
    private LinearLayout parentLinearLayout;

    public void addClass(View v)
    {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.course_time_details, null);
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount()-2);
    }
    public void proceed(View v)
    {
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course_details);
        parentLinearLayout = findViewById(R.id.parent_linear_layout);



//        spinner = findViewById(R.id.spinner);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                String selected = spinner.getSelectedItem().toString();
//                int select= Integer.parseInt(selected);
//                if(parentLinearLayout.getChildCount()>5)
//                {
//                    parentLinearLayout.removeViews(4,parentLinearLayout.getChildCount()-5);
//                }
//                for (int i = 0; i < select; i++)
//                {
//
//                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                    final View rowView = inflater.inflate(R.layout.course_time_details, null);
//                    parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount()-1);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//                // your code here
//            }
//
//        });

    }
}
