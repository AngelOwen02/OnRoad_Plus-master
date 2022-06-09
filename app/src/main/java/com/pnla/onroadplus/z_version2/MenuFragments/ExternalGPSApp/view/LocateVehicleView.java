package com.pnla.onroadplus.z_version2.MenuFragments.ExternalGPSApp.view;

public interface LocateVehicleView {
    void showLoader();
    void hideLoader();
    void showMessage(String message);
    void successLocationPermission();
    void appsInstalled(boolean mapsIsInstalled, boolean wazeIsInstaled);
    void requestLocationPermission();
    void setLocation(double latitude, double longitude);
}
