package com.cust.sammar.fyp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.cust.sammar.fyp.Activities.ManualTimetable.timetableMainActivity;
import com.cust.sammar.fyp.R;

public class MainActivity extends AppCompatActivity {
    ImageButton importPortalBtn,manaulBtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        importPortalBtn=(ImageButton)findViewById(R.id.portalBtn);
        manaulBtn=(ImageButton)findViewById(R.id.manualTimeTableBtn);

        manaulBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this, timetableMainActivity.class);
                startActivity(in);

            }
        });

        importPortalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,PortalViewActivity.class);
                startActivity(i);

            }
        });
    }
}

