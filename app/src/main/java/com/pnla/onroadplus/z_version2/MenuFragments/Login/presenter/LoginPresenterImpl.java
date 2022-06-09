package com.pnla.onroadplus.z_version2.MenuFragments.Login.presenter;

import android.content.Context;
import android.os.Bundle;

import com.pnla.onroadplus.z_version2.MenuFragments.Login.interactor.LoginInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.Login.interactor.LoginInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Login.view.LoginView;


public class LoginPresenterImpl implements  LoginPresenter{

    private LoginView view;
    private LoginInteractor interactor;

    public LoginPresenterImpl(Context context) {
        interactor = new LoginInteractorImpl(this, context);
    }

    @Override
    public void setView(LoginView view) {
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
    public void auditTrail(String name) {
        if (view != null) {
            interactor.newsetAuditTrail(name);
        }
    }
    @Override
    public void successLogin(String name) {
        if (view != null) {
            view.hideLoader();
            view.successLogin(name);
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


    @Override
    public void setErrorMessage(String message) {

    }


}
