package com.pnla.onroadplus.z_version2.MenuFragments.Profile.presenter;

import android.content.Context;
import android.graphics.Typeface;

import com.pnla.onroadplus.z_version2.MenuFragments.Profile.interactor.ProfileInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.Profile.interactor.ProfileInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Profile.view.ProfileView;

public class ProfilePresenterImpl implements ProfilePresenter {

    public Context context;
    private ProfileView view;
    private ProfileInteractor interactor;

    public ProfilePresenterImpl(Context context) {
        this.context = context;
        interactor = new ProfileInteractorImpl(context, this);
    }

    // Request Interactor //

    @Override
    public void initThemeSettings() {
        interactor.getFonts();
    }

    @Override
    public void initProfileSettings() {
        interactor.getUserDataPreferences(context);
        interactor.getUserImageProfile(context);
    }


    @Override
    public void getNotificationsConfiguration(Context context) {
        if (view != null) {
            interactor.getNotificationsConfiguration(context);
        }
    }

    @Override
    public void saveUserNotificationState(boolean isActive, Context context) {
        if (view != null) {
            interactor.saveUserNotificationState(isActive, context);
        }
    }

    @Override
    public void logout() {
        if (view != null) {
            interactor.getLogoutConfirmationDialog();
        }
    }


    // Request View //

    @Override
    public void setView(ProfileView view) {
        this.view = view;
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

    @Override
    public void setUserProfileData(String username, String email) {
        if (view != null) {
            view.setUserProfileData(username, email);
        }
    }

    @Override
    public void setUserImageProfile(String urlImage) {
        if (view != null) {
            view.setUserImageProfile(urlImage);
        }
    }

    @Override
    public void setFonts(Typeface robotoRegular, Typeface robotoBold) {
        if (view != null) {
            view.setFonts(robotoRegular, robotoBold);
        }
    }

    @Override
    public void showHelpScreen() {
        if (view != null) {
            view.goToHelpScreen();
        }
    }

    @Override
    public void showLoginScreen() {
        if (view != null) {
            view.goToLoginScreen();
        }
    }

    @Override
    public void setMessageToView(String message) {

    }


}
