package com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models;

public class tripsbyTimeResponse {
    private int responseCode;
    private String message;
    private tripsBytimeData data;

    /**
     *
     * @param responseCode
     * @param message
     * @param data
     */
    public tripsbyTimeResponse(int responseCode,String message,tripsBytimeData data)
    {
        super();
        this.responseCode=responseCode;
        this.message=message;
        this.data=data;
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

    public tripsBytimeData getData() {
        return data;
    }

    public void setData(tripsBytimeData data) {
        this.data = data;
    }
}
