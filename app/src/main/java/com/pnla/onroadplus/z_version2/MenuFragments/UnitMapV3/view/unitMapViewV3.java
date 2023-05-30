package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.view;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.dataVehicleDescV3;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;

import java.util.List;

public interface unitMapViewV3 {
    void setEvents(List<TripV2> trips);
    void showProgressDialog();
    void hideProgressDialog();
    void showProgressBar();
    void hideProgressBar();
    void settripsByDay();

    void VehicleDescriptionSucess(dataVehicleDescV3 data);
}
