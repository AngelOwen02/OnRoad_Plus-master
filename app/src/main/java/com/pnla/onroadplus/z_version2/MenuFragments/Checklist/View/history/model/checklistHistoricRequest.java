package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.model;

import com.google.gson.annotations.SerializedName;

public class checklistHistoricRequest {

    @SerializedName("token")

    private String token;

    public checklistHistoricRequest(String token) {
        super();
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
