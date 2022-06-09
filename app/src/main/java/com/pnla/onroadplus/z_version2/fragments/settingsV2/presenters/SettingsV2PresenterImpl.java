package com.pnla.onroadplus.z_version2.fragments.settingsV2.presenters;

import android.content.Context;

import com.pnla.onroadplus.z_version2.fragments.settingsV2.interactors.SettingsV2InteractorImp;
import com.pnla.onroadplus.z_version2.fragments.settingsV2.interfaces.SettingsV2Interactor;
import com.pnla.onroadplus.z_version2.fragments.settingsV2.interfaces.SettingsV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.settingsV2.interfaces.SettingsV2View;

public class SettingsV2PresenterImpl implements SettingsV2Presenter {

    private SettingsV2View view;
    private SettingsV2Interactor interactor;

    public SettingsV2PresenterImpl() {
        interactor = new SettingsV2InteractorImp(this);
    }

    @Override
    public void setView(SettingsV2View view) {
        this.view = view;
    }

    @Override
    public void getNotificationsConfiguration(Context context) {
        if (view != null) {
            interactor.getNotifiationsConfiguration(context);
        }
    }

    @Override
    public void saveUserNotificationState(boolean isActive, Context context) {
        if (view != null) {
            interactor.saveUserNotificationState(isActive, context);
        }
    }

    @Override
    public void activeNotifications() {
        if (view != null) {
            view.activeNotifications();
        }
    }

    @Override
    public void disabledNotifications() {

        if (view != null) {
            view.disabledNotifications();
        }
    }
}
