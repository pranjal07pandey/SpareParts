package com.example.lic.Main.Datamodel;

public class User {

    private int id;
    private String username;
    private String password;
    private String cname;
    private int error;
    private int expirein;


    public User(int id, String username, String password, String cname, int error, int expirein) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.cname = cname;
        this.error = error;
        this.expirein = expirein;
    }

    public int getId() {
        return id;
    }

    public String getUserid() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCname() {
        return cname;
    }

    public int getError() {
        return error;
    }

    public int getExpirein() {
        return expirein;
    }
}
