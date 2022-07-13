package com.pnla.onroadplus.z_version2.MenuFragments.Units.data;

public class SingleSupportUnitRequest {

    private int cve_layer;
    private String token;

    public SingleSupportUnitRequest(int cve_layer, String token){
        super();
        this.cve_layer = cve_layer;
        this.token = token;
    }

    public int getCve_layer() {
        return cve_layer;
    }

    public void setCve_layer(int cve_layer) {
        this.cve_layer = cve_layer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
