package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.vehicles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseUnits {


    @SerializedName("responseCode")
    @Expose
    private int responseCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<UnitsCves> data = null;

    public responseUnits(int responseCode,String message,List<UnitsCves> data)
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

    public List<UnitsCves> getData() {
        return data;
    }

    public void setData(List<UnitsCves> data) {
        this.data = data;
    }

}
