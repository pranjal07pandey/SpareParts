package com.example.lic.Main.Datamodel;

import com.google.gson.annotations.SerializedName;

public class Reportdata {
//    @SerializedName("Date")
//    private String date;
//    @SerializedName("Transaction_Id")
//    private int tid;
//    private String customername;
//    @SerializedName("Code")
//    private String pid;
//    @SerializedName("Quantity")
//    private int quantity;
//    @SerializedName("Cost_Price")
//    private int cp;
//    @SerializedName("Selling_Price")
//    private int sp;
//    @SerializedName("Marked_Price")
//    private int mp;
//    @SerializedName("Profit")
//    private int profit;
//    @SerializedName("Wholeseller")
//    private String wholeseller;
//    @SerializedName("Color")
//    private String color;
//    @SerializedName("Size")
//    private String size;
//    @SerializedName("Brand")
//    private String Brand;
//    @SerializedName("Type")
//    private String Type;
//
//
//    public Reportdata(String date, int tid, String customername, String pid, int quantity, int cp, int sp, int mp, int profit, String wholeseller, String color, String size, String brand, String type) {
//        this.date = date;
//        this.tid = tid;
//        this.customername = customername;
//        this.pid = pid;
//        this.quantity = quantity;
//        this.cp = cp;
//        this.sp = sp;
//        this.mp = mp;
//        this.profit = profit;
//        this.wholeseller = wholeseller;
//        this.color = color;
//        this.size = size;
//        Brand = brand;
//        Type = type;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public int getTid() {
//        return tid;
//    }
//
//    public String getCustomername() {
//        return customername;
//    }
//
//    public String getPid() {
//        return pid;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public int getCp() {
//        return cp;
//    }
//
//    public int getSp() {
//        return sp;
//    }
//
//    public int getMp() {
//        return mp;
//    }
//
//    public int getProfit() {
//        return profit;
//    }
//
//    public String getWholeseller() {
//        return wholeseller;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    public String getSize() {
//        return size;
//    }
//
//    public String getBrand() {
//        return Brand;
//    }
//
//    public String getType() {
//        return Type;
//    }

    private String date;
    private String name;
    private String product_id;
    private int quantity;
    private int cp;
    private int mp;
    private int sp;
    private String wholesaler;
    private int transaction_id;
    private String customer_name;
    private int labour_charge;
    private int profit;


    public String getDate() {
        return date;
    }

    public int getProfit(){
        return profit;
    }

    public String getName() {
        return name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCp() {
        return cp;
    }

    public int getMp() {
        return mp;
    }

    public int getSp() {
        return sp;
    }

    public String getWholesaler() {
        return wholesaler;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public int getLabour_charge() {
        return labour_charge;
    }

    public Reportdata(String date, String name, String product_id, int quantity, int cp, int mp, int sp, String wholesaler, int transaction_id, String customer_name, int labour_charge, int profit) {
        this.date = date;
        this.name = name;
        this.product_id = product_id;
        this.quantity = quantity;
        this.cp = cp;
        this.mp = mp;
        this.sp = sp;
        this.wholesaler = wholesaler;
        this.transaction_id = transaction_id;
        this.customer_name = customer_name;
        this.labour_charge = labour_charge;
        this.profit = profit;


    }
}
