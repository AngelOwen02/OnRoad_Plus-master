package com.pnla.onroadplus.z_version2.MenuFragments.Zones.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class pointsInterest {
    @SerializedName("cve_interest_points")
    @Expose
    private String cveInterestPoints;
    @SerializedName("interest_lat")
    @Expose
    private double interestLat;
    @SerializedName("interest_lon")
    @Expose
    private double interestLon;

    public pointsInterest(String cveInterestPoints,double interestLat,double interestLon)
    {

        this.cveInterestPoints=cveInterestPoints;
        this.interestLat=interestLat;
        this.interestLon=interestLon;
    }


    public String getCveInterestPoints() {
        return cveInterestPoints;
    }

    public void setCveInterestPoints(String cveInterestPoints) {
        this.cveInterestPoints = cveInterestPoints;
    }

    public double getInterestLat() {
        return interestLat;
    }

    public void setInterestLat(double interestLat) {
        this.interestLat = interestLat;
    }

    public double getInterestLon() {
        return interestLon;
    }

    public void setInterestLon(double interestLon) {
        this.interestLon = interestLon;
    }

}