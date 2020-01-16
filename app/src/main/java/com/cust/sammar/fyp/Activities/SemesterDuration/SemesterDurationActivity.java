package com.cust.sammar.fyp.Activities.SemesterDuration;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.cust.sammar.fyp.R;

import java.text.DateFormat;
import java.util.Calendar;

public class SemesterDurationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    Button startBtn,next;
    TextView starttxt;

    String startDateString,endDateString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_duration);
        startBtn=(Button)findViewById(R.id.startBtn);


        starttxt=(TextView)findViewById(R.id.starttxt);
        starttxt.setVisibility(View.INVISIBLE);
        next=(Button)findViewById(R.id.next);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker=new DatePickerfragment();
                datePicker.show(getSupportFragmentManager(),"Date Picker");


            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(SemesterDurationActivity.this,SemesterDurationEndDefineActivity.class);
                startActivity(in);
            }
        });


    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar c=Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,day);
        startDateString= DateFormat.getDateInstance().format(c.getTime());
        starttxt.setVisibility(View.VISIBLE);
        starttxt.setText(startDateString);





    }


}
