package com.pnla.onroadplus.z_version2.MenuFragments.Zones.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class pointsResponse {
    @SerializedName("responseCode")
    @Expose
    private int responseCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<pointsData> data = null;

    public pointsResponse(int responseCode,String message, List<pointsData> data)
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

    public  List<pointsData> getData() {
        return data;
    }

    public void setData( List<pointsData> data) {
        this.data = data;
    }

}