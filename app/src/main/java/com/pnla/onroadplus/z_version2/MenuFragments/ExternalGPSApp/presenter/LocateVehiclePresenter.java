package com.pnla.onroadplus.z_version2.MenuFragments.ExternalGPSApp.presenter;

import androidx.fragment.app.FragmentActivity;

import com.pnla.onroadplus.z_version2.MenuFragments.ExternalGPSApp.view.LocateVehicleView;

public interface LocateVehiclePresenter {

    void setView(LocateVehicleView view);

    /**RequestDataToInteractor*/
    void validateAppsInstalled(FragmentActivity activity);
    void validateLocationPermission(FragmentActivity activity);


    /**SetDataToView*/
    void showLoaderFromInteractor();
    void hideLoaderFromInteractor();
    void setMessageToView(String message);
    void setAppsInstalledToView(boolean mapsIsInstalled, boolean wazeIsInstaled);
    void requestLocationPermission();
    void setLocationToView(double latitude, double longitude);

}
