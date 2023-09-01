package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.TripsByTimeV3.Datapos;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.dataVehicleDescV3;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;

import java.util.List;

public interface unitViewpresenterV3 {
    void setDataetVehicleDescripcion(int vehicleCve);
    void setDataVehicleDescripcion(dataVehicleDescV3 data);


    void getEvents(int vehicleCve, String currentDate, Context context);
    void setEvents(List<TripV2> trips);

    void showDialog();
    void hideDialog();
    void showProgressBar();
    void hideProgressBar();

    void AsyncTaskOne(int vehicleCve, String s, String s1, Context context);

    void getTripsByTime(int vehicleCve, String startTime, String endTime, Context context);

    //region setdata TripsbyTime
    void setcalles(List<String> calles1);
    void setdatafromlistDayLats(List<String> latitudeList);
    void setdatafromlistDayLongs(List<String> longitudeList);
    //endregion
    //region HD
    void getexternalAPI(List<List<Double>> hDdoublelist);
    void tripsBDx2tripsBDy2(List<String> xdots, List<String> ydots);
    void drawHDdots(List<List<Float>> resumeDots);

    void setDataTripsByTimeV3(Datapos data);
    //endregion
}
