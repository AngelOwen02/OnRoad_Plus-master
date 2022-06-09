package com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models;


public class TripByDayResponse {
    private int responseCode;
    private String message;
    private TripsbyDayData data;

    /**
     *
     * @param responseCode
     * @param message
     * @param data
     */
    public TripByDayResponse(int responseCode,String message,TripsbyDayData data)
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

    public TripsbyDayData getData() {
        return data;
    }

    public void setData(TripsbyDayData data) {
        this.data = data;
    }
}

