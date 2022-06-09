package com.pnla.onroadplus.z_version2.fragments.notifications_v2.interfaces;

import android.content.Context;

import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;

import java.util.List;

public interface NotificationsV2Interactor {
    void getMainDate();

    void getAllVehicles(Context context);

    void getAllNotification(List<VehicleV2> vehicles, String date, Context context);

    void cancelRequest();

    void rechargeNotifications(List<VehicleV2> vehicles, String date, Context context);
}
