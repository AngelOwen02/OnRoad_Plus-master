package com.pnla.onroadplus.z_version2.fragments.mapV2.interfaces;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.pnla.onroadplus.z_version2.fragments.mapV2.models.VehicleV2Map;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.datesList.DateV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.markersV2.VehicleBitmapDescriptorV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.vehicleDescription.VehicleDescriptionData;

import java.util.List;

public interface MapV2Presenter {

    void setView(MapV2View view);

    //requestDataToInteractor
    void getDates();

    void getVehiclesFromBundleOrRealm(Bundle bundle, Context context);

    void getVehicleDescription(int vehicleCve, Context context);

    void getTripsByDate(int cveVehicle, String date, Context context);

    void getVehiclesMarkers(List<VehicleV2Map> vehicles);

    void getVehiclesToUpdate(List<VehicleV2Map> vehicles, Context context);

    void cancelRequest();

    void getLocationUpdatedByVehicleSeledted(VehicleV2Map vehicleSelected, List<VehicleV2Map> vehicles);

    //setDataToView

    void showLoaderFromInteractor();

    void hideLoaderFromInteractor();

    void setDatesToView(List<DateV2> dates);

    void setMessageToView(String message);

    void sessionExpired(String message);

    void setVehiclesListToView(List<VehicleV2Map> vehicles, boolean isFirstRequest);

    void setFragmentVehiclesToView(Fragment fragment);

    void setVehicleDescriptionToView(VehicleDescriptionData data);

    void setTripsToView(List<TripV2> trips);

    void setVehicleBitmapDescriptorToView(List<VehicleBitmapDescriptorV2> vehiclesImages);

    void updateVehiclesMarkers(List<VehicleV2Map> vehicleList);

    void setLocationUpdatedByVehicleSelected(double latitude,double longitude);

}
