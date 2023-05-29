package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;

import java.util.List;

public interface unitViewpresenterV3 {
    void getDataetVehicleDescripcion();
    void setDataetVehicleDescripcion();

    void getEvents(int vehicleCve, String currentDate, Context context);
    void setEvents(List<TripV2> trips);

    void showDialog();
    void hideDialog();

    void AsyncTaskOne(int vehicleCve, String s, String s1, Context context);

    void getTripsByTime(int vehicleCve, String s, String s1, Context context);
}
