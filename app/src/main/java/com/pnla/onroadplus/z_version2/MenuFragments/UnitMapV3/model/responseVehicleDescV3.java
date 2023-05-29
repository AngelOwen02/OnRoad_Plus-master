package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model;

import com.google.gson.annotations.SerializedName;

public class responseVehicleDescV3 {
    @SerializedName("responseCode")
    private Integer responseCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private dataVehicleDescV3 data;

    public responseVehicleDescV3(Integer responseCode, String message, dataVehicleDescV3 data) {
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

    public dataVehicleDescV3 getData() {
        return data;
    }

    public void setData(dataVehicleDescV3 data) {
        this.data = data;
    }


}