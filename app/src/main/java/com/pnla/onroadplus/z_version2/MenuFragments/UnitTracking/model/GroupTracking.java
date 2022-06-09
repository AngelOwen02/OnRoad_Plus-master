package com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.model;

import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.model.VehicleTracking;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class GroupTracking extends RealmObject {

    @PrimaryKey
    private int cve_vehicle_group;
    private String userGroup;
    private String vehicle_group;
    private String desc_vehicle_group;
    private boolean selected;
    private int positionItem;
    private RealmList<VehicleTracking> vehicles;

    public  GroupTracking(){}

    public GroupTracking(int cve_vehicle_group, String userGroup, String vehicle_group, String desc_vehicle_group, boolean selected, int positionItem, RealmList<VehicleTracking> vehicles) {
        this.cve_vehicle_group = cve_vehicle_group;
        this.userGroup = userGroup;
        this.vehicle_group = vehicle_group;
        this.desc_vehicle_group = desc_vehicle_group;
        this.selected = selected;
        this.positionItem = positionItem;
        this.vehicles = vehicles;
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

    public RealmList<VehicleTracking> getVehicles() {
        return vehicles;
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



    public void setVehicles(RealmList<VehicleTracking> vehicles) {
        this.vehicles = vehicles;
    }
}
