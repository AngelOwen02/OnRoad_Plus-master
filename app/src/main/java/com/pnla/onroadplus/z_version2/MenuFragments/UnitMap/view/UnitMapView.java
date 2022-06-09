package com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.view;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.TripsByDay;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.datesList.DateV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.vehicleDescription.VehicleDescriptionData;

import java.text.ParseException;
import java.util.List;

public interface UnitMapView {

    void setMarkerOptions(MarkerOptions markerOptions);

    void setZoomOptions(LatLng latLng, float zoom);

    void setCurrentDate(String currentDate);

    void fillTripsToView(List<TripV2> trips);

    void fillTripsbydayToView(List<TripsByDay> daytriips);
    void fillStringTipsbyDaylat(List<String> TbDx);
    void fillStringTipsbyDaylong(List<String> TbDy);
    void fillStringcalles(List<String> calles);
    void fillDatesList(List<DateV2> dates);

    void fillVehicleDescription(VehicleDescriptionData data) throws ParseException;

    void showRouteWithExternalApps(double lat, double lng);

    void showCommandScreen();

    void showProgressDialog();
 void stopallhandlers();
    void hideProgressDialog();

    void setErrorMessage(String message);


    void drawResumeDots(List<List<Float>> resumeDots);

    void drawtripdbxbdy(List<String> xdots, List<String> ydots);
}
