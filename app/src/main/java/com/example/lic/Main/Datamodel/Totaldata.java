package com.example.lic.Main.Datamodel;

import com.google.gson.annotations.SerializedName;

public class Totaldata {

    @SerializedName("profit")
    private int profittotal;
    @SerializedName("sp")
    private int sellingpricetotal;
    @SerializedName("cp")
    private int costpricetotal;
    @SerializedName("inventory_quantity")
    private int quantitytotalinventory;
    @SerializedName("inventory_cp")
    private int cp_inventory;
    @SerializedName("inventory_mp")
    private int mp_inventory;
    @SerializedName("import_quantity")
    private int import_quantitytotal;
    @SerializedName("import_cp")
    private int import_cp;
    @SerializedName("import_mp")
    private int import_mp;


    public Totaldata(int profittotal, int sellingpricetotal, int costpricetotal, int quantitytotalinventory, int cp_inventory, int mp_inventory, int import_quantitytotal, int import_cp, int import_mp) {
        this.profittotal = profittotal;
        this.sellingpricetotal = sellingpricetotal;
        this.costpricetotal = costpricetotal;
        this.quantitytotalinventory = quantitytotalinventory;
        this.cp_inventory = cp_inventory;
        this.mp_inventory = mp_inventory;
        this.import_quantitytotal = import_quantitytotal;
        this.import_cp = import_cp;
        this.import_mp = import_mp;
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

    public int getQuantitytotalinventory() {
        return quantitytotalinventory;
    }

    public int getCp_inventory() {
        return cp_inventory;
    }

    public int getMp_inventory() {
        return mp_inventory;
    }

    public int getImport_quantitytotal() {
        return import_quantitytotal;
    }

    public int getImport_cp() {
        return import_cp;
    }

    public int getImport_mp() {
        return import_mp;
    }
}
