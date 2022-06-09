package com.pnla.onroadplus.z_version2.fragments.restorePasswordV2.presenters;

import android.content.Context;

import com.pnla.onroadplus.z_version2.fragments.restorePasswordV2.interactors.RestorePasswordV2InteractorImpl;
import com.pnla.onroadplus.z_version2.fragments.restorePasswordV2.interfaces.FragmentRestorePasswordV2Interactor;
import com.pnla.onroadplus.z_version2.fragments.restorePasswordV2.interfaces.FragmentRestorePasswordV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.restorePasswordV2.interfaces.FragmentRestorePasswordV2View;

public class RestoresPasswordV2PresenterImpl implements FragmentRestorePasswordV2Presenter {

    private FragmentRestorePasswordV2View view;
    private FragmentRestorePasswordV2Interactor interactor;

    public RestoresPasswordV2PresenterImpl(Context context) {
        interactor = new RestorePasswordV2InteractorImpl(this, context);
    }

    @Override
    public void setView(FragmentRestorePasswordV2View view) {
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
            view.showMessage(message);
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
