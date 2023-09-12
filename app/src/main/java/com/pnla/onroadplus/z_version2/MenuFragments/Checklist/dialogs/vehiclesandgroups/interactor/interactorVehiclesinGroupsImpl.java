package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.interactor;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.presenter.presenterVehicleInGroups;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.presenter.presenterVehicleInGroupsImpl;

public class interactorVehiclesinGroupsImpl implements  interactorVehicleinGroups {
    private presenterVehicleInGroupsImpl presenter;
    private Context context;
    public interactorVehiclesinGroupsImpl(presenterVehicleInGroupsImpl presenter, Context context) {
        this.context=context;
        this.presenter=presenter;
    }

    @Override
    public void getGroups() {

    }
}
