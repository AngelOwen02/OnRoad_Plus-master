package com.pnla.onroadplus.z_version2.MenuFragments.geocercas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("cve_geofence")
    @Expose
    private String cveGeofence;
    @SerializedName("geofence_name")
    @Expose
    private String geofenceName;
    @SerializedName("geofence_type")
    @Expose
    private String geofenceType;
    @SerializedName("geofence_color")
    @Expose
    private String geofenceColor;
    @SerializedName("geofence_radius")
    @Expose
    private String geofenceRadius;
    @SerializedName("lats")
    @Expose
    private String lats;
    @SerializedName("lngs")
    @Expose
    private String lngs;


    public Data(String cveGeofence,String geofenceName,String geofenceType,String geofenceColor, String geofenceRadius,String lats,String lngs)
    {
        this.cveGeofence=cveGeofence;
        this.geofenceName=geofenceName;
        this.geofenceType=geofenceType;
        this.geofenceColor=geofenceColor;
        this.geofenceRadius=geofenceRadius;
        this.lats=lats;
        this.lngs=lngs;
    }

    public String getCveGeofence() {
        return cveGeofence;
    }

    public void setCveGeofence(String cveGeofence) {
        this.cveGeofence = cveGeofence;
    }

    public String getGeofenceName() {
        return geofenceName;
    }

    public void setGeofenceName(String geofenceName) {
        this.geofenceName = geofenceName;
    }

    public String getGeofenceType() {
        return geofenceType;
    }

    public void setGeofenceType(String geofenceType) {
        this.geofenceType = geofenceType;
    }

    public String getGeofenceColor() {
        return geofenceColor;
    }

    public void setGeofenceColor(String geofenceColor) {
        this.geofenceColor = geofenceColor;
    }

    public String getGeofenceRadius() {
        return geofenceRadius;
    }

    public void setGeofenceRadius(String geofenceRadius) {
        this.geofenceRadius = geofenceRadius;
    }

    public String getLats() {
        return lats;
    }

    public void setLats(String lats) {
        this.lats = lats;
    }

    public String getLngs() {
        return lngs;
    }

    public void setLngs(String lngs) {
        this.lngs = lngs;
    }

}