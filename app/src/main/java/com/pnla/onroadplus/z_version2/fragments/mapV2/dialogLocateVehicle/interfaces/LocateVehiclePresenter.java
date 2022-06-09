package com.pnla.onroadplus.z_version2.fragments.mapV2.dialogLocateVehicle.interfaces;

import androidx.fragment.app.FragmentActivity;

public interface LocateVehiclePresenter {

    void setView(LocateVehicleView view);

    /**RequestDataToInteractor*/
    void validateAppsInstalled(FragmentActivity activity);
    void validateLocationPermission(FragmentActivity activity);


    /**SetDataToView*/
    void showLoaderFromInteractor();
    void hideLoaderFromInteractor();
    void setMessageToView(String message);
    void setAppsInstalledToView(boolean mapsIsInstalled,boolean wazeIsInstaled);
    void requestLocationPermission();
    void setLocationToView(double latitude,double longitude);

}
