package com.pnla.onroadplus.z_version2.MenuFragments.Units.data;

public class GroupResponse {

    private int responseCode;
    private String message;
    private GroupData data;

    public GroupResponse(int responseCode, String message, GroupData data) {
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

    public GroupData getData() {
        return data;
    }

    public void setData(GroupData data) {
        this.data = data;
    }
}
