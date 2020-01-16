package com.cust.sammar.fyp.Activities.Notes.CourseList;


public class Note {

    private String Coursetitle;
    private String title;
    private String noteText;
    private String noteType;
    private String noteCreated;
    private int id;

    public Note(String coursetitle, String title, String noteText, String noteType, String noteCreated, int id) {
        Coursetitle = coursetitle;
        this.title = title;
        this.noteText = noteText;
        this.noteType = noteType;
        this.noteCreated = noteCreated;
        this.id = id;
    }



    public String getCoursetitle() {
        return Coursetitle;
    }

    public void setCoursetitle(String coursetitle) {
        Coursetitle = coursetitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }

    public String getNoteCreated() {
        return noteCreated;
    }

    public void setNoteCreated(String noteCreated) {
        this.noteCreated = noteCreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Note() {
    }


}
