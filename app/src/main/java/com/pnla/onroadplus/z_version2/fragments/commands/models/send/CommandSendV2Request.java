package com.pnla.onroadplus.z_version2.fragments.commands.models.send;

public class CommandSendV2Request {

    private String token;
    private int cve_device;
    private int cve_routine;
    private int cve_vehicle;

    /**
     * @param token
     * @param cve_routine
     * @param cve_device
     */
    public CommandSendV2Request(String token, int cve_device, int cve_routine, int cve_vehicle) {
        super();
        this.token = token;
        this.cve_device = cve_device;
        this.cve_routine = cve_routine;
        this.cve_vehicle = cve_vehicle;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCve_device() {
        return cve_device;
    }

    public void setCve_device(int cve_device) {
        this.cve_device = cve_device;
    }

    public int getCve_routine() {
        return cve_routine;
    }

    public void setCve_routine(int cve_routine) {
        this.cve_routine = cve_routine;
    }

    public int getCve_vehicle() {
        return cve_vehicle;
    }

    public void setCve_vehicle(int cve_vehicle) {
        this.cve_vehicle = cve_vehicle;
    }


}
