package com.cust.sammar.fyp.Activities.Notes.CourseList;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cust.sammar.fyp.Activities.Notes.CourseList.voiceNote.activities.VoiceNoteMainActivity;
import com.cust.sammar.fyp.R;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import Database.DatabaseHelper;

public class NoteEditorActivity extends AppCompatActivity {

    Note note=new Note();
    Bundle bundle;
    EditText title;
    EditText text;
    TextView textCount;
    String courseTitle;
    FloatingActionButton saveNoteBtn;
    DatabaseHelper databaseHelper;
    private int REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        saveNoteBtn =findViewById(R.id.saveNoteBtn);
        saveNoteBtn.setEnabled(false);
        databaseHelper=new DatabaseHelper(getApplicationContext());

        title=findViewById(R.id.noteTitle);
        text=findViewById(R.id.noteText);
        title.addTextChangedListener(notetypedWatcher);
        textCount = findViewById(R.id.textCount);
        text.addTextChangedListener(textWatcher);

        checkPermission();


        Bundle bundle=getIntent().getExtras();
        courseTitle=bundle.getString("CourseName");
        note.setCoursetitle(courseTitle);
        String action= bundle.getString("Action");
        if(action.equalsIgnoreCase("update"))
        {
            String t1 = bundle.getString("NoteText");
            String t2 = bundle.getString("NoteTitle");
            text.setText(t1);
            title.setText(t2);
        }

        saveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                note.setNoteText(text.getText().toString());
                note.setTitle(title.getText().toString());
                note.setNoteType("Text");
                if(action.equalsIgnoreCase("insert"))
                {
                    databaseHelper.insertNote(note);
                    Toast.makeText(NoteEditorActivity.this,"Note Inserted Successfully",Toast.LENGTH_SHORT).show();
                    String fileName= note.getCoursetitle();
                    int noteId = databaseHelper.getLastNoteId();
                    fileName = fileName+noteId+".txt";
                    createDirectoryandSaveFile(fileName,note.getCoursetitle(),note.getNoteText());
                    onBackPressed();
                }
                else if(action.equalsIgnoreCase("update"))
                {
                    note.setId(bundle.getInt("NoteId"));
                    databaseHelper.updateNote(note);
                    String fileName= note.getCoursetitle();
                    fileName = fileName+note.getId()+".txt";
                    createDirectoryandSaveFile(fileName,note.getCoursetitle(),note.getNoteText());
                    Toast.makeText(NoteEditorActivity.this,"Note Updated Successfully",Toast.LENGTH_SHORT).show();
                    onBackPressed();

                }

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId())
        {
            case android.R.id.home:
                Intent myIntent = new Intent();
                myIntent.putExtra("CourseName",courseTitle);
                setResult(NoteEditorActivity.RESULT_OK, myIntent);
                finish();
        }
        return true;
    }

    public void createDirectoryandSaveFile(String fileName,String CourseName,String NoteText)
    {

        File directory = new File(Environment.getExternalStorageDirectory() +"/StudyMate/"+CourseName);

        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(directory, fileName);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fileOutputStream = null;

        try {

            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(NoteText.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void checkPermission()
    {
        int permission = ContextCompat.checkSelfPermission(NoteEditorActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i("ABC", "Permission to Write is denied");
            makeRequest();
        }
    }
    protected void makeRequest() {
        ActivityCompat.requestPermissions(NoteEditorActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
    
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int count, int i2){

            String te= text.getText().toString();
            textCount.setText(String.valueOf(te.length()));

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private TextWatcher notetypedWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            String titleText=title.getText().toString();
            saveNoteBtn.setEnabled(!titleText.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
