package com.pnla.onroadplus.z_version2.realmOnRoad;

import android.app.Application;

import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.model.VehicleTracking;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.Group;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.User;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class BaseRealmApplication extends Application {

    public static AtomicInteger VEHICLE_ID = new AtomicInteger();
    public static AtomicInteger UNIT_ID = new AtomicInteger();
    public static AtomicInteger GROUP_ID = new AtomicInteger();
    public static AtomicInteger USER_ID = new AtomicInteger();
    public static AtomicInteger USER_DATA = new AtomicInteger();


    @Override
    public void onCreate() {
        super.onCreate();

        realmInitSetup();
        realmIdSetup();

    }

    public void realmInitSetup() {
        Realm.init(this);
        RealmConfiguration realmSetup = new RealmConfiguration.Builder()
                .name("OnRoadDB")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmSetup);
    }

    public void realmIdSetup() {
        Realm realm = Realm.getDefaultInstance();
        VEHICLE_ID = getIdByTable(realm, VehicleTracking.class);
        UNIT_ID = getIdByTable(realm, Unit.class);
        GROUP_ID = getIdByTable(realm, Group.class);
        USER_ID = getIdByTable(realm, User.class);

        USER_DATA = getIdByTable(realm, User.class);

        realm.close();
    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass) {
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0) ? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();
    }

}
