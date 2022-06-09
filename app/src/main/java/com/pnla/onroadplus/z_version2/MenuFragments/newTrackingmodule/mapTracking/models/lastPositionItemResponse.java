package com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.mapTracking.models;

public class lastPositionItemResponse {

    private int responseCode;
    private String message;
    private lastData data;

    public lastPositionItemResponse(int responseCode, String message, lastData data)
    {

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

    public lastData getData() {
        return data;
    }

    public void setData(lastData data) {
        this.data = data;
    }




}
