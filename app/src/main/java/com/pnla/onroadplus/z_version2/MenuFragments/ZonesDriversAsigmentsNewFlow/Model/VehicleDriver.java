package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VehicleDriver {

    @SerializedName("cve_vehicle")
    @Expose
    private Integer cveVehicle;
    @SerializedName("vehicle_name")
    @Expose
    private String vehicleName;
    @SerializedName("cve_driver")
    @Expose
    private Integer cveDriver;
    @SerializedName("driver_name")
    @Expose
    private String driverName;
    @SerializedName("tripulantes")
    @Expose
    private List<Tripulante> tripulantes = null;

    public VehicleDriver(Integer cveVehicle,Integer cveDriver,List<Tripulante> tripulantes,String vehicleName,String driverName)
    {
        this.cveVehicle =cveVehicle;
        this.cveDriver=cveDriver;
        this.tripulantes=tripulantes;
        this.vehicleName=vehicleName;
        this.driverName=driverName;
    }

    public Integer getCveVehicle() {
        return cveVehicle;
    }

    public void setCveVehicle(Integer cveVehicle) {
        this.cveVehicle = cveVehicle;
    }

    public Integer getCveDriver() {
        return cveDriver;
    }

    public void setCveDriver(Integer cveDriver) {
        this.cveDriver = cveDriver;
    }

    public List<Tripulante> getTripulantes() {
        return tripulantes;
    }

    public void setTripulantes(List<Tripulante> tripulantes) {
        this.tripulantes = tripulantes;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
}
