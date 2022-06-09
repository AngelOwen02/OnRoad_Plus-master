package com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Path {

    @SerializedName("distance")
    @Expose
    private Float distance;
    @SerializedName("weight")
    @Expose
    private Float weight;
    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("transfers")
    @Expose
    private Integer transfers;
    @SerializedName("points_encoded")
    @Expose
    private Boolean pointsEncoded;
    @SerializedName("points")
    @Expose
    private Points points;
    @SerializedName("legs")
    @Expose
    private List<Object> legs = null;
    @SerializedName("details")
    @Expose
    private Details details;
    @SerializedName("ascend")
    @Expose
    private Float ascend;
    @SerializedName("descend")
    @Expose
    private Float descend;
    @SerializedName("snapped_waypoints")
    @Expose
    private SnappedWaypoints snappedWaypoints;

    public Path(Float distance,Float weight,Integer time,Integer transfers,Boolean pointsEncoded,Points points,List<Object> legs,Details details,Float ascend,
                Float descend,SnappedWaypoints snappedWaypoints)
    {
        this.distance=distance;
        this.weight=weight;
        this.time=time;
        this.transfers=transfers;
        this.pointsEncoded=pointsEncoded;
        this.points=points;
        this.legs=legs;
        this.details=details;
        this.ascend=ascend;
        this.descend=descend;
        this.snappedWaypoints=snappedWaypoints;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getTransfers() {
        return transfers;
    }

    public void setTransfers(Integer transfers) {
        this.transfers = transfers;
    }

    public Boolean getPointsEncoded() {
        return pointsEncoded;
    }

    public void setPointsEncoded(Boolean pointsEncoded) {
        this.pointsEncoded = pointsEncoded;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }

    public List<Object> getLegs() {
        return legs;
    }

    public void setLegs(List<Object> legs) {
        this.legs = legs;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public Float getAscend() {
        return ascend;
    }

    public void setAscend(Float ascend) {
        this.ascend = ascend;
    }

    public Float getDescend() {
        return descend;
    }

    public void setDescend(Float descend) {
        this.descend = descend;
    }

    public SnappedWaypoints getSnappedWaypoints() {
        return snappedWaypoints;
    }

    public void setSnappedWaypoints(SnappedWaypoints snappedWaypoints) {
        this.snappedWaypoints = snappedWaypoints;
    }
}
