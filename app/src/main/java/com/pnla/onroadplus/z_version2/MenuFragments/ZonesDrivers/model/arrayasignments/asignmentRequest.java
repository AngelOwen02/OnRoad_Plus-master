package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.arrayasignments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class asignmentRequest {
    @SerializedName("cve_layer")
    @Expose
    private Integer cveLayer;
    @SerializedName("token")
    @Expose
    private String token;

    public asignmentRequest(Integer cveLayer,String token)
    {
        this.cveLayer=cveLayer;
        this.token=token;
    }

    public Integer getCveLayer() {
        return cveLayer;
    }

    public void setCveLayer(Integer cveLayer) {
        this.cveLayer = cveLayer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
