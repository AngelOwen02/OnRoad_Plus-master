package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class setZone {


    @SerializedName("cve_layer")
    @Expose
    private Integer cveLayer;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("zone")
    @Expose
    private Zone zone;

    public setZone(Integer cveLayer,String token,Zone zone)
    {
     this.cveLayer=cveLayer;
     this.token=token;
     this.zone=zone;
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

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
}
