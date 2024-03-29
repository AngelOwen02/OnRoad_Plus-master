package com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitsLegacy;

import java.util.List;

public class requestUnitsLegacy {
    private String token;
    private int boolList;
    private List<Integer> vehicleList = null;

    public requestUnitsLegacy(String token, int boolList, List<Integer> vehicleList) {
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
