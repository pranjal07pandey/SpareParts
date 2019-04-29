package com.example.lic.Main.Datamodel;

import com.example.lic.Main.main.Credit;

public class Credit_Datamodel {

    private String name;
    private String contact;
    private String advance;
    private String sp;

    public Credit_Datamodel(String name, String contact) {
        this.name = name;
        this.contact = contact;
        this.advance = advance;
        this.sp = sp;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }


    public String getAdvance() {
        return advance;
    }

    public String getSp() {
        return sp;
    }
}
