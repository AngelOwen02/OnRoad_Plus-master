package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.vehicles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class requestUnits {

    @SerializedName("flag_parent")
    @Expose
    private Boolean flagParent;
    @SerializedName("token")
    @Expose
    private String token;

    public requestUnits(boolean flagParent,String token)
    {
        this.flagParent=flagParent;
        this.token=token;
    }

    public Boolean getFlagParent() {
        return flagParent;
    }

    public void setFlagParent(Boolean flagParent) {
        this.flagParent = flagParent;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}