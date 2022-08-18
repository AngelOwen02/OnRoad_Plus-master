package com.pnla.onroadplus.z_version2.MenuFragments.Units.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SupportUnitData {

    @SerializedName("url_image")
    @Expose
    private String url_Image;
    @SerializedName("cve_layer")
    @Expose
    private int cveLayer;
    @SerializedName("cve_vehicle")
    @Expose
    private String cve_Vehicle;
    @SerializedName("vehicle_name")
    @Expose
    private String vehicle_Name;
    @SerializedName("desc_layer")
    @Expose
    private String desc_Layer;
    @SerializedName("percent_complete")
    @Expose
    private int percent_Complete;
    @SerializedName("control_point")
    @Expose
    private int control_Point;
    @SerializedName("percent_to_help")
    @Expose
    private int percentToHelp;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("help_state")
    @Expose
    private int help_State;
    @SerializedName("georeference")
    @Expose
    private String geoReference;

    public SupportUnitData(){
    }

    public SupportUnitData(String url_Image, int cveLayer, String cve_Vehicle, String vehicle_Name, String desc_Layer, int percent_Complete, int control_Point, int percentToHelp, int status, int help_State,
                           String geoReference) {
        super();
        this.url_Image = url_Image;
        this.cveLayer = cveLayer;
        this.cve_Vehicle = cve_Vehicle;
        this.vehicle_Name = vehicle_Name;
        this.desc_Layer = desc_Layer;
        this.percent_Complete = percent_Complete;
        this.control_Point = control_Point;
        this.percentToHelp = percentToHelp;
        this.status = status;
        this.help_State = help_State;
        this.geoReference = geoReference;
    }

    public String getUrl_Image() {
        return url_Image;
    }

    public void setUrl_Image(String url_Image) {
        this.url_Image = url_Image;
    }

    public int getCveLayer() {
        return cveLayer;
    }

    public void setCveLayer(int cveLayer) {
        this.cveLayer = cveLayer;
    }

    public String getCve_Vehicle() {
        return cve_Vehicle;
    }

    public void setCve_Vehicle(String cve_Vehicle) {
        this.cve_Vehicle = cve_Vehicle;
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

    public int getPercent_Complete() {
        return percent_Complete;
    }

    public void setPercent_Complete(int percent_Complete) {
        this.percent_Complete = percent_Complete;
    }

    public int getControl_Point() {
        return control_Point;
    }

    public void setControl_Point(int control_Point) {
        this.control_Point = control_Point;
    }

    public int getPercentToHelp() {
        return percentToHelp;
    }

    public void setPercentToHelp(int percentToHelp) {
        this.percentToHelp = percentToHelp;
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

    public String getGeoReference() {
        return geoReference;
    }

    public void setGeoReference(String geoReference) {
        this.geoReference = geoReference;
    }
}
