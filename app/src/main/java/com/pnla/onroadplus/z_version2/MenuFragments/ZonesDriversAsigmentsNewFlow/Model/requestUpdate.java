package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class requestUpdate {
    @SerializedName("cve_layer")
    @Expose
    private Integer cveLayer;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("vehicleDrivers")
    @Expose
    private List<VehicleDriver2> vehicleDrivers = null;

    public requestUpdate(Integer cveLayer,String token,List<VehicleDriver2> vehicleDrivers)
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

    public List<VehicleDriver2> getVehicleDrivers() {
        return vehicleDrivers;
    }

    public void setVehicleDrivers(List<VehicleDriver2> vehicleDrivers) {
        this.vehicleDrivers = vehicleDrivers;
    }
}
