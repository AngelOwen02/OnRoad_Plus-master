package com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.mapTracking.trackingPresenter;

import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.mapTracking.trackingView.TrackingViewImplements;

import java.util.List;

public interface trackingPresenter {
    void setView(TrackingViewImplements view);

    void getFullVehicles();

    void setVehiclesListToView(List<String> unitList);
    /*  void setGrupsToView(List<String> gruoplist);
     ;*/
    void failureResponse(String message);
    void showProgressDialog();
    void hideProgressDialog();

}
