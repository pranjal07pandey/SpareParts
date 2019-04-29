package com.example.lic.Main.Datamodel;

public class Delivered_Datamodel {

    private String name;
    private String code;
    private String quantity;
    private String address;
    private String contact;

    public Delivered_Datamodel(String name, String code, String quantity, String address, String contact) {
        this.name = name;
        this.code = code;
        this.quantity = quantity;
        this.address = address;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }
}
