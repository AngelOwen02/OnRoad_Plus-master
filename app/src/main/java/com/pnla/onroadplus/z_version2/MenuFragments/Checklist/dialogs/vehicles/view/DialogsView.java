package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.view;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.model.*;

import java.util.List;

public interface DialogsView {
    void setVehicles(List<DialogsData> data);

    void showDialog();
    void hideProgressDialog();
}
