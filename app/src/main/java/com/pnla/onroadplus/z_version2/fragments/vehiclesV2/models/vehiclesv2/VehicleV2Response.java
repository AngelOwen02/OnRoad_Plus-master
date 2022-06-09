package com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2;

public class VehicleV2Response {

    private int responseCode;
    private String message;
    private VehiclesV2Data data;

    /**
     * @param message
     * @param responseCode
     * @param data
     */
    public VehicleV2Response(int responseCode, String message, VehiclesV2Data data) {
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

    public VehiclesV2Data getData() {
        return data;
    }

    public void setData(VehiclesV2Data data) {
        this.data = data;
    }

}
