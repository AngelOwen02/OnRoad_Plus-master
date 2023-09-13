package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.model;

import com.google.gson.annotations.SerializedName;

public class Vehiclec2 {

    @SerializedName("cve_vehicle")
    private Integer cveVehicle;
    @SerializedName("vehicle_name")
    private String vehicleName;
    @SerializedName("vehicle_image")
    private String vehicleImage;
    @SerializedName("longitude")
    private Double longitude;
    @SerializedName("latitude")
    private Double latitude;
    private Boolean isChecked=false;

    public Vehiclec2(Integer cveVehicle, String vehicleName, String vehicleImage, Double longitude, Double latitude,Boolean isChecked) {
        super();
        this.cveVehicle = cveVehicle;
        this.vehicleName = vehicleName;
        this.vehicleImage = vehicleImage;
        this.longitude = longitude;
        this.latitude = latitude;
        this.isChecked=isChecked;
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

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Boolean getChecked() {
        if (isChecked == null) {
            isChecked = false;
        }
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        if (checked == null) {
            isChecked = false;
        } else {
            isChecked = checked;
        }
    }
}
