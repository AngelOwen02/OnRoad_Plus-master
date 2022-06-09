package com.pnla.onroadplus.z_version2.MenuFragments.geocercas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class geoCercasRequest {


    @SerializedName("token")
    @Expose
    private String token;


    public geoCercasRequest(String token)
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
