package com.pnla.onroadplus.z_version2.MenuFragments.Login.model.loginV3.origin;

import com.google.gson.annotations.SerializedName;
import com.pnla.onroadplus.z_version2.MenuFragments.Login.model.loginV3.dataLoginV3;

public class responseOrigin {
    @SerializedName("responseCode")
    private Integer responseCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private dataOrigin data;


    public responseOrigin(Integer responseCode, String message, dataOrigin data) {
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

    public dataOrigin getData() {
        return data;
    }

    public void setData(dataOrigin data) {
        this.data = data;
    }
}
