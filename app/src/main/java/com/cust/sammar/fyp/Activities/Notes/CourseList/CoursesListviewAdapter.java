package com.cust.sammar.fyp.Activities.Notes.CourseList;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cust.sammar.fyp.Activities.Course;
import com.cust.sammar.fyp.R;

import java.util.List;

public class CoursesListviewAdapter extends ArrayAdapter<Course>
{

    Context mCtx;
    int resource;
    List<Course> courseList;
    ImageButton nextBtn;

    public CoursesListviewAdapter(Context context, int resource, List<Course> courseList) {
        super(context, resource, courseList);
        this.mCtx = context;
        this.resource = resource;
        this.courseList = courseList;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.courses_listview_adapter,null);
        TextView textViewName=view.findViewById(R.id.textViewName);
        Course course=courseList.get(position);
        view.setBackgroundColor(course.getColor());
        textViewName.setText(course.getName());
        nextBtn=view.findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                View vParent= (View) v.getParent();
                TextView textViewName=vParent.findViewById(R.id.textViewName);
                Log.d("NextClick",textViewName.getText().toString());
                ChangeIntent(textViewName.getText().toString());
            }
        });
        return view;

    }
    public void ChangeIntent(String cname)
    {
        Intent intent=new Intent(mCtx,ChooseNoteType.class);
        intent.putExtra("CourseName",cname);
        mCtx.startActivity(intent);
    }

}
