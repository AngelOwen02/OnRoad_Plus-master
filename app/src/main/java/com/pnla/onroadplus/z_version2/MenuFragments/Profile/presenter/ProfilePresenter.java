package com.pnla.onroadplus.z_version2.MenuFragments.Profile.presenter;

import android.content.Context;
import android.graphics.Typeface;

import com.pnla.onroadplus.z_version2.MenuFragments.Profile.view.ProfileView;

public interface ProfilePresenter {

    // Request Profile Interactor //

    void initThemeSettings();

    void initProfileSettings();

    void getNotificationsConfiguration(Context context);

    void saveUserNotificationState(boolean isActive, Context context);

    void logout();


    // Set Profile View //

    void setView(ProfileView view);

    void activeNotifications();

    void disabledNotifications();

    void setUserProfileData(String username, String email);

    void setUserImageProfile(String urlImage);


    void setFonts(Typeface robotoRegular, Typeface robotoBold);

    void setMessageToView(String message);

    void showHelpScreen();

    void showLoginScreen();



}
