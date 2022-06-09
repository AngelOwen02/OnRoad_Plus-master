package com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class pointsResponse1 {
    @SerializedName("responseCode")
    @Expose
    private int responseCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<pointsData1> data = null;

    public pointsResponse1(int responseCode,String message, List<pointsData1> data)
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

    public  List<pointsData1> getData() {
        return data;
    }

    public void setData( List<pointsData1> data) {
        this.data = data;
    }

}