package com.cust.sammar.fyp.Activities.Notes.CourseList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.cust.sammar.fyp.Activities.Notes.CourseList.voiceNote.activities.VoiceNoteMainActivity;
import com.cust.sammar.fyp.Activities.Notes.ImageNotesActivity;
import com.cust.sammar.fyp.R;

public class ChooseNoteType extends AppCompatActivity {

    ImageButton textNoteBtn,imageNoteBtn,voiceNoteBtn;
    String courseTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_note_type);
        textNoteBtn=findViewById(R.id.textNoteBtn);
        imageNoteBtn=findViewById(R.id.imageNoteBtn);
        voiceNoteBtn=findViewById(R.id.voiceNoteBtn);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
           courseTitle=bundle.getString("CourseName");
            Log.d("ExtractBundelName",courseTitle);
        }

    }
    public void textNoteBtnClick(View v)
    {
        Intent intent = new Intent(ChooseNoteType.this,TextNoteMainActivity.class);
        intent.putExtra("CourseName",courseTitle);
        startActivity(intent);
    }
    public void imageNoteBtnClick(View v)
    {
        Intent intent = new Intent(ChooseNoteType.this, ImageNotesActivity.class);
        intent.putExtra("CourseName",courseTitle);
        startActivity(intent);
    }
    public void voiceNoteBtnClick(View v)
    {
        Intent intent = new Intent(ChooseNoteType.this, VoiceNoteMainActivity.class);
        intent.putExtra("CourseName",courseTitle);
        startActivity(intent);

    }
}
