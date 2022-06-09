package com.pnla.onroadplus.z_version2.MenuFragments.Units.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UnitFinal extends RealmObject {

    @PrimaryKey
    private int id;
    private boolean vehicleStatus;
    @SerializedName("cve_vehicle")
    private int cveVehicle;
    @SerializedName("vehicle_switch")
    private int vehicleSwitch;
    @SerializedName("vehicle_name")
    private String vehicleName;
    @SerializedName("vehicle_image")
    private String vehicleImage;
    @SerializedName("send_time")
    private String sendTime;
    @SerializedName("desc_brand")
    private String descBrand;
    @SerializedName("desc_model")
    private String descModel;
    @SerializedName("vehicle_year")
    private String vehicleYear;
    @SerializedName("vehicle_vin")
    private String vehicleVin;
    @SerializedName("vehicle_plate")
    private String vehiclePlate;
    private String georeference;
    @SerializedName("time_travel")
    private String timeTravel;
    @SerializedName("time_elapsed")
    private String timeElapsed;
    private double latitude;
    private double longitude;
    private double mileage;
    @SerializedName("km_travel")
    private double kmTravel;
    @SerializedName("current_speed")
    private double currentSpeed;
    @SerializedName("max_speed")
    private double maxSpeed;

    public UnitFinal(){}

    public UnitFinal(boolean vehicleStatus, int cveVehicle, int vehicleSwitch, String vehicleName, String vehicleImage, String sendTime, String descBrand, String descModel, String vehicleYear, String vehicleVin, String vehiclePlate, String georeference, String timeTravel, String timeElapsed, double latitude, double longitude, double mileage, double kmTravel, double currentSpeed, double maxSpeed) {
        this.id = id;
        this.vehicleStatus = vehicleStatus;
        this.cveVehicle = cveVehicle;
        this.vehicleSwitch = vehicleSwitch;
        this.vehicleName = vehicleName;
        this.vehicleImage = vehicleImage;
        this.sendTime = sendTime;
        this.descBrand = descBrand;
        this.descModel = descModel;
        this.vehicleYear = vehicleYear;
        this.vehicleVin = vehicleVin;
        this.vehiclePlate = vehiclePlate;
        this.georeference = georeference;
        this.timeTravel = timeTravel;
        this.timeElapsed = timeElapsed;
        this.latitude = latitude;
        this.longitude = longitude;
        this.mileage = mileage;
        this.kmTravel = kmTravel;
        this.currentSpeed = currentSpeed;
        this.maxSpeed = maxSpeed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(boolean vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public int getCveVehicle() {
        return cveVehicle;
    }

    public void setCveVehicle(int cveVehicle) {
        this.cveVehicle = cveVehicle;
    }

    public int getVehicleSwitch() {
        return vehicleSwitch;
    }

    public void setVehicleSwitch(int vehicleSwitch) {
        this.vehicleSwitch = vehicleSwitch;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleImage() {
        return vehicleImage;
    }

    public void setVehicleImage(String vehicleImage) {
        this.vehicleImage = vehicleImage;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getDescBrand() {
        return descBrand;
    }

    public void setDescBrand(String descBrand) {
        this.descBrand = descBrand;
    }

    public String getDescModel() {
        return descModel;
    }

    public void setDescModel(String descModel) {
        this.descModel = descModel;
    }

    public String getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(String vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public String getVehicleVin() {
        return vehicleVin;
    }

    public void setVehicleVin(String vehicleVin) {
        this.vehicleVin = vehicleVin;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public String getGeoreference() {
        return georeference;
    }

    public void setGeoreference(String georeference) {
        this.georeference = georeference;
    }

    public String getTimeTravel() {
        return timeTravel;
    }

    public void setTimeTravel(String timeTravel) {
        this.timeTravel = timeTravel;
    }

    public String getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(String timeElapsed) {
        this.timeElapsed = timeElapsed;
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
        return kmTravel;
    }

    public void setKmTravel(double kmTravel) {
        this.kmTravel = kmTravel;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
