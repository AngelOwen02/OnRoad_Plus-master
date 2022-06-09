package com.pnla.onroadplus.z_version2.MenuFragments.Tracking.data;

public class GroupTrackingResponse {

    private int responseCode;
    private String message;
    private GroupTrackingData data;

    public GroupTrackingResponse(int responseCode, String message, GroupTrackingData data) {
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

    public GroupTrackingData getData() {
        return data;
    }

    public void setData(GroupTrackingData data) {
        this.data = data;
    }
}
