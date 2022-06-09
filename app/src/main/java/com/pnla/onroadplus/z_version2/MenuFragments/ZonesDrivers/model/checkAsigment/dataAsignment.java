package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.checkAsigment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class dataAsignment {

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
    private List<asignVehicles> vehicles ;
    public  dataAsignment(int cveLayer,String descLayer,String tabLayerColor,List<asignVehicles> vehicles)
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

    public List<asignVehicles> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<asignVehicles> vehicles) {
        this.vehicles = vehicles;
    }
}
