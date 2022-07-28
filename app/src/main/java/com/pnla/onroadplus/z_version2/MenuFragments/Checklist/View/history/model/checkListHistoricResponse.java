package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.model;

import com.google.gson.annotations.SerializedName;

public class checkListHistoricResponse {

    @SerializedName("responseCode")
    private Integer responseCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private datachecklistHistoric data;

    public checkListHistoricResponse(Integer responseCode, String message, datachecklistHistoric data) {
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

    public datachecklistHistoric getData() {
        return data;
    }

    public void setData(datachecklistHistoric data) {
        this.data = data;
    }
}
