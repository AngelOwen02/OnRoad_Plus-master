package com.pnla.onroadplus.z_version2.fragments.settingsV2.interfaces;

import android.content.Context;

public interface SettingsV2Presenter {

    void setView(SettingsV2View view);

    //requestDataToInteractor
    void getNotificationsConfiguration(Context context);

    void saveUserNotificationState(boolean isActive,Context context);

    //setDataToView
    void activeNotifications();

    void disabledNotifications();

}
