package com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class vehicleGroupRequest {
    @SerializedName("token")
    @Expose
    private String token;


    public vehicleGroupRequest(String token)
    {
        this.token=token;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
