package com.pnla.onroadplus.z_version2.MenuFragments.Zones.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class visitedData {


    @SerializedName("cve_layer")
    @Expose
    private String cveLayer;
    @SerializedName("desc_layer")
    @Expose
    private String descLayer;
    @SerializedName("points_visited")
    @Expose
    private String pointsVisited;
    @SerializedName("points_not_visited")
    @Expose
    private String pointsNotVisited;
    @SerializedName("percent_complete")
    @Expose
    private String percentComplete;
    @SerializedName("general_percent")
    @Expose
    private String generalPercent;
    @SerializedName("visitedpoints")
    @Expose
    private List<String> visitedpoints ;

    public visitedData(String cveLayer,String descLayer,String pointsVisited,String pointsNotVisited, String percentComplete,String generalPercent,List<String> visitedpoints)
    {
        super();
        this.cveLayer=cveLayer;
        this.descLayer=descLayer;
        this.pointsVisited=pointsVisited;
        this.pointsNotVisited=pointsNotVisited;
        this.percentComplete=percentComplete;
        this.generalPercent=generalPercent;
        this.visitedpoints=visitedpoints;
    }

    public String getCveLayer() {
        return cveLayer;
    }

    public void setCveLayer(String cveLayer) {
        this.cveLayer = cveLayer;
    }

    public String getDescLayer() {
        return descLayer;
    }

    public void setDescLayer(String descLayer) {
        this.descLayer = descLayer;
    }

    public String getPointsVisited() {
        return pointsVisited;
    }

    public void setPointsVisited(String pointsVisited) {
        this.pointsVisited = pointsVisited;
    }

    public String getPointsNotVisited() {
        return pointsNotVisited;
    }

    public void setPointsNotVisited(String pointsNotVisited) {
        this.pointsNotVisited = pointsNotVisited;
    }

    public String getPercentComplete() {
        return percentComplete;
    }

    public void setPercentComplete(String percentComplete) {
        this.percentComplete = percentComplete;
    }

    public String getGeneralPercent() {
        return generalPercent;
    }

    public void setGeneralPercent(String generalPercent) {
        this.generalPercent = generalPercent;
    }

    public List<String> getVisitedpoints() {
        return visitedpoints;
    }

    public void setVisitedpoints(List<String> visitedpoints) {
        this.visitedpoints = visitedpoints;
    }

}
