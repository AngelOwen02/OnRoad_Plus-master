package com.pnla.onroadplus.z_version2.MenuFragments.Units.data;

public class GeoreferenceResponse {
    private int responseCode;
    private String message;
    private Directions data;

    public GeoreferenceResponse(int responseCode, String message, Directions data) {
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


    public Directions getData() {
        return data;
    }

    public void setData(Directions data) {
        this.data = data;
    }

}
