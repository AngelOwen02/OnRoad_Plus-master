package com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.model;

public class MenuRequest {

    private int cve_object;
    private String token;



    /**
     * @param cve_object
     * @param token
     */
    public MenuRequest(int cve_object, String token) {
        super();
        this.cve_object = cve_object;
        this.token = token;
    }



    public int getCve_object() {
        return cve_object;
    }

    public void setCve_object(int cve_object) {
        this.cve_object = cve_object;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
