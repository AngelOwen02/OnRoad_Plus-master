package com.pnla.onroadplus.z_version2.fragments.restorePasswordV2.models;

public class RestorePasswordV2Request {

    private String email;

    /**
     * @param email
     */
    public RestorePasswordV2Request(String email) {
        super();
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
