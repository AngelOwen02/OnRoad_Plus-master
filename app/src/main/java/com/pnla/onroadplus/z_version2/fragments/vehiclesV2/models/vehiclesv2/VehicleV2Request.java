package com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2;

import java.util.List;

public class VehicleV2Request {

    private String token;
    private int boolList;
    private List<Integer> vehicleList = null;

    /**
     * @param token
     */
    public VehicleV2Request(String token, int boolList, List<Integer> vehicleList) {
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
