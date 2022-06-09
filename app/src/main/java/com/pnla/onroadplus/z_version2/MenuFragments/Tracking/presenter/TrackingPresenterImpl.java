package com.pnla.onroadplus.z_version2.MenuFragments.Tracking.presenter;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.interactor.TrackingInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.interactor.TrackingInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.view.TrackingView;

public class TrackingPresenterImpl implements TrackingPresenter {

    private Context context;
    private TrackingView view;
    private TrackingInteractor interactor;

    public TrackingPresenterImpl(Context context) {
        this.context = context;
        interactor = new TrackingInteractorImpl(this, context);
    }

    @Override
    public void getFullVehicles() {
        if (view != null){
            interactor.getAllVehiclesFromAPI();
        }
    }

    @Override
    public void vehicleMarkerSetup(MarkerOptions markerOptions) {
        if (view != null){
            view.setMarkerOptions(markerOptions);
        }
    }

    @Override
    public void zoomVehicleInMap(double lat, double lng) {
        if (view != null){
            interactor.zoomToVehicle(lat, lng);
        }
    }

    @Override
    public void zoomVehicleSetup(LatLng latLng, float zoom) {
        if (view != null){
            view.setZoomOptions(latLng, zoom);
        }
    }

    @Override
    public void setView(TrackingView view) {
        if (view != null){
            this.view = view;
        }
    }

    @Override
    public void hideProgressBar() {
        if (view != null){
            view.hideProgressBar();
        }
    }

    @Override
    public void showProgressBar() {
        if (view != null){
            view.showProgressBar();
        }
    }
}
