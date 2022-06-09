package com.pnla.onroadplus.z_version2.fragments.mapV2.dialogLocateVehicle.interfaces;

import androidx.fragment.app.FragmentActivity;

public interface LocateVehicleInteractor {
    void validateAppsInstalled(FragmentActivity activity);

    void validateLocationPermission(FragmentActivity activity);
}
