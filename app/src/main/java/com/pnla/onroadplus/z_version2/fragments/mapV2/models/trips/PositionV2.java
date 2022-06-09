package com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips;

public class PositionV2 {
    private int orden;
    private double latitude;
    private double longitude;
    private String send_time;

    /**
     * @param orden
     * @param send_time
     * @param longitude
     * @param latitude
     */
    public PositionV2(int orden, double latitude, double longitude, String send_time) {
        super();
        this.orden = orden;
        this.latitude = latitude;
        this.longitude = longitude;
        this.send_time = send_time;
    }

    public int getOrder() {
        return orden;
    }

    public void setOrder(int orden) {
        this.orden = orden;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }
}
