package com.pnla.onroadplus.z_version2.MenuFragments.Login.model.loginV3;

import com.google.gson.annotations.SerializedName;

public class responseLoginV3 {
    @SerializedName("responseCode")
    private Integer responseCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private dataLoginV3 data;


    public responseLoginV3(Integer responseCode, String message, dataLoginV3 data) {
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

    public dataLoginV3 getData() {
        return data;
    }

    public void setData(dataLoginV3 data) {
        this.data = data;
    }
}
