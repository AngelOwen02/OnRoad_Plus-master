package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model;

import com.google.gson.annotations.SerializedName;

public class requestVehicleDescV3 {

    @SerializedName("cve_vehicle")
    private Integer cveVehicle;
    @SerializedName("token")
    private String token;

    public requestVehicleDescV3(Integer cveVehicle, String token) {
        super();
        this.cveVehicle = cveVehicle;
        this.token = token;
    }

    public Integer getCveVehicle() {
        return cveVehicle;
    }

    public void setCveVehicle(Integer cveVehicle) {
        this.cveVehicle = cveVehicle;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
