package com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models;

public class tripsbydayRequest {
    private int cve_vehicle;
    private String day;
    private String token;

    /**
     *
     * @param cve_vehicle
     * @param day
     * @param token
     */
    public tripsbydayRequest(int cve_vehicle,String day,String token)
    {
        this.cve_vehicle=cve_vehicle;
        this.day=day;
        this.token=token;
    }

    public int getCve_vehicle() {
        return cve_vehicle;
    }

    public void setCve_vehicle(int cve_vehicle) {
        this.cve_vehicle = cve_vehicle;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



}
