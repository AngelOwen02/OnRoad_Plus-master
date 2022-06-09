package com.pnla.onroadplus.z_version2.MenuFragments.RecoveryPassword.presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.RecoveryPassword.interactor.RecoveryPasswordInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.RecoveryPassword.interactor.RecoveryPasswordInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.RecoveryPassword.view.RecoveryPasswordView;

public class RecoveryPasswordPresenterImpl implements RecoveryPasswordPresenter {

    private Context context;
    private RecoveryPasswordView view;
    private RecoveryPasswordInteractor interactor;

    public RecoveryPasswordPresenterImpl(Context context) {
        this.context = context;
        interactor = new RecoveryPasswordInteractorImpl(this,context);
    }

    @Override
    public void setView(RecoveryPasswordView view) {
        this.view = view;
    }

    @Override
    public void validateRestorePasswordData(String email) {
        if (view != null) {
            view.showLoader();
            interactor.validateRestorePasswordData(email);
        }
    }

    @Override
    public void setMessageToView(String message) {
        if (view != null) {
            view.hideLoader();
            view.showErrorMessage(message);
        }
    }

    @Override
    public void successRestorePassword() {
        if (view != null) {
            view.hideLoader();
            view.successRestorePassword();
        }
    }
}
