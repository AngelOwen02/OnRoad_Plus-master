package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.drivers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responsDrivers {


    @SerializedName("responseCode")
    @Expose
    private int responseCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<driversNames> data = null;

    public responsDrivers(int responseCode,String message,List<driversNames> data)
    {
        this.responseCode=responseCode;
        this.message=message;
        this.data=data;
    }
    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<driversNames> getData() {
        return data;
    }

    public void setData(List<driversNames> data) {
        this.data = data;
    }
}
