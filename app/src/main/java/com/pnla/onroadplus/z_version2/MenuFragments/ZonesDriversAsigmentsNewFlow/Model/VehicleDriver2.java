package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VehicleDriver2 {


    @SerializedName("cve_vehicle")
    @Expose
    private Integer cveVehicle;
    @SerializedName("cve_driver")
    @Expose
    private Integer cveDriver;
    @SerializedName("tripulantes")
    @Expose
    private List<Tripulante> tripulantes = null;

    public VehicleDriver2(Integer cveVehicle,Integer cveDriver,List<Tripulante> tripulantes)
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

    public List<Tripulante> getTripulantes() {
        return tripulantes;
    }

    public void setTripulantes(List<Tripulante> tripulantes) {
        this.tripulantes = tripulantes;
    }


}
