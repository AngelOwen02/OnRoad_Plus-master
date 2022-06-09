package com.pnla.onroadplus.z_version2.fragments.vehiclesV2.interfaces;

import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;

import java.util.List;

public interface VehiclesV2View {
    void showLoader();

    void hideLoader();

    void showMessage(String message);

    void sessionExpired(String message);

    void fillVehiclesList(List<VehicleV2> vehicles);

    void fillGroupsList(List<GroupV2> groups);
}
