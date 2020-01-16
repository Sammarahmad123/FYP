package com.cust.sammar.fyp.Activities;

import java.util.ArrayList;

public class Course {
    ArrayList<String> time_ranges;
    ArrayList<String> days;
    String name;
    String lecturer_name;
    ArrayList<String> locations;
    int section;
    int color;

    public Course(ArrayList<String> time_ranges, ArrayList<String> days, String name, String lecturer_name, ArrayList<String> locations, int section) {
        this.time_ranges = time_ranges;
        this.days = days;
        this.name = name;
        this.lecturer_name = lecturer_name;
        this.locations = locations;
        this.section = section;
    }


    public Course(){
        this.days = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.time_ranges = new ArrayList<>();
    }

    public ArrayList<String> getTime_ranges() {
        return time_ranges;
    }

    public void setTime_ranges(ArrayList<String> time_ranges) {
        this.time_ranges = time_ranges;
    }

    public ArrayList<String> getDays() {
        return days;
    }

    public void setDays(ArrayList<String> days) {
        this.days = days;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLecturer_name() {
        return lecturer_name;
    }

    public void setLecturer_name(String lecturer_name) {
        this.lecturer_name = lecturer_name;
    }

    public ArrayList<String> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<String> locations) {
        this.locations = locations;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
