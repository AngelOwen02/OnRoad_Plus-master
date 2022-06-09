package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.checkAsigment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class responseAsigment {

    @SerializedName("responseCode")
    @Expose
    private int responseCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private dataAsignment[] data = null;

    public responseAsigment(int responseCode,String message,dataAsignment[] data)
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

    public dataAsignment[] getData() {
        return data;
    }

    public void setData(dataAsignment[] data) {
        this.data = data;
    }


}
