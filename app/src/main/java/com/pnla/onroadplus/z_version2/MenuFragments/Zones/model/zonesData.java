package com.pnla.onroadplus.z_version2.MenuFragments.Zones.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class zonesData {
    @SerializedName("cve_layer")
    @Expose
    private int cveLayer;
    @SerializedName("desc_layer")
    @Expose
    private String descLayer;
    @SerializedName("tab_layer_color")
    @Expose
    private String tabLayerColor;
    @SerializedName("vehicles")
    @Expose
    private List<zonesVehicles> vehicles = null;
    public  zonesData(int cveLayer,String descLayer,String tabLayerColor,List<zonesVehicles> vehicles)
    {
        //super();
        this.cveLayer=cveLayer;
        this.descLayer=descLayer;
        this.tabLayerColor=tabLayerColor;
        this.vehicles=vehicles;
    }

    public int getCveLayer() {
        return cveLayer;
    }

    public void setCveLayer(int cveLayer) {
        this.cveLayer = cveLayer;
    }

    public String getDescLayer() {
        return descLayer;
    }

    public void setDescLayer(String descLayer) {
        this.descLayer = descLayer;
    }

    public String getTabLayerColor() {
        return tabLayerColor;
    }

    public void setTabLayerColor(String tabLayerColor) {
        this.tabLayerColor = tabLayerColor;
    }

    public List<zonesVehicles> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<zonesVehicles> vehicles) {
        this.vehicles = vehicles;
    }

}
