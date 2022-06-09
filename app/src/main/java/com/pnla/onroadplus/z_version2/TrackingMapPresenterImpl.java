package com.pnla.onroadplus.z_version2;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.model.ClusterTracking;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;

import java.util.List;

public class TrackingMapPresenterImpl implements TrackingMapPresenter {

    private TrackingMapView view;
    private TrackingMapInteractor interactor;

    public TrackingMapPresenterImpl(Context context) {
        interactor = new TrackingMapInteractorImpl(this, context);
    }

    @Override
    public void setView(TrackingMapView view) {
        this.view = view;
    }

    @Override
    public void getMarkers() {
        showProgressDialog();
        interactor.getMarkers();
    }

    @Override
    public void getFullVehicles() {
        if (view != null){
            interactor.getAllVehiclesFromAPI();
        }
    }

    @Override
    public void eraseeverything() {
        if (view != null) {
            interactor.eraseeverything();
        }
    }

    @Override
    public void hideshowgeoCercasRequest() {
        if (view != null) {
            interactor.hidegeoCercas();
        }
    }


    @Override
    public void putMarkersInMap(ClusterTracking clusterTracking) {
        hideProgressDialog();
        view.addMarkersToMap(clusterTracking);
    }

    @Override
    public void setVehiclesListToView(List<Unit> unitList) {
        if (view != null) {
            view.setUnitList(unitList);
        }
    }

    @Override
    public void setMessageToView(String message) {
        view.showErrorMessage(message);
    }

    @Override
    public void showProgressDialog() {
        view.showProgressDialog();
    }

    @Override
    public void hideProgressDialog() {
        view.hideProgressDialog();
    }

    @Override
    public void hideShowIconGeoCercas(boolean accessFlag) {
        if (view != null) {
            view.hideShowiconGeoCercas(accessFlag);
        }
    }
}