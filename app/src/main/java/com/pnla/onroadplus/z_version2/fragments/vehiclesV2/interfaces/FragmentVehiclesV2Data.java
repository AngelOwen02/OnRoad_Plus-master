package com.pnla.onroadplus.z_version2.fragments.vehiclesV2.interfaces;

import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;

import java.util.List;

public interface FragmentVehiclesV2Data {
    void setVehiclesTitleToolbar(String vehiclesTitle);

    void setVehiclesListToActivity(List<VehicleV2> vehicles);

    void setNewStateToVehicleList(int position, boolean newState);

    void setGroupsListToActivity(List<GroupV2> groups);

    void setNewStateToGroupsList(int position, boolean newState);

    void setVehiclesListState(boolean isVisible);
}
