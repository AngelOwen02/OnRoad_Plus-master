package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.model;

import com.google.gson.annotations.SerializedName;

public class DialogsData {

    @SerializedName("origin_adm")
    private Integer originAdm;
    @SerializedName("cve_vehicle")
    private Integer cveVehicle;
    @SerializedName("vehicle_name")
    private String vehicleName;
    @SerializedName("vehicle_plate")
    private String vehiclePlate;
    @SerializedName("vehicle_vin")
    private String vehicleVin;
    @SerializedName("vehicle_year")
    private String vehicleYear;

    public DialogsData(Integer originAdm, Integer cveVehicle, String vehicleName, String vehiclePlate, String vehicleVin, String vehicleYear) {
        super();
        this.originAdm = originAdm;
        this.cveVehicle = cveVehicle;
        this.vehicleName = vehicleName;
        this.vehiclePlate = vehiclePlate;
        this.vehicleVin = vehicleVin;
        this.vehicleYear = vehicleYear;
    }

    public Integer getOriginAdm() {
        return originAdm;
    }

    public void setOriginAdm(Integer originAdm) {
        this.originAdm = originAdm;
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

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public String getVehicleVin() {
        return vehicleVin;
    }

    public void setVehicleVin(String vehicleVin) {
        this.vehicleVin = vehicleVin;
    }

    public String getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(String vehicleYear) {
        this.vehicleYear = vehicleYear;
    }
}
