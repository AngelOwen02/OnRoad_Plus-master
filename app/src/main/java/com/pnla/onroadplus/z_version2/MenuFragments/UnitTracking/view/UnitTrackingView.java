package com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.view;

import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.Group;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;

import java.util.List;

public interface UnitTrackingView {

    void showProgressBar();
    void vehiclesinsiderequest();
    void hideProgressBar();

    void showErrorMessage(String message);

    void fillVehiclesList(List<Unit> vehicles);

    void fillGroupsList(List<Group> groups);

    void hideUnitRV();

    void showUnitRV();

    void hideGroupRV();

    void showGroupRV();

    void hideEmptyGroupsImage();

    void showEmptyGroupsImage();
}
