package com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class visitedData1 {


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
    @SerializedName("cve_supervisor")
    @Expose
    private Integer cve_supervisor;
    @SerializedName("supervisor_name")
    @Expose
    private String supervisor_name;

    @SerializedName("visitedpoints")
    @Expose
    private List<String> visitedpoints ;


    private List<String> colorVolante;

    public visitedData1(String cveLayer,String descLayer,String pointsVisited,String pointsNotVisited, String percentComplete,String generalPercent
            ,List<String> visitedpoints,List<String> colorVolante,Integer cve_supervisor, String supervisor_name )
    {
        super();
        this.cveLayer=cveLayer;
        this.descLayer=descLayer;
        this.pointsVisited=pointsVisited;
        this.pointsNotVisited=pointsNotVisited;
        this.percentComplete=percentComplete;
        this.generalPercent=generalPercent;
        this.visitedpoints=visitedpoints;
        this.colorVolante=colorVolante;
        this.cve_supervisor=cve_supervisor;
        this.supervisor_name=supervisor_name;
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

    public List<String> getColorVolante() {
        return colorVolante;
    }

    public void setColorVolante(List<String> colorVolante) {
        this.colorVolante = colorVolante;
    }


    public int getCve_employee() {
        return cve_supervisor;
    }

    public void setCve_employee(int cve_supervisor) {
        this.cve_supervisor = cve_supervisor;
    }

    public String getSupervisor_name() {
        return supervisor_name;
    }

    public void setSupervisor_name(String supervisor_name) {
        this.supervisor_name = supervisor_name;
    }

}
