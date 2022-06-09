package com.pnla.onroadplus.z_version2.fragments.mapV2.interfaces;

import android.content.Context;
import android.os.Bundle;

import com.pnla.onroadplus.z_version2.fragments.mapV2.models.VehicleV2Map;

import java.util.List;

public interface MapV2Interactor {

    void getDates();

    void getVehiclesFromBundleOrRealm(Bundle bundle, Context context);

    void getVehicleDescription(int vehicleCve, Context context);

    void getTripsByDate(int cveVehicle, String date, Context context);

    void getVehiclesMarkers(List<VehicleV2Map> vehicles);

    void getVehiclesToUpdate(List<VehicleV2Map> vehicles, Context context);

    void cancelRequest();

    void getLocationUpdatedByVehicleSeledted(VehicleV2Map vehicleSelected, List<VehicleV2Map> vehicles);
}
