package com.pnla.onroadplus.z_version2.fragments.commands.models.get;

public class CommandsV2Request {

    private int cve_vehicle;
    private String token;

    /**
     * @param token
     * @param cve_vehicle
     */
    public CommandsV2Request(int cve_vehicle, String token) {
        super();
        this.cve_vehicle = cve_vehicle;
        this.token = token;
    }

    public int getCve_vehicle() {
        return cve_vehicle;
    }

    public void setCve_vehicle(int cve_vehicle) {
        this.cve_vehicle = cve_vehicle;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
