package com.example.lic.Main.Datamodel;

import com.google.gson.annotations.SerializedName;

public class Reportdata {


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
