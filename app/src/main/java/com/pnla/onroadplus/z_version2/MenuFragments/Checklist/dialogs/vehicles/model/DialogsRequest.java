package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.model;

import com.google.gson.annotations.SerializedName;

public class DialogsRequest {

    @SerializedName("flag_parent")
    private boolean flagParent;

    @SerializedName("token")
    private String token;

    public DialogsRequest(boolean flagParent, String token) {
        this.flagParent = flagParent;
        this.token = token;
    }

    public boolean isFlagParent() {
        return flagParent;
    }

    public void setFlagParent(boolean flagParent) {
        this.flagParent = flagParent;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
