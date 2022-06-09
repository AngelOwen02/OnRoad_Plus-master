package com.pnla.onroadplus.z_version2.fragments.mapV2.models;

public class VehicleV2MapResponse {

    private int responseCode;
    private String message;
    private VehiclesV2MapData data;

    /**
     * @param message
     * @param responseCode
     * @param data
     */
    public VehicleV2MapResponse(int responseCode, String message, VehiclesV2MapData data) {
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

    public VehiclesV2MapData getData() {
        return data;
    }

    public void setData(VehiclesV2MapData data) {
        this.data = data;
    }

}
