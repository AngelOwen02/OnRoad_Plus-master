package com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips;

public class TripsV2Request {

    private int cve_vehicle;
    private String day_start;
    private String day_end;
    private String token;

    /**
     * @param cve_vehicle
     * @param day_start
     * @param day_end
     * @param token
     */
    public TripsV2Request(int cve_vehicle, String day_start, String day_end, String token) {
        this.cve_vehicle = cve_vehicle;
        this.day_start = day_start;
        this.day_end = day_end;
        this.token = token;
    }

    public int getCveVehicle() {
        return cve_vehicle;
    }

    public void setCveVehicle(int cve_vehicle) {
        this.cve_vehicle = cve_vehicle;
    }

    public String getDayStart() {
        return day_start;
    }

    public void setDayStart(String day_start) {
        this.day_start = day_start;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDayEnd() {
        return day_end;
    }

    public void setDayEnd(String day_end) {
        this.day_end = day_end;
    }

}
