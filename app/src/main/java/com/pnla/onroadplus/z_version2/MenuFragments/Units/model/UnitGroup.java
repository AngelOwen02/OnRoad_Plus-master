package com.pnla.onroadplus.z_version2.MenuFragments.Units.model;

import io.realm.RealmObject;

public class UnitGroup extends RealmObject {

    private int cve_vehicle;
    private double latitude;
    private double longitude;
    private String vehicle_image;
    private String vehicle_name;
    private int vehicle_switch;
    private boolean vehicle_status;

    public UnitGroup(){}

    public UnitGroup(int cve_vehicle, double latitude, double longitude, String vehicle_image, String vehicle_name, int vehicle_switch, boolean vehicle_status) {
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

    public boolean isVehicle_status() {
        return vehicle_status;
    }

    public void setVehicle_status(boolean vehicle_status) {
        this.vehicle_status = vehicle_status;
    }
}
