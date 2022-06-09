package com.pnla.onroadplus.z_version2.MenuFragments.Units.data;

public class UnitResponse {

    private int responseCode;
    private String message;
    private UnitData data;

    public UnitResponse(int responseCode, String message, UnitData data) {
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

    public UnitData getData() {
        return data;
    }

    public void setData(UnitData data) {
        this.data = data;
    }
}
