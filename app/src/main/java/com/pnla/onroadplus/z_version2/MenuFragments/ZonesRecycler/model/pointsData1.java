package com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class pointsData1 {

    @SerializedName("cve_layer")
    @Expose
    private int cveLayer;
    @SerializedName("desc_layer")
    @Expose
    private String descLayer;
    @SerializedName("lats")
    @Expose
    private String lats;
    @SerializedName("lngs")
    @Expose
    private String lngs;
    @SerializedName("interests")
    @Expose
    private List<pointsInterest1> interests = null;

    public pointsData1(int cveLayer,String descLayer,String lats,String lngs,List<pointsInterest1> interests)
    {
        //super();
        this.cveLayer=cveLayer;
        this.descLayer=descLayer;
        this.lats=lats;
        this.lngs=lngs;
        this.interests=interests;
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

    public List<pointsInterest1> getInterests() {
        return interests;
    }

    public void setInterests(List<pointsInterest1> interests) {
        this.interests = interests;
    }

}