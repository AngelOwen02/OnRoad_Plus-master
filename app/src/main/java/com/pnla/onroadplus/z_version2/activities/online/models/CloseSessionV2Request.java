package com.pnla.onroadplus.z_version2.activities.online.models;

public class CloseSessionV2Request {
    private String token;

    /**
     * @param token
     */
    public CloseSessionV2Request(String token) {
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
