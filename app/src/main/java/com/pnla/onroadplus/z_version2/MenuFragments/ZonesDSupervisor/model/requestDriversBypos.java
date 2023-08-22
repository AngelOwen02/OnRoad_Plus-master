package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class requestDriversBypos {

    @SerializedName("flag_parent")
    @Expose
    private Boolean flagParent;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("position")
    private Integer position;

    public requestDriversBypos(boolean flagParent,String token,Integer position)
    {
        this.flagParent=flagParent;
        this.token=token;
        this.position=position;
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
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
