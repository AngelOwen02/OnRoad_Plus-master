package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.TripsByTimeV3;

import com.google.gson.annotations.SerializedName;

public class tripsbyTimeResponse3 {
    @SerializedName("responseCode")
    private Integer responseCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private Datapos data;

    public tripsbyTimeResponse3(Integer responseCode, String message, Datapos data) {
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

    public Datapos getData() {
        return data;
    }

    public void setData(Datapos data) {
        this.data = data;
    }

}

