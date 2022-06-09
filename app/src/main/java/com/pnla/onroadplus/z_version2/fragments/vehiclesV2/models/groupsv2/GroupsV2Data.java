package com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2;

import java.util.List;

import io.realm.RealmList;

public class GroupsV2Data {

    private RealmList<GroupV2> vehicleGroups = null;

    /**
     * @param groups
     */
    public GroupsV2Data(List<GroupV2> groups) {
        super();
        this.vehicleGroups = vehicleGroups;
    }

    public RealmList<GroupV2> getGroups() {
        return vehicleGroups;
    }

    public void setGroups(RealmList<GroupV2> vehicleGroups) {
        this.vehicleGroups = vehicleGroups;
    }

}
