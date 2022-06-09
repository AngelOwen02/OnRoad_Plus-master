package com.pnla.onroadplus.z_version2;

import io.realm.Realm;
import io.realm.RealmResults;

public class UserDataDB {

    public Realm realm;

    public UserDataDB(Realm realm) {
        this.realm = realm;
    }

    public static void createUserData(String employeeName, Boolean firstLogin, String userImage, String token, String email,String cve_name) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        UserData userData = new UserData(employeeName, firstLogin, userImage, token, email,cve_name);
        realm.copyToRealm(userData);
        realm.commitTransaction();

    }

    public static UserData getUserData() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(UserData.class).findFirst();
    }

    public static boolean isEmpty() {
        Realm realm = Realm.getDefaultInstance();
        UserData userData = realm.where(UserData.class).findFirst();
        return userData == null;
    }

    public static void deleteDB() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<UserData> songRealmResults = realm.where(UserData.class).findAll();
        songRealmResults.deleteAllFromRealm();
        realm.commitTransaction();
    }
}
