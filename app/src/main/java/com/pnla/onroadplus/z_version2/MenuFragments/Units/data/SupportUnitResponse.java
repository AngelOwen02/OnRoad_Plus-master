package com.pnla.onroadplus.z_version2.MenuFragments.Units.data;

public class SupportUnitResponse {

    private int responseCode;
    private String message;
    private SupportUnitData supportUnitData;

    public SupportUnitResponse(int responseCode, String message, SupportUnitData supportUnitData) {
        this.responseCode = responseCode;
        this.message = message;
        this.supportUnitData = supportUnitData;
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
        return supportUnitData;
    }

    public void setData(SupportUnitData supportUnitData) {
        this.supportUnitData = supportUnitData;
    }
}
