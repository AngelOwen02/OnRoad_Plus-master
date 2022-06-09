package com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.interactor;

import android.content.Context;

import java.util.List;

public interface UnitMapInteractor {

    void vehicleMarkerSetup(double lat, double lng, String title,String image, int vehicleSwitch);
    void zoomToVehicle(double lat, double lng);
    void getDates();
    void getTripsByDate(int cveVehicle, String date, Context context);
    void getVehicleDescription(int vehicleCve, Context context);

    void getCurrentDate();

    void getTripbyDay(int vehicleCve,String sendime , Context context);

    void getTripsbyTime(int vehicleCve,String startTimer,String endtime,Context context);
    void dataExteralAPI(List<List<Double>> correctedDots);

}
