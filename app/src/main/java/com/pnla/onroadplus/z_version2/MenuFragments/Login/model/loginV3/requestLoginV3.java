package com.pnla.onroadplus.z_version2.MenuFragments.Login.model.loginV3;

import com.google.gson.annotations.SerializedName;

public class requestLoginV3 {
    @SerializedName("password")
    private String password;
    @SerializedName("user")
    private String user;


    public requestLoginV3(String password, String user) {
        super();
        this.password = password;
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
