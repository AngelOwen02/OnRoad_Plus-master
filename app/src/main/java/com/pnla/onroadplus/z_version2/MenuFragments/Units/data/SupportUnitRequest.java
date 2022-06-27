package com.pnla.onroadplus.z_version2.MenuFragments.Units.data;

import java.util.List;

public class SupportUnitRequest {

    private String token;
    //private int boolList;
    //private List<Integer> vehicleList = null;

    public SupportUnitRequest(String token) {
        super();
        this.token = token;
        //this.boolList = boolList;
        //this.vehicleList = vehicleList;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /**public int getBoolList() {
        return boolList;
    }

    public void setBoolList(int boolList) {
        this.boolList = boolList;
    }*/

    /**public List<Integer> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Integer> vehicleList) {
        this.vehicleList = vehicleList;
    }*/
}
