package com.pnla.onroadplus.z_version2.MenuFragments.Tracking.presenter;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.view.TrackingView;

public interface TrackingPresenter {

    void getFullVehicles();

    void vehicleMarkerSetup(MarkerOptions markerOptions);
    void zoomVehicleSetup(LatLng latLng, float zoom);
    void zoomVehicleInMap(double lat, double lng);

    void setView(TrackingView view);


    void hideProgressBar();
    void showProgressBar();

}
