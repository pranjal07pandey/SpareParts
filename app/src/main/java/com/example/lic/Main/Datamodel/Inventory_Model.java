package com.example.lic.Main.Datamodel;

import com.google.gson.annotations.SerializedName;

public class Inventory_Model {


    private String name;
    private String product_id;
    private int quantity;
    private int cp;
    private int mp;
    private String wholesaler;


    public Inventory_Model(String name, String product_id, int quantity, int cp, int mp, String wholesaler) {
        this.name = name;
        this.product_id = product_id;
        this.quantity = quantity;
        this.cp = cp;
        this.mp = mp;
        this.wholesaler = wholesaler;
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

    public String getWholesaler() {
        return wholesaler;
    }
}
