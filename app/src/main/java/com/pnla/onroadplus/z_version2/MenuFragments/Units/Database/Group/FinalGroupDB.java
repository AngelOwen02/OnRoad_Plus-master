package com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Group;

import android.util.Log;

import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.Group;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.UnitGroup;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class FinalGroupDB {

    public Realm realm;

    public FinalGroupDB(Realm realm) {
        this.realm = realm;
    }

    public static void createNewGroup(int cve_vehicle_group, String userGroup, String vehicle_group, String desc_vehicle_group, boolean selected, int positionItem, RealmList<UnitGroup> vehicles){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Group group = new Group(cve_vehicle_group, userGroup, vehicle_group, desc_vehicle_group, selected, positionItem, vehicles);
        List<Group> unitList = new ArrayList<>();
        unitList.add(group);
        realm.copyToRealm(unitList);
        realm.commitTransaction();
    }

    public static List<Group> getGroupList(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Group> groupRealmResults = realm.where(Group.class).findAll();
        List<Group> groupList = new ArrayList<>();
        for (Group group: groupRealmResults){
            groupList.add(group);
        }
        return groupList;
    }

    public static void updateCheckedStatus(int id, boolean isChecked){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Group group = realm.where(Group.class).equalTo("id", id).findFirst();
        group.setSelected(isChecked);
        realm.commitTransaction();
    }

    public static void updateGroups(int id,int cve_vehicle_group, String userGroup, String vehicle_group, String desc_vehicle_group, boolean selected, int positionItem, RealmList<UnitGroup> vehicles){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Group group = realm.where(Group.class).equalTo("id", id).findFirst();
        ArrayList<Group> groupList = new ArrayList<>();
        groupList.add(group);
        for (Group groups : groupList){
            groups.setCve_vehicle_group(cve_vehicle_group);
            groups.setUserGroup(userGroup);
            groups.setVehicle_group(vehicle_group);
            groups.setDesc_vehicle_group(desc_vehicle_group);
            groups.setSelected(selected);
            groups.setPositionItem(positionItem);
            groups.setVehicles(vehicles);
            Log.e("setvehicles",String.valueOf(vehicles));
        }
        realm.commitTransaction();
    }

    public List<Unit> getModelList(){
        List<Unit> list = new ArrayList<>();
        Realm realm;
        realm = Realm.getDefaultInstance();
        RealmResults<Unit> results = realm
                .where(Unit.class)
                .findAll();
        list.addAll(realm.copyFromRealm(results));
        return list;
    }
}
