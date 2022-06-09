package com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models;

public class TripsbyDayData {


   private String latitude;
   private String longitude;

    public  TripsbyDayData(String latitude,String longitude){
        super();
        this.latitude=latitude;
        this.longitude=longitude;
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
}
