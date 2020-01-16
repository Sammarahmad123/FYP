package com.cust.sammar.fyp.Activities.Notes.CourseList;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cust.sammar.fyp.Activities.Reminders.ReminderActivity;
import com.cust.sammar.fyp.R;

import java.util.List;

public class TextNotesListAdapter extends ArrayAdapter<Note>
{
    Context mCtx;
    int resource;
    List<Note> noteList;

    public TextNotesListAdapter(Context context, int resource, List<Note> courseList) {
        super(context, resource, courseList);
        this.mCtx = context;
        this.resource = resource;
        this.noteList = courseList;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.text_note_list_adapter,null);
        TextView noteTitleTV=view.findViewById(R.id.noteTitleTV);
        TextView noteTextTv=view.findViewById(R.id.description);
        TextView dateCreated=view.findViewById(R.id.dateCreated);
        TextView NoteId=view.findViewById(R.id.noteId);
        TextView setReminder = view.findViewById(R.id.setReminder);
        setReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mCtx, ReminderActivity.class);
                intent.putExtra("CourseName",noteTitleTV.getText().toString());
                intent.putExtra("description",noteTextTv.getText().toString());
                mCtx.startActivity(intent);
            }
        });

        Note note=noteList.get(position);
        NoteId.setText(""+note.getId());
        noteTitleTV.setText(note.getTitle());
        noteTextTv.setText(note.getNoteText());
        dateCreated.setText(note.getNoteCreated());
        return view;

    }
}
