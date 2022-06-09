package com.pnla.onroadplus.z_version2.MenuFragments.Tracking.interactor;

public interface TrackingInteractor {
    void getAllVehiclesFromAPI();
    void vehicleMarkerSetup(double lat, double lng, String title, String image, int vehicleSwitch);
    void zoomToVehicle(double lat, double lng);

}
