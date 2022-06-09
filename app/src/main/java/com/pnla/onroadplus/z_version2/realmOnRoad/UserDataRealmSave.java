package com.pnla.onroadplus.z_version2.realmOnRoad;

import io.realm.RealmObject;

public class UserDataRealmSave extends RealmObject {

    private String userName;
    private String email;
    private String password;
    private boolean isFirstTime;

    public UserDataRealmSave() {
    }

    public UserDataRealmSave(String userName, String email, String password, boolean isFirstTime) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.isFirstTime = isFirstTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFirstTime() {
        return isFirstTime;
    }

    public void setFirstTime(boolean firstTime) {
        isFirstTime = firstTime;
    }
}
