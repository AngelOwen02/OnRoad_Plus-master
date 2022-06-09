package com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.presenter;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.TripsByDay;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.datesList.DateV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.vehicleDescription.VehicleDescriptionData;

import java.util.List;

public interface UnitMapPresenter {


    void putVehicleMarkerInMap(double lat, double lng, String title, String image, int vehicleSwitch);

    void vehicleMarkerSetup(MarkerOptions markerOptions);

    void zoomVehicleInMap(double lat, double lng);

    void zoomVehicleSetup(LatLng latLng, float zoom);

    void showProgressDialog();

    void hideProgressDialog();

    void getTripsByDate(int cveVehicle, String date, Context context);
    void getTripsByDay(int vehicleCve,String sendime , Context context);//
    void getTripsByTime(int vehicleCve,String sendime ,String sendime1, Context context);//
    void getVehicleDescription(int vehicleCve, Context context);

    void getCurrentDateTrip();


    void setErrorMessage(String message);
    void stophandlers();
    void setDatesToView(List<DateV2> dates);

    void setTripsToView(List<TripV2> trips);
    void setTripsBydayToView(List<TripsByDay> tripsbyday);
    void setCurrentDate(String currentDate);

    void setdatafromlistDayLats(List<String> datax);
    void setdatafromlistDayLongs(List<String> datay);
    void setcalles(List<String> calles);
    void setVehicleDescriptionToView(VehicleDescriptionData data);


    void getDates();
    void getexternalApi(List<List<Double>> correctedDots);


    void drawHDdots(List<List<Float>> resumeDots);

    void tripsBDx2tripsBDy2(List<String> xdots, List<String> ydots);
}
