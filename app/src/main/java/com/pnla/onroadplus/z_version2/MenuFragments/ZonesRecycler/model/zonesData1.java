package com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class zonesData1 {
    @SerializedName("cve_layer")
    @Expose
    private int cveLayer;
    @SerializedName("desc_layer")
    @Expose
    private String descLayer;
    @SerializedName("tab_layer_color")
    @Expose
    private String tabLayerColor;
    @SerializedName("cve_supervisor")
    @Expose
    private String cve_supervisor;
    @SerializedName("supervisor_name")
    @Expose
    private String supervisor_name;
    @SerializedName("vehicles")
    @Expose
    private List<zonesVehicles1> vehicles = null;


    @SerializedName("atention_day")
    @Expose
    private String daysAvailable;


    public  zonesData1(int cveLayer,String descLayer,String tabLayerColor,List<zonesVehicles1> vehicles,String daysAvailable,String cve_employee, String supervisor_name)
    {
        //super();
        this.cveLayer=cveLayer;
        this.descLayer=descLayer;
        this.tabLayerColor=tabLayerColor;
        this.vehicles=vehicles;
        this.daysAvailable=daysAvailable;
        this.cve_supervisor=cve_employee;
        this.supervisor_name=supervisor_name;
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

    public List<zonesVehicles1> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<zonesVehicles1> vehicles) {
        this.vehicles = vehicles;
    }
    public String getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(String daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
    public String getCve_employee() {
        return cve_supervisor;
    }

    public void setCve_employee(String cve_employee) {
        this.cve_supervisor = cve_employee;
    }

    public String getSupervisor_name() {
        return supervisor_name;
    }

    public void setSupervisor_name(String supervisor_name) {
        this.supervisor_name = supervisor_name;
    }
}
