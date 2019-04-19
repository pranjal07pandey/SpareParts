package com.example.lic.Main.Datamodel;

public class Dashboard {
    private String Title;
    private int Hthumbnail;

    public Dashboard(String title, int hthumbnail) {
        Title = title;
        Hthumbnail = hthumbnail;
    }

    public String getTitle() {
        return Title;
    }

    public int getHthumbnail() {
        return Hthumbnail;
    }
}
