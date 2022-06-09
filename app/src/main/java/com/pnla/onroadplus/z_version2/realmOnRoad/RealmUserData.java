package com.pnla.onroadplus.z_version2.realmOnRoad;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmUserData {

    public static void saveUser(String userName, String email, String password, boolean isFirstTime) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        UserDataRealmSave realmUser = realm.createObject(UserDataRealmSave.class);
        realmUser.setUserName(userName);
        realmUser.setEmail(email);
        realmUser.setPassword(password);
        realmUser.setFirstTime(isFirstTime);
        realm.commitTransaction();
        //realm.close();
    }

    public static boolean existUser(String user) {
        Realm realm = Realm.getDefaultInstance();
        UserDataRealmSave realmUser = realm.where(UserDataRealmSave.class).equalTo("userName", user).findFirst();
        if (realmUser != null) {
            //realm.close();
            return true;
        } else {
            //realm.close();
            return false;
        }
    }

    public static void updateUserRegistered(String user, boolean userRegistered) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        UserDataRealmSave userDataRealmSave = realm.where(UserDataRealmSave.class).equalTo("userName", user).findFirst();
        if (userDataRealmSave != null) {
            userDataRealmSave.setFirstTime(userRegistered);
            realm.commitTransaction();
        } else {
            realm.commitTransaction();
        }
    }

    public static void deleteDB() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<UserDataRealmSave> unitRealmResults = realm.where(UserDataRealmSave.class).findAll();
        unitRealmResults.deleteAllFromRealm();
        realm.commitTransaction();
    }

    public static boolean existUsers() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<UserDataRealmSave> areThereUsers = realm.where(UserDataRealmSave.class).findAll();
        if (areThereUsers != null && areThereUsers.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isFirstTimeThatUserUsedApp(String user) {
        Realm realm = Realm.getDefaultInstance();
        UserDataRealmSave userDataRealmSave = realm.where(UserDataRealmSave.class).equalTo("userName", user).findFirst();
        if (userDataRealmSave != null) {
            boolean status = userDataRealmSave.isFirstTime();
            if (status) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

}
