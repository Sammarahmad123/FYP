package com.cust.sammar.fyp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.cust.sammar.fyp.R;

import Database.DatabaseHelper;

public class AddNotesActivity extends AppCompatActivity {
    DatabaseHelper db;
    Button imageNoeBtn,voiceNoteBtn;
    TextView crsTxt;

   // private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        db=new DatabaseHelper(this);

        //crsTxt=(TextView)findViewById(R.id.show);
        imageNoeBtn=(Button)findViewById(R.id.imageNote);
        voiceNoteBtn=(Button) findViewById(R.id.voicenote);


        imageNoeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*String[] crsNameList=db.getCourseNames(AddNotesActivity.this);
                Spinner spinner = (Spinner) findViewById(R.id.courseList);
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(AddNotesActivity.this,android.R.layout.simple_spinner_item, crsNameList);
                spinner.setAdapter(spinnerAdapter);*/

               // Intent in=new Intent(Add)
                //Intent in=new Intent(AddNotesActivity.this, ImageNotesActivity.class);
//                startActivity(in);

            }
        });
        voiceNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*String[] crsNameList=db.getCourseNames(AddNotesActivity.this);
                Spinner spinner = (Spinner) findViewById(R.id.courseList);
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(AddNotesActivity.this,android.R.layout.simple_spinner_item, crsNameList);
                spinner.setAdapter(spinnerAdapter);*/

                //Intent in=new Intent(AddNotesActivity.this, AddVoiceNoteMainActivity.class);
//                startActivity(in);

            }
        });


    }


}
