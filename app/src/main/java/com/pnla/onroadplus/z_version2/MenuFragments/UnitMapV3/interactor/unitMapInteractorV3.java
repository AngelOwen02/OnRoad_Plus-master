package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.interactor;

import android.content.Context;

public interface unitMapInteractorV3 {
    void  getVehicleDescription(int vehicleCve);

    void reqEvents(int vehicleCve, String currentDate, Context context);

    void AsyncTaskOne(int vehicleCve, String sendime, String sendime1, Context context);

    void getTripsbyTime(int vehicleCve, String sendime, String sendime1, Context context);
}
