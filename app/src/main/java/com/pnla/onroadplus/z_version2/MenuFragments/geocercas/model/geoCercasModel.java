package com.pnla.onroadplus.z_version2.MenuFragments.geocercas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class geoCercasModel {

    @SerializedName("responseCode")
    @Expose
    private int responseCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Data> data = null;

    public geoCercasModel(int responseCode,String message,List<Data> data)
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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

}