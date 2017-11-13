package com.example.ifsp.apeiara.model;

/**
 * Created by Henrique on 3/22/2016.
 */
public class Request {

    private int ID;
    private String DATE;
    private String TYPE;
    private String STATUS;
    private String LATITUDE;
    private String LONGITUDE;
    private User CUIDANDO;
    private User CUIDADOR;


    public Request(){
        this.ID=0;
        this.DATE=null;
        this.TYPE=null;
        this.STATUS=null;
        this.LATITUDE=null;
        this.LONGITUDE=null;
        this.CUIDANDO=null;
        this.CUIDADOR=null;

    }

    public Request(int ID, String DATE, String TYPE, String STATUS, String LATITUDE,String LONGITUDE, User CUIDANDO, User CUIDADOR) {
        this.ID = ID;
        this.DATE = DATE;
        this.TYPE = TYPE;
        this.STATUS = STATUS;
        this.LONGITUDE = LONGITUDE;
        this.LATITUDE = LATITUDE;
        this.CUIDANDO = CUIDANDO;
        this.CUIDADOR = CUIDADOR;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getLONGITUDE() {
        return LONGITUDE;
    }

    public void setLONGITUDE(String LONGITUDE) {
        this.LONGITUDE = LONGITUDE;
    }

    public String getLATITUDE() {
        return LATITUDE;
    }

    public void setLATITUDE(String LATITUDE) {
        this.LATITUDE = LATITUDE;
    }

    public User getCUIDANDO() {
        return CUIDANDO;
    }

    public void setCUIDANDO(User CUIDANDO) {
        this.CUIDANDO = CUIDANDO;
    }

    public User getCUIDADOR() {
        return CUIDADOR;
    }

    public void setCUIDADOR(User CUIDADOR) {
        this.CUIDADOR = CUIDADOR;
    }
}
