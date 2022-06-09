package com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2;

public class GroupV2Request {

    private String token;

    /**
     * @param token
     */
    public GroupV2Request(String token) {
        super();
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
