package com.pnla.onroadplus.z_version2.MenuFragments.Units.data;

public class SupportUnitResponse {

    private int responseCode;
    private String message;
    private SupportUnitData data;

    public SupportUnitResponse(int responseCode, String message, SupportUnitData data) {
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

    public SupportUnitData getData() {
        return data;
    }

    public void setData(SupportUnitData data) {
        this.data = data;
    }
}
