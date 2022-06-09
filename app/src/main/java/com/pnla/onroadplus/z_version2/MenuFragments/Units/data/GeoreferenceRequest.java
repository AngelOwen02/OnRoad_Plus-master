package com.pnla.onroadplus.z_version2.MenuFragments.Units.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeoreferenceRequest {
    private String token;
    private int boolList;
    @SerializedName("vehicleList")
    @Expose
    private List<Integer> vehicleList;

    public GeoreferenceRequest(String token, int boolList, List<Integer> vehicleList) {
        super();
        this.token = token;
        this.boolList = boolList;
        this.vehicleList = vehicleList;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getBoolList() {
        return boolList;
    }

    public void setBoolList(int boolList) {
        this.boolList = boolList;
    }

    public List<Integer> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Integer> vehicleList) {
        this.vehicleList = vehicleList;
    }
}
