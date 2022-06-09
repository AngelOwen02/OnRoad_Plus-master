package com.pnla.onroadplus.z_version2;

import com.pnla.onroadplus.z_version2.realmOnRoad.BaseRealmApplication;

import io.realm.RealmObject;

public class UserData extends RealmObject {

    private int id;
    private String employee_name;
    private Boolean firstLogin;
    private String userImage;
    private String token;
    private String email;
    private String  user_cve;

    public UserData(){}



    public UserData(String employee_name, Boolean firstLogin, String userImage, String token, String email, String  user_cve) {
        this.id = BaseRealmApplication.USER_DATA.incrementAndGet();
        this.employee_name = employee_name;
        this.firstLogin = firstLogin;
        this.userImage = userImage;
        this.token = token;
        this.email = email;
        this.user_cve=user_cve;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public Boolean getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getUser_cve() {
        return user_cve;
    }

    public void setUser_cve(String user_cve) {
        this.user_cve = user_cve;
    }
}
