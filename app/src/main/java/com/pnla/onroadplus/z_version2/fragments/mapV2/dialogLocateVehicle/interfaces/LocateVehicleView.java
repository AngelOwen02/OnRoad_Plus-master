package com.pnla.onroadplus.z_version2.fragments.mapV2.dialogLocateVehicle.interfaces;

public interface LocateVehicleView {
    void showLoader();
    void hideLoader();
    void showMessage(String message);
    void successLocationPermission();
    void appsInstalled(boolean mapsIsInstalled,boolean wazeIsInstaled);
    void requestLocationPermission();
    void setLocation(double latitude,double longitude);
}
