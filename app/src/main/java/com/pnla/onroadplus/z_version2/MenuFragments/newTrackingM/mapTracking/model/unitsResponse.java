package com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.model;

public class unitsResponse {

    private int responseCode;
    private String message;
    private dataUnits data;

    public unitsResponse(int responseCode, String message, dataUnits data) {
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

    public dataUnits getData() {
        return data;
    }

    public void setData(dataUnits data) {
        this.data = data;
    }
}
