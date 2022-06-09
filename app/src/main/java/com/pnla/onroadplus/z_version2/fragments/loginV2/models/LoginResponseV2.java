package com.pnla.onroadplus.z_version2.fragments.loginV2.models;

public class LoginResponseV2 {

    private int responseCode;
    private String message;
    private UserDataV2 data;

    /**
     * @param message
     * @param responseCode
     * @param data
     */
    public LoginResponseV2(int responseCode, String message, UserDataV2 data) {
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

    public UserDataV2 getData() {
        return data;
    }

    public void setData(UserDataV2 data) {
        this.data = data;
    }

}
