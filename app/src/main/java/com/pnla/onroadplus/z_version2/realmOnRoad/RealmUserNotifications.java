package com.pnla.onroadplus.z_version2.realmOnRoad;

import io.realm.Realm;

public class RealmUserNotifications {

    public static UserNotificationRealmSave existUserNotification(String user) {
        Realm realm = Realm.getDefaultInstance();
        UserNotificationRealmSave realmUser = realm.where(UserNotificationRealmSave.class).equalTo("user", user).findFirst();
        if (realmUser != null) {
            return realmUser;
        } else {
            return null;
        }
    }

    public static void saveUserNotifications(String userName, boolean isActiveNotifications) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        UserNotificationRealmSave userNotificationRealmSave = realm.createObject(UserNotificationRealmSave.class);
        userNotificationRealmSave.setUser(userName);
        userNotificationRealmSave.setActiveNotifications(isActiveNotifications);
        realm.commitTransaction();
    }

    public static void updateUserNotification(String userName, boolean isActiveNotifications) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        UserNotificationRealmSave user = realm.where(UserNotificationRealmSave.class).equalTo("user", userName).findFirst();
        if (user != null) {
            user.setActiveNotifications(isActiveNotifications);
            realm.insertOrUpdate(user);
        }
        realm.commitTransaction();
    }

}