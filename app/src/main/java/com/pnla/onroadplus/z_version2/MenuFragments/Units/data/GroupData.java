package com.pnla.onroadplus.z_version2.MenuFragments.Units.data;

import java.util.List;

import io.realm.RealmList;

public class GroupData {

    private List<Group> vehicleGroups = null;

    public GroupData(RealmList<Group> vehicleGroups) {
        super();
        this.vehicleGroups = vehicleGroups;
    }

    public List<Group> getVehicleGroups() {
        return vehicleGroups;
    }

    public void setVehicleGroups(RealmList<Group> vehicleGroups) {
        this.vehicleGroups = vehicleGroups;
    }
}
