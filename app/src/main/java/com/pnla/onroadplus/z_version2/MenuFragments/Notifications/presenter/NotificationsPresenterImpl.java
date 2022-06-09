package com.pnla.onroadplus.z_version2.MenuFragments.Notifications.presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.interactor.NotificationsInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.interactor.NotificationsInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.NotificationsOnroad;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.Vehicles;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.view.NotificationsView;

import java.util.List;

public class NotificationsPresenterImpl implements NotificationsPresenter {
    private NotificationsView view;
    private NotificationsInteractor interactor;

    public NotificationsPresenterImpl() {
        interactor = new NotificationsInteractorImpl(this);
    }

    @Override
    public void setView(NotificationsView view) {
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
    public void getAllNotification(List<Vehicles> vehicles, String date, Context context) {
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
    public void rechargeNotifications(List<Vehicles> vehicles, String date, Context context) {
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
    public void setVehiclesToView(List<Vehicles> vehicles) {
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
    public void setAllNotificationsToView(List<NotificationsOnroad> notifications) {
        if (view != null) {
            view.fillAllNotifications(notifications);
        }
    }
}
