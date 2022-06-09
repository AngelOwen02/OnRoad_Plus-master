package com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestAPIMAP {

    @SerializedName("points")
    @Expose
    private List<List<Double>> points = null;
    @SerializedName("instructions")
    @Expose
    private Boolean instructions;
    @SerializedName("vehicle")
    @Expose
    private String vehicle;
    @SerializedName("points_encoded")
    @Expose
    private Boolean pointsEncoded;
    @SerializedName("optimize")
    @Expose
    private Boolean optimize;

    public RequestAPIMAP(List<List<Double>> points,Boolean instructions,String vehicle,Boolean pointsEncoded,Boolean optimize)
    {
        this.points=points;
        this.instructions=instructions;
        this.vehicle=vehicle;
        this.pointsEncoded=pointsEncoded;
        this.optimize=optimize;

    }

    public List<List<Double>> getPoints() {
        return points;
    }

    public void setPoints(List<List<Double>> points) {
        this.points = points;
    }

    public Boolean getInstructions() {
        return instructions;
    }

    public void setInstructions(Boolean instructions) {
        this.instructions = instructions;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public Boolean getPointsEncoded() {
        return pointsEncoded;
    }

    public void setPointsEncoded(Boolean pointsEncoded) {
        this.pointsEncoded = pointsEncoded;
    }

    public Boolean getOptimize() {
        return optimize;
    }

    public void setOptimize(Boolean optimize) {
        this.optimize = optimize;
    }


}
