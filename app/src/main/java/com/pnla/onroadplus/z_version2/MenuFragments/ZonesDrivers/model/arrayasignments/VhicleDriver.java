package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.arrayasignments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VhicleDriver {

    @SerializedName("cve_vehicle")
    @Expose
    private Integer cveVehicle;
    @SerializedName("cve_driver")
    @Expose
    private Integer cveDriver;
    @SerializedName("tripulantes")
    @Expose
    private List<Integer> tripulantes = null;

    public VhicleDriver(Integer cveVehicle,Integer cveDriver,List<Integer> tripulantes)
    {
        this.cveVehicle =cveVehicle;
        this.cveDriver=cveDriver;
        this.tripulantes=tripulantes;
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

    public List<Integer> getTripulantes() {
        return tripulantes;
    }

    public void setTripulantes(List<Integer> tripulantes) {
        this.tripulantes = tripulantes;
    }


}
