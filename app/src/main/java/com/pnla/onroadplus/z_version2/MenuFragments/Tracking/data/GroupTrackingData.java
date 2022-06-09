package com.pnla.onroadplus.z_version2.MenuFragments.Tracking.data;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.model.GroupTracking;

import java.util.List;

import io.realm.RealmList;

public class GroupTrackingData {

    private List<GroupTracking> vehicleGroups;

    public GroupTrackingData(List<GroupTracking> groups) {
        super();
        this.vehicleGroups = groups;
    }

    public List<GroupTracking> getGroups() {
        return vehicleGroups;
    }

    public void setGroups(RealmList<GroupTracking> vehicleGroups) {
        this.vehicleGroups = vehicleGroups;
    }
}
