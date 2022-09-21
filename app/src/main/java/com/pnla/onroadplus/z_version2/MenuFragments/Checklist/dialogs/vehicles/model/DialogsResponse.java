package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DialogsResponse {

    @SerializedName("responseCode")
    private Integer responseCode;

    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<DialogsData> data = null;

    public DialogsResponse(Integer responseCode, String message, List<DialogsData> data) {
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

    public List<DialogsData> getData() {
        return data;
    }

    public void setData(List<DialogsData> data) {
        this.data = data;
    }
}
