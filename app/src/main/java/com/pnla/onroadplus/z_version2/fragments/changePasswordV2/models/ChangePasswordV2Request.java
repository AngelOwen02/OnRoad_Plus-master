package com.pnla.onroadplus.z_version2.fragments.changePasswordV2.models;

public class ChangePasswordV2Request {
    private String newPassword;
    private String token;

    /**
     * @param token
     * @param newPassword
     */
    public ChangePasswordV2Request(String newPassword, String token) {
        super();
        this.newPassword = newPassword;
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
