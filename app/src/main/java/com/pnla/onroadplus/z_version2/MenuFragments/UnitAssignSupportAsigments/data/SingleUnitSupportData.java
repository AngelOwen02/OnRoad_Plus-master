package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.data;

public class SingleUnitSupportData {

    private int cveLayer;
    private String cveVehicle;
    private String VehicleName;
    private String DescLayer;
    private int PercentComplete;
    private int ControlPoint;
    private int PercentToHelp;
    private int Status;
    private int HelpState;
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
