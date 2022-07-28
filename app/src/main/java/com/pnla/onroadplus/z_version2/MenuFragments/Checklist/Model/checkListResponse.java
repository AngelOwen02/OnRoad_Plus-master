package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class checkListResponse {

    @SerializedName("responseCode")

    private Integer responseCode;
    @SerializedName("message")

    private String message;
    @SerializedName("data")

    private List<checkListdata> data = null;

    public checkListResponse(Integer responseCode, String message, List<checkListdata> data) {
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

    public List<checkListdata> getData() {
        return data;
    }

    public void setData(List<checkListdata> data) {
        this.data = data;
    }

}
