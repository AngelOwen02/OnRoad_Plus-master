package com.pnla.onroadplus.z_version2.MenuFragments.Zones.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class visitedRequest {

    @SerializedName("cve_layers")
    @Expose
    private List<Integer> cveLayers = null;
    @SerializedName("token")
    @Expose
    private String token;

    public visitedRequest( List<Integer> cveLayers,String token)
    {
        this.cveLayers=cveLayers;
        this.token=token;
    }

    public List<Integer> getCveLayers() {
        return cveLayers;
    }

    public void setCveLayers(List<Integer> cveLayers) {
        this.cveLayers = cveLayers;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
