package com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class zoneResponse1 {

    @SerializedName("responseCode")
    @Expose
    private int responseCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private zonesData1[] data = null;

    public zoneResponse1(int responseCode,String message,zonesData1[] data)
    {
        super();
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

    public zonesData1[] getData() {
        return data;
    }

    public void setData(zonesData1[] data) {
        this.data = data;
    }


}
