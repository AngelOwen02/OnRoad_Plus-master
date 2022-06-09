package com.pnla.onroadplus.z_version2.fragments.loginV2.models;

public class LoginRequestV2 {

    private String user;
    private String password;

    /**
     * @param user
     * @param password
     */
    public LoginRequestV2(String user, String password) {
        super();
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
