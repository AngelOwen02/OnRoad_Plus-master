package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Historic {

    @SerializedName("date_insert")
    @Expose
    private String date_Insert;
    @SerializedName("vehicle_name")
    @Expose
    private String vehicleName;
    @SerializedName("score")
    @Expose
    private String score;
    @SerializedName("traffic_light")
    @Expose
    private int trafficLight;
    @SerializedName("is_approvement")
    @Expose
    private boolean isApprovement;

    public Historic(String dateInsert, String vehicleName, String score, int trafficLight, boolean isApprovement) {
        super();
        this.date_Insert = dateInsert;
        this.vehicleName = vehicleName;
        this.score = score;
        this.trafficLight = trafficLight;
        this.isApprovement = isApprovement;
    }

    public String getDate_Insert() {
        return date_Insert;
    }

    public void setDate_Insert(String date_Insert) {
        this.date_Insert = date_Insert;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getTrafficLight() {
        return trafficLight;
    }

    public void setTrafficLight(int trafficLight) {
        this.trafficLight = trafficLight;
    }

    public Boolean getApprovement() {
        return isApprovement;
    }

    public void setApprovement(Boolean approvement) {
        isApprovement = approvement;
    }
}
