package com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models;

public class tripsBytimeData {
    private String latitude;
    private String longitude;
    private String georeference;
    private String sendtime;

    public  tripsBytimeData(String latitude,String longitude,String georeference,String sendtime){
        super();
        this.latitude=latitude;
        this.longitude=longitude;
        this.georeference=georeference;
        this.sendtime=sendtime;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getGeoreference() {
        return georeference;
    }

    public void setGeoreference(String georeference) {
        this.georeference = georeference;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }



}
