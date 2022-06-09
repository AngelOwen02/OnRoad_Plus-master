package com.pnla.onroadplus.z_version2.realmOnRoad;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmConfig {

    private static final long REALM_VERSION = 0;
    private static final String REALM_DATA_BASE_NAME = "onRoad.realm";

    public static void initRealm(Context context) {
        Realm.init(context);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(REALM_DATA_BASE_NAME)
                .schemaVersion(REALM_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

}
