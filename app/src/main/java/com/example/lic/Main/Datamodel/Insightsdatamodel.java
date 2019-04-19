package com.example.lic.Main.Datamodel;

public class Insightsdatamodel {

    private int sp;
    private int duration;
    private String name;
    private String date;
    private String label;

    public Insightsdatamodel(int sp, int duration, String name, String date, String label) {
        this.sp = sp;
        this.duration = duration;
        this.name = name;
        this.date = date;
        this.label = label;
    }

    public int getSp() {
        return sp;
    }

    public int getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getLabel() {
        return label;
    }
}
