package com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.model;

public class VersionResponse {
    private int responseCode;
    private String message;
    private Version data;



    public VersionResponse(int responseCode, String message, Version data){
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

    public Version getData() {
        return data;
    }

    public void setData(Version data) {
        this.data = data;
    }
}
