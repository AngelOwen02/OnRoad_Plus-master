package com.pnla.onroadplus.z_version2.MenuFragments.ChangePassword.presenter;

import android.content.Context;
import android.os.Bundle;

import com.pnla.onroadplus.z_version2.MenuFragments.ChangePassword.interactor.ChangePasswordInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.ChangePassword.interactor.ChangePasswordInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.ChangePassword.view.ChangePasswordView;

public class ChangePasswordPresenterImpl implements ChangePasswordPresenter{
    private ChangePasswordView view;
    private ChangePasswordInteractor interactor;

    public ChangePasswordPresenterImpl(Context context) {
        interactor = new ChangePasswordInteractorImpl(this, context);
    }

    @Override
    public void setView(ChangePasswordView view) {
        this.view = view;
    }

    @Override
    public void getDataFromBundle(Bundle bundle) {
        if (view != null) {
            interactor.getDataFromBundle(bundle);
        }
    }

    @Override
    public void validateChangePasswordData(String token, String password1, String password2) {
        if (view != null) {
            view.showLoader();
            interactor.validateChangePasswordData(token, password1, password2);
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
    public void successChangePassword() {
        if (view != null) {
            view.hideLoader();
            view.successChangePassword();
        }
    }

    @Override
    public void setUserDataToView(String token, String email) {
        if (view != null) {
            view.setUserData(token, email);
        }
    }
}
