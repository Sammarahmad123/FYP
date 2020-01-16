package com.cust.sammar.fyp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.cust.sammar.fyp.R;

public class TimetableDetailsActivity extends AppCompatActivity {
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_details);

        spinner=(Spinner)findViewById(R.id.spinner);
        String[] lecturesCount={"2","3","4"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,lecturesCount);
        spinner.setAdapter(adapter);

    }
}
