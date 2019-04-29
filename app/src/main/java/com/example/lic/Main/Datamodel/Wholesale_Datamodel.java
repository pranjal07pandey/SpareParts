package com.example.lic.Main.Datamodel;

import com.google.gson.annotations.SerializedName;

public class Wholesale_Datamodel {

    private String name;
    private String contact;
    @SerializedName("address")
    private String address;

    public Wholesale_Datamodel(String name, String contact, String address) {
        this.name = name;
        this.contact = contact;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }
}
