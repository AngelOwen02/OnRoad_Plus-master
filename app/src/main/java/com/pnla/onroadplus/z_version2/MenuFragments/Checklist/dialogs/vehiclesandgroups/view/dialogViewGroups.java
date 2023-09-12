package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.view;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.model.DataGroups;

public interface dialogViewGroups {
    void setVehiclesGroups(DataGroups data);
    void showDialog();
    void hideProgressDialog();

    void closeDialog();
}
