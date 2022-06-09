package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.asignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class asignmentResponse {


    @SerializedName("responseCode")
    @Expose
    private String responseCode;
    @SerializedName("message")
    @Expose
    private String message;

    public asignmentResponse(String responseCode,String message)
    {
        this.responseCode=responseCode;
        this.message=message;
    }
    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
