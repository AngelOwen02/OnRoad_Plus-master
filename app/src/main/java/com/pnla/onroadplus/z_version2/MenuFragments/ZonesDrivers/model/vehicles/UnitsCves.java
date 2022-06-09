package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.vehicles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnitsCves {

    @SerializedName("origin_adm")
    @Expose
    private String originAdm;
    @SerializedName("cve_vehicle")
    @Expose
    private String cveVehicle;
    @SerializedName("vehicle_name")
    @Expose
    private String vehicleName;
    @SerializedName("vehicle_plate")
    @Expose
    private String vehiclePlate;
    @SerializedName("vehicle_vin")
    @Expose
    private String vehicleVin;
    @SerializedName("vehicle_year")
    @Expose
    private String vehicleYear;

    public UnitsCves(String originAdm,String cveVehicle,String vehicleName,String vehiclePlate,String vehicleVin,String vehicleYear)
    {
        this.originAdm=originAdm;
        this.cveVehicle=cveVehicle;
        this.vehicleName=vehicleName;
        this.vehiclePlate=vehiclePlate;
        this.vehicleVin=vehicleVin;
        this.vehicleYear=vehicleYear;

    }

    public String getOriginAdm() {
        return originAdm;
    }

    public void setOriginAdm(String originAdm) {
        this.originAdm = originAdm;
    }

    public String getCveVehicle() {
        return cveVehicle;
    }

    public void setCveVehicle(String cveVehicle) {
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
