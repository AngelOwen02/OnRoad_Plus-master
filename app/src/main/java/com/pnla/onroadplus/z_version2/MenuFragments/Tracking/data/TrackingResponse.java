package com.pnla.onroadplus.z_version2.MenuFragments.Tracking.data;

public class TrackingResponse {
    private int responseCode;
    private String message;
    private TrackingData data;

    public TrackingResponse(int responseCode, String message, TrackingData data) {
        super();
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

    public TrackingData getData() {
        return data;
    }

    public void setData(TrackingData data) {
        this.data = data;
    }
}
