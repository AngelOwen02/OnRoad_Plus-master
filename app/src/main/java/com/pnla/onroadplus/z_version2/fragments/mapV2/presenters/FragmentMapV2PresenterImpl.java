package com.pnla.onroadplus.z_version2.fragments.mapV2.presenters;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.pnla.onroadplus.z_version2.fragments.mapV2.interactors.FragmentMapV2InteractorImpl;
import com.pnla.onroadplus.z_version2.fragments.mapV2.interfaces.MapV2Interactor;
import com.pnla.onroadplus.z_version2.fragments.mapV2.interfaces.MapV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.mapV2.interfaces.MapV2View;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.VehicleV2Map;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.datesList.DateV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.markersV2.VehicleBitmapDescriptorV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.vehicleDescription.VehicleDescriptionData;

import java.util.List;

public class FragmentMapV2PresenterImpl implements MapV2Presenter {

    private MapV2View view;
    private MapV2Interactor interactor;

    public FragmentMapV2PresenterImpl() {
        interactor = new FragmentMapV2InteractorImpl(this);
    }

    @Override
    public void setView(MapV2View view) {
        this.view = view;
    }

    @Override
    public void getDates() {
        if (view != null) {
            interactor.getDates();
        }
    }

    @Override
    public void getVehiclesFromBundleOrRealm(Bundle bundle, Context context) {
        if (view != null) {
            interactor.getVehiclesFromBundleOrRealm(bundle, context);
        }
    }

    @Override
    public void getVehicleDescription(int vehicleCve, Context context) {
        if (view != null) {
            view.showLoader();
            interactor.getVehicleDescription(vehicleCve, context);
        }
    }

    @Override
    public void getTripsByDate(int cveVehicle, String date, Context context) {
        if (view != null) {
            view.showLoader();
            interactor.getTripsByDate(cveVehicle, date, context);
        }
    }

    @Override
    public void getVehiclesMarkers(List<VehicleV2Map> vehicles) {
        if (view != null) {
            view.showLoader();
            interactor.getVehiclesMarkers(vehicles);
        }
    }

    @Override
    public void getVehiclesToUpdate(List<VehicleV2Map> vehicles, Context context) {
        if (view != null) {
            interactor.getVehiclesToUpdate(vehicles, context);
        }
    }

    @Override
    public void cancelRequest() {
        if (view != null) {
            interactor.cancelRequest();
        }
    }

    @Override
    public void getLocationUpdatedByVehicleSeledted(VehicleV2Map vehicleSelected, List<VehicleV2Map> vehicles) {
        if (view != null) {
            interactor.getLocationUpdatedByVehicleSeledted(vehicleSelected, vehicles);
        }
    }

    @Override
    public void showLoaderFromInteractor() {
        if (view != null) {
            view.showLoader();
        }
    }

    @Override
    public void hideLoaderFromInteractor() {
        if (view != null) {
            view.hideLoader();
        }
    }

    @Override
    public void setDatesToView(List<DateV2> dates) {
        if (view != null) {
            view.fillDatesList(dates);
        }
    }

    @Override
    public void setMessageToView(String message) {
        if (view != null) {
            view.hideLoader();
            view.showMessage(message);
        }
    }

    @Override
    public void sessionExpired(String message) {
        if (view != null) {
            view.hideLoader();
            view.sessionExpired(message);
        }
    }

    @Override
    public void setVehiclesListToView(List<VehicleV2Map> vehicles, boolean isFirstRequest) {
        if (view != null) {
            view.hideLoader();
            view.fillVehiclesList(vehicles, isFirstRequest);
        }
    }

    @Override
    public void setFragmentVehiclesToView(Fragment fragment) {
        if (view != null) {
            view.showFragmentVehicles(fragment);
        }
    }

    @Override
    public void setVehicleDescriptionToView(VehicleDescriptionData data) {
        if (view != null) {
            view.hideLoader();
            view.fillVehicleDescription(data);
        }

    }

    @Override
    public void setTripsToView(List<TripV2> trips) {
        if (view != null) {
            view.hideLoader();
            view.fillTripsToView(trips);
        }
    }

    @Override
    public void setVehicleBitmapDescriptorToView(List<VehicleBitmapDescriptorV2> vehiclesImages) {
        if (view != null) {
            view.setVehicleBitmapDescriptorToView(vehiclesImages);
        }
    }

    @Override
    public void updateVehiclesMarkers(List<VehicleV2Map> vehicleList) {
        if (view != null) {
            view.updateVehiclesMarkers(vehicleList);
        }
    }

    @Override
    public void setLocationUpdatedByVehicleSelected(double latitude, double longitude) {
        if (view != null) {
            view.showZoomByVehicleSelected(latitude, longitude);
        }
    }

}
