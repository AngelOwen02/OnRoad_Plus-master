package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.data;

import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleUnitSupportData {

    @SerializedName("cve_layer")
    @Expose
    private int cveLayer;
    @SerializedName("cve_vehicle")
    @Expose
    private String cveVehicle;
    @SerializedName("vehicle_name")
    @Expose
    private String VehicleName;
    @SerializedName("desc_layer")
    @Expose
    private String DescLayer;
    @SerializedName("percent_complete")
    @Expose
    private int PercentComplete;
    @SerializedName("control_point")
    @Expose
    private int ControlPoint;
    @SerializedName("percent_to_help")
    @Expose
    private int PercentToHelp;
    @SerializedName("status")
    @Expose
    private int Status;
    @SerializedName("help_state")
    @Expose
    private int HelpState;
    @SerializedName("georeference")
    @Expose
    private String GeoReference;

    public SingleUnitSupportData(){

    }

    public SingleUnitSupportData(int cveLayer, String cveVehicle, String vehicleName, String descLayer, int percentComplete, int controlPoint, int percentToHelp, int status, int helpState, String geoReference) {
        this.cveLayer = cveLayer;
        this.cveVehicle = cveVehicle;
        VehicleName = vehicleName;
        DescLayer = descLayer;
        PercentComplete = percentComplete;
        ControlPoint = controlPoint;
        PercentToHelp = percentToHelp;
        Status = status;
        HelpState = helpState;
        GeoReference = geoReference;
    }

    public int getCveLayer() {
        return cveLayer;
    }

    public void setCveLayer(int cveLayer) {
        this.cveLayer = cveLayer;
    }

    public String getCveVehicle() {
        return cveVehicle;
    }

    public void setCveVehicle(String cveVehicle) {
        this.cveVehicle = cveVehicle;
    }

    public String getVehicleName() {
        return VehicleName;
    }

    public void setVehicleName(String vehicleName) {
        VehicleName = vehicleName;
    }

    public String getDescLayer() {
        return DescLayer;
    }

    public void setDescLayer(String descLayer) {
        DescLayer = descLayer;
    }

    public int getPercentComplete() {
        return PercentComplete;
    }

    public void setPercentComplete(int percentComplete) {
        PercentComplete = percentComplete;
    }

    public int getControlPoint() {
        return ControlPoint;
    }

    public void setControlPoint(int controlPoint) {
        ControlPoint = controlPoint;
    }

    public int getPercentToHelp() {
        return PercentToHelp;
    }

    public void setPercentToHelp(int percentToHelp) {
        PercentToHelp = percentToHelp;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getHelpState() {
        return HelpState;
    }

    public void setHelpState(int helpState) {
        HelpState = helpState;
    }

    public String getGeoReference() {
        return GeoReference;
    }

    public void setGeoReference(String geoReference) {
        GeoReference = geoReference;
    }
}
