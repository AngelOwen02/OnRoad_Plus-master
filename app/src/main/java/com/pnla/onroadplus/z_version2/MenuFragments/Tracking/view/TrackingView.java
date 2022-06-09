package com.pnla.onroadplus.z_version2.MenuFragments.Tracking.view;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public interface TrackingView {

    void setMarkerOptions(MarkerOptions markerOptions);
    void setZoomOptions(LatLng latLng, float zoom);
    void hideProgressBar();
    void showProgressBar();

}
