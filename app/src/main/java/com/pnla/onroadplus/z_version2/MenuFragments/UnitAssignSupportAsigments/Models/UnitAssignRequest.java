package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnitAssignRequest {

    @SerializedName("cve_layer")
    private Integer cveLayer;
    @SerializedName("cve_layer_support")
    private Integer cveLayerSupport;
    @SerializedName("cve_vehicle")
    private Integer cveVehicle;
    @SerializedName("token")
    private String token;

    public UnitAssignRequest(Integer cveLayer, Integer cveLayerSupport, Integer cveVehicle, String token) {
        super();
        this.cveLayer = cveLayer;
        this.cveLayerSupport = cveLayerSupport;
        this.cveVehicle = cveVehicle;
        this.token = token;
    }

    public Integer getCveLayer() {
        return cveLayer;
    }

    public void setCveLayer(Integer cveLayer) {
        this.cveLayer = cveLayer;
    }

    public Integer getCveLayerSupport() {
        return cveLayerSupport;
    }

    public void setCveLayerSupport(Integer cveLayerSupport) {
        this.cveLayerSupport = cveLayerSupport;
    }

    public Integer getCveVehicle() {
        return cveVehicle;
    }

    public void setCveVehicle(Integer cveVehicle) {
        this.cveVehicle = cveVehicle;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
