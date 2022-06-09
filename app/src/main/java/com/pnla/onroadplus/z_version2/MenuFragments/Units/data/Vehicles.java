package com.pnla.onroadplus.z_version2.MenuFragments.Units.data;

public class Vehicles {

    private int cve_vehicle;
    private String vehicle_name;
    private String vehicle_image;
    private double longitude;
    private double latitude;

    public Vehicles(int cve_vehicle, String vehicle_name, String vehicle_image, double longitude, double latitude){
        super();
        this.cve_vehicle = cve_vehicle;
        this.vehicle_name = vehicle_name;
        this.vehicle_image = vehicle_image;
        this.longitude = longitude;
        this.latitude = latitude;
        
    }

    public int getCve_vehicle() {
        return cve_vehicle;
    }

    public void setCve_vehicle(int cve_vehicle) {
        this.cve_vehicle = cve_vehicle;
    }

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public String getVehicle_image() {
        return vehicle_image;
    }

    public void setVehicle_image(String vehicle_image) {
        this.vehicle_image = vehicle_image;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }



}
