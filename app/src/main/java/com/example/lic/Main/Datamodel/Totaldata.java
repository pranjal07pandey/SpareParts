package com.example.lic.Main.Datamodel;

import com.google.gson.annotations.SerializedName;

public class Totaldata {

    @SerializedName("profit")
    private int profittotal;
    @SerializedName("sp")
    private int sellingpricetotal;
    @SerializedName("cp")
    private int costpricetotal;

    public Totaldata(int profittotal, int sellingpricetotal, int costpricetotal) {
        this.profittotal = profittotal;
        this.sellingpricetotal = sellingpricetotal;
        this.costpricetotal = costpricetotal;
    }

    public int getProfittotal() {
        return profittotal;
    }

    public int getSellingpricetotal() {
        return sellingpricetotal;
    }

    public int getCostpricetotal() {
        return costpricetotal;
    }
}
