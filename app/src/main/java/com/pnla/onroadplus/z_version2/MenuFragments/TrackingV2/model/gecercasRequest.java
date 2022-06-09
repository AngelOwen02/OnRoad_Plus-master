package com.pnla.onroadplus.z_version2.MenuFragments.TrackingV2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class gecercasRequest {

    @SerializedName("cve_object")
    @Expose
    private int cveObject;
    @SerializedName("token")
    @Expose
    private String token;

    public gecercasRequest(int cveObject,String token)
    {
        this.cveObject=cveObject;
        this.token=token;

    }

    public int getCveObject() {
        return cveObject;
    }

    public void setCveObject(int cveObject) {
        this.cveObject = cveObject;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
