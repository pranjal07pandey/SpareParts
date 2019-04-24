package com.example.lic.Main.Datamodel;

import com.google.gson.annotations.SerializedName;

public class Reportdata {
    @SerializedName("Date")
    private String date;
    @SerializedName("Transaction_Id")
    private int tid;
    private String customername;
    @SerializedName("Code")
    private String pid;
    @SerializedName("Quantity")
    private int quantity;
    @SerializedName("Cost_Price")
    private int cp;
    @SerializedName("Selling_Price")
    private int sp;
    @SerializedName("Marked_Price")
    private int mp;
    @SerializedName("Profit")
    private int profit;
    @SerializedName("Wholeseller")
    private String wholeseller;
    @SerializedName("Color")
    private String color;
    @SerializedName("Size")
    private String size;
    @SerializedName("Brand")
    private String Brand;
    @SerializedName("Type")
    private String Type;


    public Reportdata(String date, int tid, String customername, String pid, int quantity, int cp, int sp, int mp, int profit, String wholeseller, String color, String size, String brand, String type) {
        this.date = date;
        this.tid = tid;
        this.customername = customername;
        this.pid = pid;
        this.quantity = quantity;
        this.cp = cp;
        this.sp = sp;
        this.mp = mp;
        this.profit = profit;
        this.wholeseller = wholeseller;
        this.color = color;
        this.size = size;
        Brand = brand;
        Type = type;
    }

    public String getDate() {
        return date;
    }

    public int getTid() {
        return tid;
    }

    public String getCustomername() {
        return customername;
    }

    public String getPid() {
        return pid;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCp() {
        return cp;
    }

    public int getSp() {
        return sp;
    }

    public int getMp() {
        return mp;
    }

    public int getProfit() {
        return profit;
    }

    public String getWholeseller() {
        return wholeseller;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public String getBrand() {
        return Brand;
    }

    public String getType() {
        return Type;
    }
}
