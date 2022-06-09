package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.checkAsigment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class requestAsigments {

    @SerializedName("token")
    @Expose
    private String token;


    public requestAsigments(String token)
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
