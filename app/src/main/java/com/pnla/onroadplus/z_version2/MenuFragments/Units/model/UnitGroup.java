package com.pnla.onroadplus.z_version2.MenuFragments.Units.model;

import io.realm.RealmObject;

public class UnitGroup extends RealmObject {

    private int cve_vehicle;
    private Double latitude = null;
    private Double longitude = null;
    private String vehicle_image;
    private String vehicle_name;
    private int vehicle_switch;
    private Boolean vehicle_status = false;

    public UnitGroup(){}

    public UnitGroup(int cve_vehicle, double latitude, double longitude, String vehicle_image, String vehicle_name, int vehicle_switch, Boolean vehicle_status) {
        this.cve_vehicle = cve_vehicle;
        this.latitude = latitude;
        this.longitude = longitude;
        this.vehicle_image = vehicle_image;
        this.vehicle_name = vehicle_name;
        this.vehicle_switch = vehicle_switch;
        this.vehicle_status = vehicle_status;
    }

    public int getCve_vehicle() {
        return cve_vehicle;
    }

    public void setCve_vehicle(int cve_vehicle) {
        this.cve_vehicle = cve_vehicle;
    }

    public Double getLatitude() {
        return latitude != null ? latitude : 0.0;
        //return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude != null ? latitude : 0.0;
        //this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getVehicle_image() {
        return vehicle_image;
    }

    public void setVehicle_image(String vehicle_image) {
        this.vehicle_image = vehicle_image;
    }

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public int getVehicle_switch() {
        return vehicle_switch;
    }

    public void setVehicle_switch(int vehicle_switch) {
        this.vehicle_switch = vehicle_switch;
    }

    public Boolean isVehicle_status() {
        return vehicle_status;
    }

    public void setVehicle_status(Boolean vehicle_status) {
        this.vehicle_status = vehicle_status;
    }
}
