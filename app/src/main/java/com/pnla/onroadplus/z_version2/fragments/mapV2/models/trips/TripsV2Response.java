package com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips;

public class TripsV2Response {

    private int responseCode;
    private String message;
    private TripsV2Data data;

    /**
     * @param message
     * @param responseCode
     * @param data
     */
    public TripsV2Response(int responseCode, String message, TripsV2Data data) {
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

    public TripsV2Data getData() {
        return data;
    }

    public void setData(TripsV2Data data) {
        this.data = data;
    }

}
