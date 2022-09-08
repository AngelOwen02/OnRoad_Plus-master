package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.model.sections;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseSections {


    @SerializedName("responseCode")

    private Integer responseCode;
    @SerializedName("message")

    private String message;
    @SerializedName("data")

    private List<dataSections> data = null;


    public responseSections(Integer responseCode, String message, List<dataSections> data) {
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

    public List<dataSections> getData() {
        return data;
    }

    public void setData(List<dataSections> data) {
        this.data = data;
    }

}
