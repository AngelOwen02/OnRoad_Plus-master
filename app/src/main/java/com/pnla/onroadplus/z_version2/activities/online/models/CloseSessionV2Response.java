package com.pnla.onroadplus.z_version2.activities.online.models;

public class CloseSessionV2Response {

    private int responseCode;
    private String message;

    /**
     * @param message
     * @param responseCode
     */
    public CloseSessionV2Response(int responseCode, String message) {
        super();
        this.responseCode = responseCode;
        this.message = message;
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
}
