package com.cust.sammar.fyp.Activities.Notes.CourseList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cust.sammar.fyp.R;

import java.io.File;
import java.util.ArrayList;
import Database.DatabaseHelper;
public class TextNoteMainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    Bundle bundle;
    String courseTitle;
    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_note_main);

        bundle = getIntent().getExtras();
        if (bundle != null)
        {
            courseTitle = bundle.getString("CourseName");
        }
        databaseHelper = new DatabaseHelper(getApplicationContext());
        setListAdapter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==111 && resultCode==RESULT_OK)
        {
            courseTitle=data.getExtras().getString("CourseName");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setListAdapter();


    }

    public void setListAdapter()
    {
        ArrayList<Note> notesList = databaseHelper.fetchNotes(courseTitle);
        if (notesList != null) {
            TextNotesListAdapter textNotesListAdapter = new TextNotesListAdapter(this, R.layout.text_note_list_adapter, notesList);
            ListView listView = findViewById(R.id.textNotesList);
            listView.setAdapter(textNotesListAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView t1 = view.findViewById(R.id.description);
                    TextView t2 = view.findViewById(R.id.noteTitleTV);
                    TextView idtext = view.findViewById(R.id.noteId);
                    Intent intent = new Intent(TextNoteMainActivity.this, NoteEditorActivity.class);
                    intent.putExtra("Action", "update");
                    intent.putExtra("CourseName",courseTitle);
                    intent.putExtra("NoteId", Integer.valueOf(idtext.getText().toString()));
                    intent.putExtra("NoteText", t1.getText().toString());
                    intent.putExtra("NoteTitle", t2.getText().toString());
                    startActivity(intent);

                }
            });

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView idtext = view.findViewById(R.id.noteId);
                    noteId=Integer.valueOf(idtext.getText().toString());

                    AlertDialog.Builder builder = new AlertDialog.Builder(TextNoteMainActivity.this);

                    builder.setTitle("Are You Sure");
                    builder.setMessage("Do You want to delete this note");

                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            databaseHelper.deleteNote(noteId);
                            File file = new File(Environment.getExternalStorageDirectory() +"/StudyMate/"+courseTitle+"/"+courseTitle+noteId+".txt");
                            if(file.exists())
                            {
                                file.delete();
                                Toast.makeText(TextNoteMainActivity.this, "Note Deleted Successfully", Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                            setListAdapter();
                        }
                    });

                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                    return true;
                }
            });



        }
    }
    public void addNote(View view)
    {
        Log.d("TextNoteMainActivity","addNote");
        Intent intent=new Intent(TextNoteMainActivity.this,NoteEditorActivity.class);
        intent.putExtra("CourseName",courseTitle);
        intent.putExtra("Action","insert");
        startActivity(intent);

    }
}
