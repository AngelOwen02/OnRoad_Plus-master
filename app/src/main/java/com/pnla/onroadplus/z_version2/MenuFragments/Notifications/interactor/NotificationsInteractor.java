package com.pnla.onroadplus.z_version2.MenuFragments.Notifications.interactor;
import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.Vehicles;

import java.util.List;
public interface NotificationsInteractor {
    void getMainDate();

    void getAllVehicles(Context context);

    void getAllNotification(List<Vehicles> vehicles, String date, Context context);

    void cancelRequest();

    void rechargeNotifications(List<Vehicles> vehicles, String date, Context context);
}
