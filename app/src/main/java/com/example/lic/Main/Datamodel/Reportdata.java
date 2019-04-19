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
    @SerializedName("Profit")
    private int profit;


    public Reportdata(String date, int tid, String customername, String pid, int quantity, int cp, int sp, int profit) {
        this.date = date;
        this.tid = tid;
        this.customername = customername;
        this.pid = pid;
        this.quantity = quantity;
        this.cp = cp;
        this.sp = sp;
        this.profit = profit;
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

    public int getProfit() {
        return profit;
    }
}
