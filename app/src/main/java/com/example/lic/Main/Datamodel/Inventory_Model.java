package com.example.lic.Main.Datamodel;

import com.google.gson.annotations.SerializedName;

public class Inventory_Model {

    @SerializedName("code")
    private String productID;
    private int id;
    private String date;
    private String type;
    @SerializedName("cp")
    private int costprice;
    @SerializedName("mp")
    private int markedprice;
    private String brand;
    private String category;
    private String wholesaler;
    private String color;
    private String size;
    @SerializedName("quantity")
    private int quantity;

    public Inventory_Model(String productID, int id, String date, String type, int costprice, int markedprice, String brand, String category, String wholesaler, String color, String size, int quantity) {
        this.productID = productID;
        this.id = id;
        this.date = date;
        this.type = type;
        this.costprice = costprice;
        this.markedprice = markedprice;
        this.brand = brand;
        this.category = category;
        this.wholesaler = wholesaler;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
    }

    public String getProductID() {
        return productID;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public int getCostprice() {
        return costprice;
    }

    public int getMarkedprice() {
        return markedprice;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
    }

    public String getWholesaler() {
        return wholesaler;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }
}
