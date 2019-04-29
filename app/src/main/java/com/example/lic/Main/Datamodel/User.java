package com.example.lic.Main.Datamodel;

public class User {

    private int id;
    private String userid;
    private String password;
    private String cname;
    private int error;
    private int expirein;


    public User(int id, String userid, String password, String cname, int error, int expirein) {
        this.id = id;
        this.userid = userid;
        this.password = password;
        this.cname = cname;
        this.error = error;
        this.expirein = expirein;
    }

    public int getId() {
        return id;
    }

    public String getUserid() {
        return userid;
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
