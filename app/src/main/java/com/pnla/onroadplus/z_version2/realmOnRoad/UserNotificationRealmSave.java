package com.pnla.onroadplus.z_version2.realmOnRoad;

import io.realm.RealmObject;

public class UserNotificationRealmSave extends RealmObject {
    private String user;
    private boolean activeNotifications;

    public UserNotificationRealmSave() {
    }

    public UserNotificationRealmSave(String user, boolean activeNotifications) {
        this.user = user;
        this.activeNotifications = activeNotifications;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isActiveNotifications() {
        return activeNotifications;
    }

    public void setActiveNotifications(boolean activeNotifications) {
        this.activeNotifications = activeNotifications;
    }
}
