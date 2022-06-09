package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.asignment.vehicleZones;

import java.util.List;

public class updateAsignRequest {



    @SerializedName("cve_layer")
    @Expose
    private int cveLayer;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("vehiclesZones")
    @Expose
    private List<vehicleZones> vehiclesZones = null;///    List<VehiclesZone> vehiclesZones = null;
    public  updateAsignRequest(int cveLayer,String token, List<vehicleZones> vehiclesZones)
    {
        this.cveLayer=cveLayer;
        this.token=token;
        this.vehiclesZones=vehiclesZones;

    }

    public int getCveLayer() {
        return cveLayer;
    }

    public void setCveLayer(int cveLayer) {
        this.cveLayer = cveLayer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<vehicleZones> getVehiclesZones() {
        return vehiclesZones;
    }

    public void setVehiclesZones(List<vehicleZones> vehiclesZones) {
        this.vehiclesZones = vehiclesZones;
    }
}
