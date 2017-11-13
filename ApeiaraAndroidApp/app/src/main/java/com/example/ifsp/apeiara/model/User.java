package com.example.ifsp.apeiara.model;

/**
 * Created by Henrique on 3/22/2016.
 */
public class User {

    private int ID;
    private String NAME;
    private String CATEGORY;
    private String EMAIL;
    private String PASS;
    private String PHONE;


    public User() {
        this.ID=0;
        this.NAME=null;
        this.CATEGORY=null;
        this.EMAIL=null;
        this.PASS=null;
        this.PHONE=null;
    }

    public User(int ID, String NAME, String CATEGORY, String EMAIL, String PASS, String PHONE) {
        this.ID = ID;
        this.NAME = NAME;
        this.CATEGORY = CATEGORY;
        this.EMAIL = EMAIL;
        this.PHONE = PHONE;
        this.PASS = PASS;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getCATEGORY() {
        return CATEGORY;
    }

    public void setCATEGORY(String CATEGORY) {
        this.CATEGORY = CATEGORY;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPASS() {
        return PASS;
    }

    public void setPASS(String PASS) {
        this.PASS = PASS;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }
}





