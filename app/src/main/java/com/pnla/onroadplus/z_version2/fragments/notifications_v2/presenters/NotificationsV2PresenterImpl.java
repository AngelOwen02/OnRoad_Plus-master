package com.pnla.onroadplus.z_version2.fragments.notifications_v2.presenters;

import android.content.Context;

import com.pnla.onroadplus.z_version2.fragments.notifications_v2.interactors.NotificationsV2InteractorImpl;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.interfaces.NotificationsV2Interactor;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.interfaces.NotificationsV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.interfaces.NotificationsV2View;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.models.NotificationV2OnRoad;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;

import java.util.List;

public class NotificationsV2PresenterImpl implements NotificationsV2Presenter {

    private NotificationsV2View view;
    private NotificationsV2Interactor interactor;

    public NotificationsV2PresenterImpl() {
        interactor = new NotificationsV2InteractorImpl(this);
    }

    @Override
    public void setView(NotificationsV2View view) {
        this.view = view;
    }

    @Override
    public void getMainDate() {
        if (view != null) {
            interactor.getMainDate();
        }
    }

    @Override
    public void getAllVehicles(Context context) {
        if (view != null) {
            view.showLoader();
            interactor.getAllVehicles(context);
        }
    }

    @Override
    public void getAllNotification(List<VehicleV2> vehicles, String date, Context context) {
        if (view != null) {
            interactor.getAllNotification(vehicles, date, context);
        }
    }

    @Override
    public void cancelRequest() {
        if (view != null) {
            interactor.cancelRequest();
        }
    }

    @Override
    public void rechargeNotifications(List<VehicleV2> vehicles, String date, Context context) {
        if (view != null) {
            interactor.rechargeNotifications(vehicles, date, context);
        }
    }

    @Override
    public void showLoaderFromInteractor() {
        if (view != null) {
            view.showLoader();
        }
    }

    @Override
    public void setMainDate(String mainDate) {
        if (view != null) {
            view.showCurrentDate(mainDate);
        }
    }

    @Override
    public void setVehiclesToView(List<VehicleV2> vehicles) {
        if (view != null) {
            view.fillVehiclesList(vehicles);
        }
    }

    @Override
    public void sessionExpired(String message) {
        if (view != null) {
            view.hideLoader();
            view.sessionExpired(message);
        }
    }

    @Override
    public void setMessageToView(String message) {
        if (view != null) {
            view.hideLoader();
            view.showMessage(message);
        }
    }

    @Override
    public void hideLoaderFromInteractor() {
        if (view != null) {
            view.hideLoader();
        }
    }

    @Override
    public void setAllNotificationsToView(List<NotificationV2OnRoad> notifications) {
        if (view != null) {
            view.fillAllNotifications(notifications);
        }
    }
}
