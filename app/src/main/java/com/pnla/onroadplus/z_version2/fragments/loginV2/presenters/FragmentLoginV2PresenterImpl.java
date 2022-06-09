package com.pnla.onroadplus.z_version2.fragments.loginV2.presenters;

import android.content.Context;
import android.os.Bundle;

import com.pnla.onroadplus.z_version2.fragments.loginV2.interactors.FragmentLoginV2InteractorImpl;
import com.pnla.onroadplus.z_version2.fragments.loginV2.interfaces.FragmentLoginV2Interactor;
import com.pnla.onroadplus.z_version2.fragments.loginV2.interfaces.FragmentLoginV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.loginV2.interfaces.FragmentLoginV2View;

public class FragmentLoginV2PresenterImpl implements FragmentLoginV2Presenter {

    private FragmentLoginV2View view;
    private FragmentLoginV2Interactor interactor;

    public FragmentLoginV2PresenterImpl(Context context) {
        interactor = new FragmentLoginV2InteractorImpl(this, context);
    }

    @Override
    public void setView(FragmentLoginV2View view) {
        this.view = view;
    }

    @Override
    public void showLoaderFromInteractor() {
        if (view != null) {
            view.showLoader();
        }
    }

    @Override
    public void getUserDataPreferences() {
        if (view != null) {
            interactor.getUserDataPreferences();
        }
    }

    @Override
    public void validateDataToStartLoginRequest(String user, String password) {
        if (view != null) {
            view.showLoader();
            interactor.validateData(user, password);
        }
    }

    @Override
    public void successLogin() {
        if (view != null) {
            view.hideLoader();
            view.successLogin();
        }
    }

    @Override
    public void setUserData(String user, String password) {
        if (view != null) {
            view.hideLoader();
            view.setUserData(user, password);
        }
    }

    @Override
    public void showFragmentChangePasswordV2(Bundle bundle) {
        if (view != null) {
            view.hideLoader();
            view.showFragmentChangePasswordV2(bundle);
        }
    }

    @Override
    public void setMessageToView(String message) {
        if (view != null) {
            view.hideLoader();
            view.showMessage(message);
        }
    }

}
