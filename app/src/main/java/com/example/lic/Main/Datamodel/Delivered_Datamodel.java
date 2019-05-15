package com.example.lic.Main.Datamodel;

public class Delivered_Datamodel {

    private String name;
    private String code;
    private String quantity;
    private String address;
    private String contact;
    private String delivery_date;
    private String sp;
    private String color;
    private String size;

    public Delivered_Datamodel(String name, String code, String quantity, String address, String contact, String delivery_date
            ,String sp, String color, String size) {

        this.name = name;
        this.code = code;
        this.quantity = quantity;
        this.address = address;
        this.contact = contact;
        this.delivery_date = delivery_date;
        this.sp = sp;
        this.color = color;
        this.size = size;
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


    public String getDelivery_date() {
        return delivery_date;
    }

    public String getSp() {
        return sp;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }
}
