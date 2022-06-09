package com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models;

public class tripsbyTimeRequest {
    private int cve_vehicle;
    private String fin;
    private String ini;
    private String token;

    /**
     *
     * @param cve_vehicle
     * @param fin
     * @param token
     */
    public tripsbyTimeRequest(int cve_vehicle,String fin,String ini,String token)
    {
        this.cve_vehicle=cve_vehicle;
        this.fin=fin;
        this.ini=ini;
        this.token=token;
    }

    public int getCve_vehicle() {
        return cve_vehicle;
    }

    public void setCve_vehicle(int cve_vehicle) {
        this.cve_vehicle = cve_vehicle;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String day) {
        this.fin = fin;
    }
    public String getIni() {
        return ini;
    }

    public void setIni(String ini) {
        this.ini = ini;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
