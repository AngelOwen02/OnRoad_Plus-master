package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model;

import com.google.gson.annotations.SerializedName;

public class checklistRequest {

    @SerializedName("token")

    private String token;

    public checklistRequest(String token) {
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
