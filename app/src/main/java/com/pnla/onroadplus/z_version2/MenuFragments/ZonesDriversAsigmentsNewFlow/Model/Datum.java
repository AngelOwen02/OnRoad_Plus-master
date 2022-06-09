package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Datum {

    @SerializedName("cve_layer")
    @Expose
    private Integer cveLayer;
    @SerializedName("vehicleDrivers")
    @Expose
    private List<VehicleDriver> vehicleDrivers = null;

    public Datum (Integer cveLayer,List<VehicleDriver> vehicleDrivers)
    {
        this.cveLayer=cveLayer;
        this.vehicleDrivers=vehicleDrivers;
    }

    public Integer getCveLayer() {
        return cveLayer;
    }

    public void setCveLayer(Integer cveLayer) {
        this.cveLayer = cveLayer;
    }

    public List<VehicleDriver> getVehicleDrivers() {
        return vehicleDrivers;
    }

    public void setVehicleDrivers(List<VehicleDriver> vehicleDrivers) {
        this.vehicleDrivers = vehicleDrivers;
    }
}
