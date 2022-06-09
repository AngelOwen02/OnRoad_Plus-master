package com.pnla.onroadplus.z_version2.MenuFragments.Profile.view;

import android.graphics.Typeface;

public interface ProfileView {


    void activeNotifications();

    void disabledNotifications();

    void setUserImageProfile(String urlImage);
    void setUserProfileData(String username, String email);
    void setFonts(Typeface robotoRegular, Typeface robotoBold);
    void goToHelpScreen();
    void goToLoginScreen();



}
