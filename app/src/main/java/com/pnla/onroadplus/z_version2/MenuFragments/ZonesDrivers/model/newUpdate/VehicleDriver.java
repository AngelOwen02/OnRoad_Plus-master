package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.newUpdate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VehicleDriver {


    @SerializedName("cve_driver")
    @Expose
    private Integer cveDriver;
    @SerializedName("cve_vehicle")
    @Expose
    private Integer cveVehicle;
    @SerializedName("tripulantes")
    @Expose
    private List<Integer> tripulantes = null;

    public VehicleDriver(Integer cveDriver,Integer cveVehicle,List<Integer> tripulantes)
    {
        this.cveDriver=cveDriver;
        this.cveVehicle=cveVehicle;
        this.tripulantes=tripulantes;
    }


    public Integer getCveDriver() {
        return cveDriver;
    }

    public void setCveDriver(Integer cveDriver) {
        this.cveDriver = cveDriver;
    }

    public Integer getCveVehicle() {
        return cveVehicle;
    }

    public void setCveVehicle(Integer cveVehicle) {
        this.cveVehicle = cveVehicle;
    }

    public List<Integer> getTripulantes() {
        return tripulantes;
    }

    public void setTripulantes(List<Integer> tripulantes) {
        this.tripulantes = tripulantes;
    }
}
