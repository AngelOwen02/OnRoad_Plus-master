package com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.mapTracking.models;

public class lastlatlong {


    private String cve_vehicle;
    private String vehicle_name;
    private String last_latitude;
    private String last_longitude;
    private String vehicle_switch;
    private String vehicle_image;


    public lastlatlong(String cve_vehicle, String vehicle_name, String last_latitude, String last_longitude, String vehicle_switch, String vehicle_image)
    {
        this.cve_vehicle = cve_vehicle;
        this.vehicle_name = vehicle_name;

        this.last_latitude = last_latitude;
        this.last_longitude = last_longitude;
        this.vehicle_switch = vehicle_switch;
        this.vehicle_image = vehicle_image;
    }

    public String getCve_vehicle() {
        return cve_vehicle;
    }

    public void setCve_vehicle(String cve_vehicle) {
        this.cve_vehicle = cve_vehicle;
    }

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public String getLast_latitude() {
        return last_latitude;
    }

    public void setLast_latitude(String last_latitude) {
        this.last_latitude = last_latitude;
    }

    public String getLast_longitude() {
        return last_longitude;
    }

    public void setLast_longitude(String last_longitude) {
        this.last_longitude = last_longitude;
    }

    public String getVehicle_switch() {
        return vehicle_switch;
    }

    public void setVehicle_switch(String vehicle_switch) {
        this.vehicle_switch = vehicle_switch;
    }

    public String getVehicle_image() {
        return vehicle_image;
    }

    public void setVehicle_image(String vehicle_image) {
        this.vehicle_image = vehicle_image;
    }

}
