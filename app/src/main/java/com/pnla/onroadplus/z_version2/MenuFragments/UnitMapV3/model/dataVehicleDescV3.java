package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model;

import com.google.gson.annotations.SerializedName;

public class dataVehicleDescV3 {

    @SerializedName("cve_vehicle")
    private Integer cveVehicle;
    @SerializedName("vehicle_name")
    private String vehicleName;
    @SerializedName("vehicle_image")
    private String vehicleImage;
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
    @SerializedName("policy_number")
    private String policyNumber;
    @SerializedName("insurance_name")
    private String insuranceName;
    @SerializedName("telephone")
    private String telephone;
    @SerializedName("last_message")
    private String lastMessage;
    @SerializedName("address")
    private String address;
    @SerializedName("satelites")
    private Integer satelites;
    @SerializedName("altitude")
    private Double altitude;
    @SerializedName("latitude")
    private Double latitude;
    @SerializedName("longitude")
    private Double longitude;
    @SerializedName("current_speed")
    private Double currentSpeed;
    @SerializedName("vehicle_switch")
    private Integer vehicleSwitch;
    @SerializedName("km_travel")
    private Double kmTravel;

    public dataVehicleDescV3(Integer cveVehicle, String vehicleName, String vehicleImage, String descBrand, String descModel, String vehicleYear, String vehicleVin, String vehiclePlate, String policyNumber, String insuranceName, String telephone, String lastMessage, String address, Integer satelites, Double altitude, Double latitude, Double longitude, Double currentSpeed, Integer vehicleSwitch, Double kmTravel) {
        super();
        this.cveVehicle = cveVehicle;
        this.vehicleName = vehicleName;
        this.vehicleImage = vehicleImage;
        this.descBrand = descBrand;
        this.descModel = descModel;
        this.vehicleYear = vehicleYear;
        this.vehicleVin = vehicleVin;
        this.vehiclePlate = vehiclePlate;
        this.policyNumber = policyNumber;
        this.insuranceName = insuranceName;
        this.telephone = telephone;
        this.lastMessage = lastMessage;
        this.address = address;
        this.satelites = satelites;
        this.altitude = altitude;
        this.latitude = latitude;
        this.longitude = longitude;
        this.currentSpeed = currentSpeed;
        this.vehicleSwitch = vehicleSwitch;
        this.kmTravel = kmTravel;
    }

    public Integer getCveVehicle() {
        return cveVehicle;
    }

    public void setCveVehicle(Integer cveVehicle) {
        this.cveVehicle = cveVehicle;
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

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSatelites() {
        return satelites;
    }

    public void setSatelites(Integer satelites) {
        this.satelites = satelites;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(Double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public Integer getVehicleSwitch() {
        return vehicleSwitch;
    }

    public void setVehicleSwitch(Integer vehicleSwitch) {
        this.vehicleSwitch = vehicleSwitch;
    }

    public Double getKmTravel() {
        return kmTravel;
    }

    public void setKmTravel(Double kmTravel) {
        this.kmTravel = kmTravel;
    }
}
