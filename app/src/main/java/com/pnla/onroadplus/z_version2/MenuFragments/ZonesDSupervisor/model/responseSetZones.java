package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class responseSetZones {
    @SerializedName("responseCode")
    @Expose
    private int responseCode;

    @SerializedName("message")
    @Expose
    private String message;
    public responseSetZones(int responseCode,String message)
    {
        this.responseCode=responseCode;
        this.message=message;

    }
    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
