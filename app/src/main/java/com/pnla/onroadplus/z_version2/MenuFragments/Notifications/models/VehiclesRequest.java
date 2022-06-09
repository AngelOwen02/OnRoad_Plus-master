package com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models;

import java.util.List;

public class VehiclesRequest {
    private String token;
    private int boolList;
    private List<Integer> vehicleList = null;

    /**
     * @param token
     */
    public VehiclesRequest(String token, int boolList, List<Integer> vehicleList) {
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

    public long getBoolList() {
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
