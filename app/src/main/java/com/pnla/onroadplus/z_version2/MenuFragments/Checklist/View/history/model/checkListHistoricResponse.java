package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class checkListHistoricResponse {

    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    //private datachecklistHistoric data;
    private List<Historic> data= null;

    //public checkListHistoricResponse(Integer responseCode, String message, datachecklistHistoric data) {
    public checkListHistoricResponse(int responseCode, String message, List<Historic> data) {
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

    public List<Historic> getData() {
        return data;
    }

    public void setData(List<Historic> data) {
        this.data = data;
    }

    /**public datachecklistHistoric getData() {
        return data;
    }

    public void setData(datachecklistHistoric data) {
        this.data = data;
    }*/
}
