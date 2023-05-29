package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.presenter;

<<<<<<< HEAD
import android.content.Context;

import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;
=======
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.dataVehicleDescV3;
>>>>>>> 2881a57b5c1602b01222a7427cac33a6b81b399f

import java.util.List;

public interface unitViewpresenterV3 {
    void getDataetVehicleDescripcion();
<<<<<<< HEAD
    void setDataetVehicleDescripcion();

    void getEvents(int vehicleCve, String currentDate, Context context);
    void setEvents(List<TripV2> trips);

    void showDialog();
    void hideDialog();

    void AsyncTaskOne(int vehicleCve, String s, String s1, Context context);

    void getTripsByTime(int vehicleCve, String s, String s1, Context context);
=======
    void setDataetVehicleDescripcion(int vehicleCve);
    void setDataVehicleDescripcion(dataVehicleDescV3 data);
>>>>>>> 2881a57b5c1602b01222a7427cac33a6b81b399f
}
