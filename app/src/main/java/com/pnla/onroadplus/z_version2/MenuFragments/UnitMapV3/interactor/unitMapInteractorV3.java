package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.interactor;

import android.content.Context;

import java.util.List;

public interface unitMapInteractorV3 {
    void  getVehicleDescription(int vehicleCve);

    void reqEvents(int vehicleCve, String currentDate, Context context);

    void AsyncTaskOne(int vehicleCve, String timeStart, String timeEnd, Context context);

    void getTripsbyTime(int vehicleCve, String timeStart, String timeEnd, Context context);
    void dataExteralAPI(List<List<Double>> correctedDots);
}
