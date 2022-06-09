package com.pnla.onroadplus.z_version2.MenuFragments.Notifications.view;

import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.NotificationsOnroad;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.Vehicles;

import java.util.List;

public interface NotificationsView {
    void showLoader();
    void hideLoader();
    void showMessage(String message);
    void sessionExpired(String message);
    void showCurrentDate(String currentDate);
    void fillVehiclesList(List<Vehicles> vehicles);
    void fillAllNotifications(List<NotificationsOnroad> notifications);
}
