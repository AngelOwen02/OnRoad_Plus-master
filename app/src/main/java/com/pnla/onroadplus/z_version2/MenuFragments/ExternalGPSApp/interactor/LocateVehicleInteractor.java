package com.pnla.onroadplus.z_version2.MenuFragments.ExternalGPSApp.interactor;

import androidx.fragment.app.FragmentActivity;

public interface LocateVehicleInteractor {
    void validateAppsInstalled(FragmentActivity activity);

    void validateLocationPermission(FragmentActivity activity);
}
