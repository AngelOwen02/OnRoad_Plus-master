package com.pnla.onroadplus.z_version2.fragments.mapV2.interfaces;


import androidx.fragment.app.Fragment;

import com.pnla.onroadplus.z_version2.fragments.mapV2.models.VehicleV2Map;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.datesList.DateV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.markersV2.VehicleBitmapDescriptorV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.vehicleDescription.VehicleDescriptionData;

import java.util.List;

public interface MapV2View {

    void showLoader();

    void hideLoader();

    void showMessage(String message);

    void sessionExpired(String message);

    void showFragmentVehicles(Fragment fragment);

    void fillDatesList(List<DateV2> dates);

    void fillVehiclesList(List<VehicleV2Map> vehicles, boolean isFirstRequest);

    void fillVehicleDescription(VehicleDescriptionData data);

    void fillTripsToView(List<TripV2> trips);

    void setVehicleBitmapDescriptorToView(List<VehicleBitmapDescriptorV2> vehiclesImages);

    void updateVehiclesMarkers(List<VehicleV2Map> vehicles);

    void showZoomByVehicleSelected(double latitude,double longitude);

}
