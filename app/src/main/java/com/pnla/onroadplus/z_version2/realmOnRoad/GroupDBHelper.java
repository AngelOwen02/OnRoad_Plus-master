package com.pnla.onroadplus.z_version2.realmOnRoad;

import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.model.VehicleTracking;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.model.GroupTracking;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class GroupDBHelper {
    public Realm realm;
    public GroupDBHelper(Realm realm) {
        this.realm = realm;
    }

    public static void createGroupList(int cve_vehicle_group, String userGroup, String vehicle_group, String desc_vehicle_group, boolean selected, int positionItem, RealmList<VehicleTracking> vehicles){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        GroupTracking groupTracking = new GroupTracking(cve_vehicle_group,userGroup,vehicle_group,desc_vehicle_group,selected,positionItem,vehicles);
        List<GroupTracking> vehicleTrackingList = new ArrayList<>();
        vehicleTrackingList.add(groupTracking);
        realm.copyToRealm(vehicleTrackingList);
        realm.commitTransaction();

    }

    public static List<GroupTracking> readGroupList(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<GroupTracking> realmresults = realm.where(GroupTracking.class).findAll();
        ArrayList<GroupTracking> songArrayList = new ArrayList<>();
        for (GroupTracking s: realmresults){
            songArrayList.add(s);
        }
        return songArrayList;
    }

    public static void updateCheckedStatus(int id, boolean isChecked){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        VehicleTracking vehicleTracking = realm.where(VehicleTracking.class).equalTo("id", id).findFirst();
        vehicleTracking.setSelected(isChecked);
        realm.commitTransaction();

    }
}
