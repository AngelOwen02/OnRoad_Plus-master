package com.pnla.onroadplus.z_version2.fragments.notifications_v2.interfaces;

import com.pnla.onroadplus.z_version2.fragments.notifications_v2.models.NotificationV2OnRoad;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;

import java.util.List;

public interface NotificationsV2View {
    void showLoader();
    void hideLoader();
    void showMessage(String message);
    void sessionExpired(String message);
    void showCurrentDate(String currentDate);
    void fillVehiclesList(List<VehicleV2> vehicles);
    void fillAllNotifications(List<NotificationV2OnRoad> notifications);
}
