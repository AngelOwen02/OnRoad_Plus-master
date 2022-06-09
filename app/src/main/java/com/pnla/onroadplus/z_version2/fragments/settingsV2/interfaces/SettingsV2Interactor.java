package com.pnla.onroadplus.z_version2.fragments.settingsV2.interfaces;

import android.content.Context;

public interface SettingsV2Interactor {

    void getNotifiationsConfiguration(Context context);

    void saveUserNotificationState(boolean isActive,Context context);
}
