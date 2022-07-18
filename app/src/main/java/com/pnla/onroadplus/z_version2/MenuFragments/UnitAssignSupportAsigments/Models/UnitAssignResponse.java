package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnitAssignResponse {

    @SerializedName("responseCode")
    private Integer responseCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private String data;

    public UnitAssignResponse(Integer responseCode, String message, String data) {
        super();
        this.responseCode = responseCode;
        this.message = message;
        this.data = data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
