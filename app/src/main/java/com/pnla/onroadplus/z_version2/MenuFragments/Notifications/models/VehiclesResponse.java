package com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models;

import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.utils.clases.VehiclesData;

public class VehiclesResponse {
    private int responseCode;
    private String message;
    private VehiclesData data;

    /**
     * @param message
     * @param responseCode
     * @param data
     */
    public VehiclesResponse(int responseCode, String message, VehiclesData data) {
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

    public VehiclesData getData() {
        return data;
    }

    public void setData(VehiclesData data) {
        this.data = data;
    }

}
