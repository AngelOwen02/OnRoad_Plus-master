package com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class unitsRequest {


    @SerializedName("boolList")
    @Expose
    private Integer boolList;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("vehicleList")
    @Expose
    private List<Integer> vehicleList = null;

    public unitsRequest(Integer boolList,String token,List<Integer> vehicleList)
    {
        this.boolList=boolList;
        this.token=token;
        this.vehicleList=vehicleList;

    }



    public Integer getBoolList() {
        return boolList;
    }

    public void setBoolList(Integer boolList) {
        this.boolList = boolList;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Integer> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Integer> vehicleList) {
        this.vehicleList = vehicleList;
    }


}
