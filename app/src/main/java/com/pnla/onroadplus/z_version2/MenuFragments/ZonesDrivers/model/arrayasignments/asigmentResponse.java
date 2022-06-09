package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.arrayasignments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class asigmentResponse {

    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Data> data = null;

    public asigmentResponse (Integer responseCode,String message,List<Data> data )
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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

}
