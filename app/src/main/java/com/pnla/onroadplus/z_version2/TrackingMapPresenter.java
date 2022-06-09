package com.pnla.onroadplus.z_version2;

import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.model.ClusterTracking;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;

import java.util.List;

public interface TrackingMapPresenter {
    void setView(TrackingMapView view);
    void getMarkers();
    void getFullVehicles();
    void eraseeverything();
    void hideshowgeoCercasRequest();
    void putMarkersInMap(ClusterTracking clusterTracking);
    void setVehiclesListToView(List<Unit> unitList);
    void setMessageToView(String message);
    void showProgressDialog();
    void hideProgressDialog();

    void hideShowIconGeoCercas(boolean accessFlag);
}