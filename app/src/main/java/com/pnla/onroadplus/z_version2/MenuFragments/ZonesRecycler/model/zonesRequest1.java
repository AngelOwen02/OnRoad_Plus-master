package com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class zonesRequest1 {

    @SerializedName("token")
    @Expose
    private String token;


    public zonesRequest1(String token)
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
