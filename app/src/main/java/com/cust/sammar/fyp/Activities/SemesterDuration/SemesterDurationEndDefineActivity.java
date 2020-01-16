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

import com.cust.sammar.fyp.Activities.MainActivity;
import com.cust.sammar.fyp.R;

import java.text.DateFormat;
import java.util.Calendar;

public class SemesterDurationEndDefineActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    Button endBtn,save;
    TextView endtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_duration_end_define);

        endBtn=(Button)findViewById(R.id.endBtn);


        endtxt=(TextView)findViewById(R.id.endtxt);
        save=(Button)findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in =new Intent(SemesterDurationEndDefineActivity.this, MainActivity.class);
                startActivity(in);
            }
        });


        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker=new DatePickerfragment();
                datePicker.show(getSupportFragmentManager(),"Date Picker");


            }
        });

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar c=Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,day);
        String endDateString= DateFormat.getDateInstance().format(c.getTime());
        endtxt.setText(endDateString);





    }


}
