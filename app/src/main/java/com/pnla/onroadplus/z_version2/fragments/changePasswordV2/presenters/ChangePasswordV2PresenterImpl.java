package com.pnla.onroadplus.z_version2.fragments.changePasswordV2.presenters;

import android.content.Context;
import android.os.Bundle;

import com.pnla.onroadplus.z_version2.fragments.changePasswordV2.interactors.ChangePasswordV2InteractorImpl;
import com.pnla.onroadplus.z_version2.fragments.changePasswordV2.interfaces.ChangePasswordV2Interactor;
import com.pnla.onroadplus.z_version2.fragments.changePasswordV2.interfaces.ChangePasswordV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.changePasswordV2.interfaces.ChangePasswordV2View;

public class ChangePasswordV2PresenterImpl implements ChangePasswordV2Presenter {

    private ChangePasswordV2View view;
    private ChangePasswordV2Interactor interactor;

    public ChangePasswordV2PresenterImpl(Context context) {
        interactor = new ChangePasswordV2InteractorImpl(this, context);
    }

    @Override
    public void setView(ChangePasswordV2View view) {
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