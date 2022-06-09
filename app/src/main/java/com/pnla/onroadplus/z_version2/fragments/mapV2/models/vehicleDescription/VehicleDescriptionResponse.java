package com.pnla.onroadplus.z_version2.fragments.mapV2.models.vehicleDescription;

public class VehicleDescriptionResponse {

    private int responseCode;
    private String message;
    private VehicleDescriptionData data;

    /**
     * @param message
     * @param responseCode
     * @param data
     */
    public VehicleDescriptionResponse(int responseCode, String message, VehicleDescriptionData data) {
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

    public VehicleDescriptionData getData() {
        return data;
    }

    public void setData(VehicleDescriptionData data) {
        this.data = data;
    }

}
