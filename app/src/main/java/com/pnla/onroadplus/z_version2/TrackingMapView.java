package com.pnla.onroadplus.z_version2;

import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.model.ClusterTracking;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;

import java.util.List;

public interface TrackingMapView {

    void addMarkersToMap(ClusterTracking clusterTracking);
    void setUnitList(List<Unit> vehicles);
    void showErrorMessage(String message);

    void showProgressDialog();

    void hideProgressDialog();

    void hideShowiconGeoCercas(boolean accessFlag);
}
