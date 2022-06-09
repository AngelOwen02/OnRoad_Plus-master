package com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models;

public class NotificationsResponse {

    private int responseCode;
    private String message;
    private NotificationsData data;

    /**
     * @param message
     * @param responseCode
     * @param data
     */
    public NotificationsResponse(int responseCode, String message, NotificationsData data) {
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

    public NotificationsData getData() {
        return data;
    }

    public void setData(NotificationsData data) {
        this.data = data;
    }

}
