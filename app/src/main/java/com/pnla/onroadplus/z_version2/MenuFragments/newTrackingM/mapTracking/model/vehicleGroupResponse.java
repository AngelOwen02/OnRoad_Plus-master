package com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class vehicleGroupResponse {
    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private vehicleGroupData data;

    public vehicleGroupResponse( Integer responseCode,String message,vehicleGroupData data)
    {
        this.responseCode=responseCode;
        this.message=message;
        this.data=data;

    }
    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public vehicleGroupData getData() {
        return data;
    }

    public void setData(vehicleGroupData data) {
        this.data = data;
    }
}
