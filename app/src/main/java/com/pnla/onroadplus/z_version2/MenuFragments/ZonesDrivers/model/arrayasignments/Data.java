package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.arrayasignments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

public class Data {
    @SerializedName("cve_layer")
    @Expose
    private Integer cveLayer;
    @SerializedName("vehicleDrivers")
    @Expose
    private List<VhicleDriver> vehicleDrivers = null;

    public Data (Integer cveLayer,List<VhicleDriver> vehicleDrivers)
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

    public List<VhicleDriver> getVehicleDrivers() {
        return vehicleDrivers;
    }

    public void setVehicleDrivers(List<VhicleDriver> vehicleDrivers) {
        this.vehicleDrivers = vehicleDrivers;
    }
}
