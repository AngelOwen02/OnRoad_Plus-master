package com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vehicle {

    @SerializedName("cve_vehicle")
    @Expose
    private Integer cveVehicle;
    @SerializedName("vehicle_name")
    @Expose
    private String vehicleName;
    @SerializedName("vehicle_image")
    @Expose
    private String vehicleImage;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("latitude")
    @Expose
    private String latitude;

    public Vehicle(Integer cveVehicle,String vehicleName,String vehicleImage,String longitude,String latitude)
    {
        this.cveVehicle=cveVehicle;
        this.vehicleName=vehicleName;
        this.vehicleImage=vehicleImage;
        this.longitude=longitude;
        this.latitude=latitude;
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
