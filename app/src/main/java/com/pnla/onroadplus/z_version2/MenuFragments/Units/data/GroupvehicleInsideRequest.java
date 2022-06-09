package com.pnla.onroadplus.z_version2.MenuFragments.Units.data;

public class GroupvehicleInsideRequest {

    private int cve_vehicle_group;
    private String token;

    /**
     *
     * @param cve_vehicle_group
     * @param token
     */
    public GroupvehicleInsideRequest(int cve_vehicle_group,String token){
        super();
        this.cve_vehicle_group=cve_vehicle_group;
        this.token = token;
    }

    public int getCve_vehicle_group() {
        return cve_vehicle_group;
    }

    public void setCve_vehicle_group(int cve_vehicle_group) {
        this.cve_vehicle_group = cve_vehicle_group;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
