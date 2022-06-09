package com.pnla.onroadplus.z_version2.fragments.settingsV2.interactors;

import android.content.Context;
import android.content.SharedPreferences;

import com.pnla.onroadplus.z_version2.fragments.settingsV2.interfaces.SettingsV2Interactor;
import com.pnla.onroadplus.z_version2.fragments.settingsV2.interfaces.SettingsV2Presenter;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmUserNotifications;
import com.pnla.onroadplus.z_version2.realmOnRoad.UserNotificationRealmSave;

public class SettingsV2InteractorImp implements SettingsV2Interactor {

    private SettingsV2Presenter presenter;

    public SettingsV2InteractorImp(SettingsV2Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getNotifiationsConfiguration(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String user = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);

        UserNotificationRealmSave userNotificationRealmSave = RealmUserNotifications.existUserNotification(user);
        if (userNotificationRealmSave != null) {
            boolean isActiveNotification = userNotificationRealmSave.isActiveNotifications();
            if (isActiveNotification) {
                presenter.activeNotifications();
            } else {
                presenter.disabledNotifications();
            }
        } else {
            presenter.disabledNotifications();
        }

    }

    @Override
    public void saveUserNotificationState(boolean isActive, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String user = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);

        if (RealmUserNotifications.existUserNotification(user) == null) {
            RealmUserNotifications.saveUserNotifications(user, isActive);
        } else {
            RealmUserNotifications.updateUserNotification(user, isActive);
        }

    }
}
