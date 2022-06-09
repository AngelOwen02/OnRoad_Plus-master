package com.pnla.onroadplus.z_version2.Containers.ModelVersion;

public class VersionRequest {

    private String app_name;
    private String token;

    public VersionRequest(String app_name, String token){
        this.app_name = app_name;
        this.token = token;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
