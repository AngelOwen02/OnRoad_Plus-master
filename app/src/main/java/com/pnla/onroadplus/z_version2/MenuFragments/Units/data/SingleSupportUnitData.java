package com.pnla.onroadplus.z_version2.MenuFragments.Units.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleSupportUnitData {

    @SerializedName("url_image")
    @Expose
    private String url_Image;
    @SerializedName("cve_vehicle")
    @Expose
    private String cve_Vehicle;
    @SerializedName("cve_layer")
    @Expose
    private int cveLayer;
    @SerializedName("percent_to_help")
    @Expose
    private int percentToHelp;
    @SerializedName("distance")
    @Expose
    private double distance;
    @SerializedName("georeference")
    @Expose
    private String geoReference;
    @SerializedName("orden")
    @Expose
    private int orden;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("help_state")
    @Expose
    private int help_State;
    @SerializedName("cve_layer_support")
    @Expose
    private int cve_layer_Support;
    @SerializedName("vehicle_name")
    @Expose
    private String vehicle_Name;
    @SerializedName("desc_layer")
    @Expose
    private String desc_Layer;

    public SingleSupportUnitData(){
    }

    public SingleSupportUnitData(String url_Image, String cve_Vehicle, int cveLayer, int percentToHelp, double distance, String geoReference, int orden, int status, int help_State, int cve_layer_Support,
                                 String vehicle_Name, String desc_Layer) {
        super();
        this.url_Image = url_Image;
        this.cve_Vehicle = cve_Vehicle;
        this.cveLayer = cveLayer;
        this.percentToHelp = percentToHelp;
        this.distance = distance;
        this.geoReference = geoReference;
        this.orden = orden;
        this.status = status;
        this.help_State = help_State;
        this.cve_layer_Support = cve_layer_Support;
        this.vehicle_Name = vehicle_Name;
        this.desc_Layer = desc_Layer;
    }

    public String getUrl_Image() {
        return url_Image;
    }

    public void setUrlImage(String url_Image) {
        this.url_Image = url_Image;
    }

    public String getCve_Vehicle() {
        return cve_Vehicle;
    }

    public void setCve_Vehicle(String cve_Vehicle) {
        this.cve_Vehicle = cve_Vehicle;
    }

    public int getCveLayer() {
        return cveLayer;
    }

    public void setCveLayer(int cveLayer) {
        this.cveLayer = cveLayer;
    }

    public int getPercentToHelp() {
        return percentToHelp;
    }

    public void setPercentToHelp(int percentToHelp) {
        this.percentToHelp = percentToHelp;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getGeoReference() {
        return geoReference;
    }

    public void setGeoReference(String geoReference) {
        this.geoReference = geoReference;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getHelp_State() {
        return help_State;
    }

    public void setHelp_State(int help_State) {
        this.help_State = help_State;
    }

    public int getCve_layer_Support() {
        return cve_layer_Support;
    }

    public void setCve_layer_Support(int cve_layer_Support) {
        this.cve_layer_Support = cve_layer_Support;
    }

    public String getVehicle_Name() {
        return vehicle_Name;
    }

    public void setVehicle_Name(String vehicle_Name) {
        this.vehicle_Name = vehicle_Name;
    }

    public String getDesc_Layer() {
        return desc_Layer;
    }

    public void setDesc_Layer(String desc_Layer) {
        this.desc_Layer = desc_Layer;
    }
}
