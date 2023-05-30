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

    public dataVehicleDescV3(Integer cveVehicle, String vehicleName, String vehicleImage,
                             String descBrand, String descModel, String vehicleYear, String vehicleVin,
                             String vehiclePlate, String policyNumber, String insuranceName,
                             String telephone, String lastMessage, String address, Integer satelites,
                             Double altitude, Double latitude, Double longitude, Double currentSpeed,
                             Integer vehicleSwitch, Double kmTravel) {
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
        return cveVehicle != null ? cveVehicle : 0; // Return 0 if cveVehicle is null
    }

    public void setCveVehicle(Integer cveVehicle) {
        this.cveVehicle = cveVehicle;
    }

    public String getVehicleName() {
        return vehicleName != null ? vehicleName : ""; // Return an empty string if vehicleName is null
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleImage() {
        return vehicleImage != null ? vehicleImage : ""; // Return an empty string if vehicleImage is null
    }

    public void setVehicleImage(String vehicleImage) {
        this.vehicleImage = vehicleImage;
    }

    public String getDescBrand() {
        return descBrand != null ? descBrand : ""; // Return an empty string if descBrand is null
    }

    public void setDescBrand(String descBrand) {
        this.descBrand = descBrand;
    }

    public String getDescModel() {
        return descModel != null ? descModel : ""; // Return an empty string if descModel is null
    }

    public void setDescModel(String descModel) {
        this.descModel = descModel;
    }

    public String getVehicleYear() {
        return vehicleYear != null ? vehicleYear : ""; // Return an empty string if vehicleYear is null
    }

    public void setVehicleYear(String vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public String getVehicleVin() {
        return vehicleVin != null ? vehicleVin : ""; // Return an empty string if vehicleVin is null
    }

    public void setVehicleVin(String vehicleVin) {
        this.vehicleVin = vehicleVin;
    }

    public String getVehiclePlate() {
        return vehiclePlate != null ? vehiclePlate : ""; // Return an empty string if vehiclePlate is null
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public String getPolicyNumber() {
        return policyNumber != null ? policyNumber : "-- --"; // Return an empty string if policyNumber is null
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getInsuranceName() {
        return insuranceName != null ? insuranceName : "-- --"; // Return an empty string if insuranceName is null
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getTelephone() {
        return telephone != null ? telephone : ""; // Return an empty string if telephone is null
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLastMessage() {
        return lastMessage != null ? lastMessage : ""; // Return an empty string if lastMessage is null
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getAddress() {
        return address != null ? address : ""; // Return an empty string if address is null
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSatelites() {
        return satelites != null ? satelites : 0; // Return 0 if satelites is null
    }

    public void setSatelites(Integer satelites) {
        this.satelites = satelites;
    }

    public Double getAltitude() {
        return altitude != null ? altitude : 0.0; // Return 0.0 if altitude is null
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Double getLatitude() {
        return latitude; // Return 0.0 if latitude is null
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude ; // Return 0.0 if longitude is null
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getCurrentSpeed() {
        return currentSpeed != null ? currentSpeed : 0.0; // Return 0.0 if currentSpeed is null
    }

    public void setCurrentSpeed(Double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public Integer getVehicleSwitch() {
        return vehicleSwitch != null ? vehicleSwitch : 0; // Return 0 if vehicleSwitch is null
    }

    public void setVehicleSwitch(Integer vehicleSwitch) {
        this.vehicleSwitch = vehicleSwitch;
    }

    public Double getKmTravel() {
        return kmTravel != null ? kmTravel : 0.0; // Return 0.0 if kmTravel is null
    }

    public void setKmTravel(Double kmTravel) {
        this.kmTravel = kmTravel;
    }
}
