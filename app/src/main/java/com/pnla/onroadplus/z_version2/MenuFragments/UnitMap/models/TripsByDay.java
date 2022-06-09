package com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models;

public class TripsByDay {//estas son las de las imagenes

    private double latitude;
    private int orden;
    private double longitude;
    private String sendtime;


    public TripsByDay(double latitude,int orden,double longitude,String sendtime)
    {
        super();
        this.latitude=latitude;
        this.orden=orden;
        this.longitude=longitude;
        this.sendtime=sendtime;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

}
