package com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2;

import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class GroupV2 extends RealmObject {

    @PrimaryKey
    private int cve_vehicle_group;
    private String userGroup;
    private String vehicle_group;
    private String desc_vehicle_group;
    private boolean selected;
    private int positionItem;
    private RealmList<VehicleV2> vehicles;

    public GroupV2() {
    }

    public GroupV2(int cve_vehicle_group, String userGroup, String vehicle_group, String desc_vehicle_group, boolean selected, int positionItem, RealmList<VehicleV2> vehicles) {
        this.cve_vehicle_group = cve_vehicle_group;
        this.userGroup = userGroup;
        this.vehicle_group = vehicle_group;
        this.desc_vehicle_group = desc_vehicle_group;
        this.selected = selected;
        this.positionItem = positionItem;
        this.vehicles = vehicles;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getCve_vehicle_group() {
        return cve_vehicle_group;
    }

    public void setCve_vehicle_group(int cve_vehicle_group) {
        this.cve_vehicle_group = cve_vehicle_group;
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

    public List<VehicleV2> getVehicles() {
        return vehicles;
    }

    public void setVehicles(RealmList<VehicleV2> vehicles) {
        this.vehicles = vehicles;
    }

    public int getPositionItem() {
        return positionItem;
    }

    public void setPositionItem(int positionItem) {
        this.positionItem = positionItem;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }
}
