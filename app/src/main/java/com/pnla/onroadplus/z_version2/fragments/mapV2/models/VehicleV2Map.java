package com.pnla.onroadplus.z_version2.fragments.mapV2.models;

public class VehicleV2Map {

    private int cve_vehicle;
    private int vehicle_switch;
    private String vehicle_name;
    private String vehicle_image;
    private String send_time;
    private String desc_brand;
    private String desc_model;
    private String vehicle_year;
    private String vehicle_vin;
    private String vehicle_plate;
    private String georeference;
    private String time_travel;
    private String time_elapsed;
    private double latitude;
    private double longitude;
    private double mileage;
    private double km_travel;
    private double current_speed;
    private double max_speed;

    public VehicleV2Map(int cve_vehicle, int vehicle_switch, String vehicle_name, String vehicle_image, String send_time, String desc_brand, String desc_model, String vehicle_year, String vehicle_vin, String vehicle_plate, String georeference, String time_travel, String time_elapsed, double latitude, double longitude, double mileage, double km_travel, double current_speed, double max_speed) {
        this.cve_vehicle = cve_vehicle;
        this.vehicle_switch = vehicle_switch;
        this.vehicle_name = vehicle_name;
        this.vehicle_image = vehicle_image;
        this.send_time = send_time;
        this.desc_brand = desc_brand;
        this.desc_model = desc_model;
        this.vehicle_year = vehicle_year;
        this.vehicle_vin = vehicle_vin;
        this.vehicle_plate = vehicle_plate;
        this.georeference = georeference;
        this.time_travel = time_travel;
        this.time_elapsed = time_elapsed;
        this.latitude = latitude;
        this.longitude = longitude;
        this.mileage = mileage;
        this.km_travel = km_travel;
        this.current_speed = current_speed;
        this.max_speed = max_speed;
    }

    public int getCveVehicle() {
        return cve_vehicle;
    }

    public void setCveVehicle(int cve_vehicle) {
        this.cve_vehicle = cve_vehicle;
    }

    public int getVehicleSwitch() {
        return vehicle_switch;
    }

    public void setVehicleSwitch(int vehicle_switch) {
        this.vehicle_switch = vehicle_switch;
    }

    public String getVehicleName() {
        return vehicle_name;
    }

    public void setVehicleName(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public String getVehicleImage() {
        return vehicle_image;
    }

    public void setVehicleImage(String vehicle_image) {
        this.vehicle_image = vehicle_image;
    }

    public String getSendTime() {
        return send_time;
    }

    public void setSendTime(String send_time) {
        this.send_time = send_time;
    }

    public String getDescBrand() {
        return desc_brand;
    }

    public void setDescBrand(String desc_brand) {
        this.desc_brand = desc_brand;
    }

    public String getDescModel() {
        return desc_model;
    }

    public void setDescModel(String desc_model) {
        this.desc_model = desc_model;
    }

    public String getVehicleYear() {
        return vehicle_year;
    }

    public void setVehicleYear(String vehicle_year) {
        this.vehicle_year = vehicle_year;
    }

    public String getVehicleVin() {
        return vehicle_vin;
    }

    public void setVehicleVin(String vehicle_vin) {
        this.vehicle_vin = vehicle_vin;
    }

    public String getVehiclePlate() {
        return vehicle_plate;
    }

    public void setVehiclePlate(String vehicle_plate) {
        this.vehicle_plate = vehicle_plate;
    }

    public String getGeoreference() {
        return georeference;
    }

    public void setGeoreference(String georeference) {
        this.georeference = georeference;
    }

    public String getTimeTravel() {
        return time_travel;
    }

    public void setTimeTravel(String time_travel) {
        this.time_travel = time_travel;
    }

    public String getTimeElapsed() {
        return time_elapsed;
    }

    public void setTimeElapsed(String time_elapsed) {
        this.time_elapsed = time_elapsed;
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

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public double getKmTravel() {
        return km_travel;
    }

    public void setKmTravel(double km_travel) {
        this.km_travel = km_travel;
    }

    public double getCurrentSpeed() {
        return current_speed;
    }

    public void setCurrentSpeed(double current_speed) {
        this.current_speed = current_speed;
    }

    public double getMaxSpeed() {
        return max_speed;
    }

    public void setMaxSpeed(double max_speed) {
        this.max_speed = max_speed;
    }
}
