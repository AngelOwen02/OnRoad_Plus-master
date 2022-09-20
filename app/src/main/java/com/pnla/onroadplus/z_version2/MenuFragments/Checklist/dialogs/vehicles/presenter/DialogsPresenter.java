package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.presenter;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.model.*;

import java.util.List;

public interface DialogsPresenter {
    void requestVehicles();
    void setVehicles(List<DialogsData> data);

    void showDialog();
    void hideDialog();
}
