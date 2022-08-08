package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sections;

import com.google.gson.annotations.SerializedName;

public class requestmySections {
    @SerializedName("token")

    private String token;

    public requestmySections(String token) {
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
