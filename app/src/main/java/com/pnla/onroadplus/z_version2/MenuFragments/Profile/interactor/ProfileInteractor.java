package com.pnla.onroadplus.z_version2.MenuFragments.Profile.interactor;

import android.content.Context;

public interface ProfileInteractor {

    void getFonts();
    void getUserDataPreferences(Context context);
    void getUserImageProfile(Context context);
    void getNotificationsConfiguration(Context context);
    void getLogoutConfirmationDialog();

    void saveUserNotificationState(boolean isActive,Context context);
    void validateDataToCloseSession(Context context);
    void intentToHelpScreen(Context context);



}
