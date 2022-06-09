package com.pnla.onroadplus.z_version2.fragments.mapV2.models.vehicleDescription;

public class VehicleDescriptionRequest {

    private int cve_vehicle;
    private String token;

    /**
     * @param token
     * @param cve_vehicle
     */
    public VehicleDescriptionRequest(int cve_vehicle, String token) {
        super();
        this.cve_vehicle = cve_vehicle;
        this.token = token;
    }

    public int getCveVehicle() {
        return cve_vehicle;
    }

    public void setCveVehicle(int cve_vehicle) {
        this.cve_vehicle = cve_vehicle;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
