package com.pnla.onroadplus.z_version2.MenuFragments.Units.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.UnitSupport;

import java.util.List;

public class SupportUnitResponse {
    @SerializedName("responseCode")
    @Expose
    private int responseCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<SupportUnitData> data = null;
    //private SupportUnitData data;

    public SupportUnitResponse(int responseCode, String message, List<SupportUnitData> data) {
    //public SupportUnitResponse(int responseCode, String message, SupportUnitData data) {
        this.responseCode = responseCode;
        this.message = message;
        this.data = data;
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

    public List<SupportUnitData> getData() {
        return data;
    }

    public void setData(List<SupportUnitData> data) {
        this.data = data;
    }
}
