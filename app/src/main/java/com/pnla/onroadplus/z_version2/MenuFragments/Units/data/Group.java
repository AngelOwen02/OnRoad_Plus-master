package com.pnla.onroadplus.z_version2.MenuFragments.Units.data;

import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.UnitGroup;
import com.pnla.onroadplus.z_version2.realmOnRoad.BaseRealmApplication;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Group extends RealmObject {

    @PrimaryKey
    private int id;
    private int cve_vehicle_group;
    private String userGroup;
    private String vehicle_group;
    private String desc_vehicle_group;
    private boolean selected;
    private int positionItem;
    private RealmList<UnitGroup> vehicles;

    public Group(){}

    public Group(int cve_vehicle_group, String userGroup, String vehicle_group, String desc_vehicle_group, boolean selected, int positionItem, RealmList<UnitGroup> vehicles) {
        this.id = BaseRealmApplication.GROUP_ID.incrementAndGet();
        this.cve_vehicle_group = cve_vehicle_group;
        this.userGroup = userGroup;
        this.vehicle_group = vehicle_group;
        this.desc_vehicle_group = desc_vehicle_group;
        this.selected = selected;
        this.positionItem = positionItem;
        this.vehicles = vehicles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCve_vehicle_group() {
        return cve_vehicle_group;
    }

    public void setCve_vehicle_group(int cve_vehicle_group) {
        this.cve_vehicle_group = cve_vehicle_group;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getVehicle_group() {
        return vehicle_group;
    }

    public void setVehicle_group(String vehicle_group) {
        this.vehicle_group = vehicle_group;
    }

    public String getDesc_vehicle_group() {
        return desc_vehicle_group;
    }

    public void setDesc_vehicle_group(String desc_vehicle_group) {
        this.desc_vehicle_group = desc_vehicle_group;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getPositionItem() {
        return positionItem;
    }

    public void setPositionItem(int positionItem) {
        this.positionItem = positionItem;
    }

    public RealmList<UnitGroup> getVehicles() {
        return vehicles;
    }

    public void setVehicles(RealmList<UnitGroup> vehicles) {
        this.vehicles = vehicles;
    }
}
