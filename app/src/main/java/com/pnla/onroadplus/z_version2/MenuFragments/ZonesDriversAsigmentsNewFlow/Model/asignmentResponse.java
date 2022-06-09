package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class asignmentResponse {

    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public asignmentResponse (Integer responseCode,String message,List<Datum> data )
    {
        this.responseCode=responseCode;
        this.message=message;
        this.data=data;
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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}
