package com.pnla.onroadplus.z_version2.fragments.notifications_v2.models;

public class NotificationsV2Response {

    private int responseCode;
    private String message;
    private NotificationsV2Data data;

    /**
     * @param message
     * @param responseCode
     * @param data
     */
    public NotificationsV2Response(int responseCode, String message, NotificationsV2Data data) {
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

    public NotificationsV2Data getData() {
        return data;
    }

    public void setData(NotificationsV2Data data) {
        this.data = data;
    }

}
