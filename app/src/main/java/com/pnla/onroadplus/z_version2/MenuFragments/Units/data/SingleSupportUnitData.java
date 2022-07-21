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

    public SingleSupportUnitData(){
    }

    public SingleSupportUnitData(String url_Image, String cve_Vehicle, int cveLayer, int percentToHelp, double distance, String geoReference, int orden) {
        super();
        this.url_Image = url_Image;
        this.cve_Vehicle = cve_Vehicle;
        this.cveLayer = cveLayer;
        this.percentToHelp = percentToHelp;
        this.distance = distance;
        this.geoReference = geoReference;
        this.orden = orden;
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
}
