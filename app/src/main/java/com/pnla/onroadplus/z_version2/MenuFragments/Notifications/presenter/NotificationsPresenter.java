package com.pnla.onroadplus.z_version2.MenuFragments.Notifications.presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.NotificationsOnroad;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.Vehicles;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.view.NotificationsView;

import java.util.List;

public interface NotificationsPresenter {
    void setView(NotificationsView view);

    //requestDataToInteractor
    void getMainDate();

    void getAllVehicles(Context context);

    void getAllNotification(List<Vehicles> vehicles, String date, Context context);

    void cancelRequest();

    void rechargeNotifications(List<Vehicles> vehicles, String date, Context context);

    //setDataToView

    void showLoaderFromInteractor();

    void setMainDate(String mainDate);

    void setVehiclesToView(List<Vehicles> vehicles);

    void sessionExpired(String message);

    void setMessageToView(String message);

    void hideLoaderFromInteractor();

    void setAllNotificationsToView(List<NotificationsOnroad> notifications);
}
