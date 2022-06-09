package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class asignmentUpdateResponse {

    @SerializedName("responseCode")
    @Expose
    private String responseCode;
    @SerializedName("message")
    @Expose
    private String message;

    public asignmentUpdateResponse(String responseCode,String message)
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
