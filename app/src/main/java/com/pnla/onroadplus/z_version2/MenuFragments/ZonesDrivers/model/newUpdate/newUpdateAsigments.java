package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.newUpdate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class newUpdateAsigments {


    @SerializedName("cve_layer")
    @Expose
    private Integer cveLayer;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("vehicleDrivers")
    @Expose
    private List<VehicleDriver> vehicleDrivers = null;

    public newUpdateAsigments(Integer cveLayer,String token,List<VehicleDriver> vehicleDrivers)
    {
        this.cveLayer=cveLayer;
        this.token=token;
        this.vehicleDrivers=vehicleDrivers;
    }

    public Integer getCveLayer() {
        return cveLayer;
    }

    public void setCveLayer(Integer cveLayer) {
        this.cveLayer = cveLayer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<VehicleDriver> getVehicleDrivers() {
        return vehicleDrivers;
    }

    public void setVehicleDrivers(List<VehicleDriver> vehicleDrivers) {
        this.vehicleDrivers = vehicleDrivers;
    }

}

