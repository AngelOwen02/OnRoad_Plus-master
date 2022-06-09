package com.pnla.onroadplus.z_version2.Containers.LoginContainer.presenter;

import android.os.Bundle;

import com.pnla.onroadplus.z_version2.Containers.LoginContainer.interactor.LoginContainerInteractor;
import com.pnla.onroadplus.z_version2.Containers.LoginContainer.interactor.LoginContainerInteractorImpl;
import com.pnla.onroadplus.z_version2.Containers.LoginContainer.view.LoginContainerView;

public class LoginContainerPresenterImpl implements LoginContainerPresenter {

    private LoginContainerView view;
    private LoginContainerInteractor interactor;

    public LoginContainerPresenterImpl() {
        interactor = new LoginContainerInteractorImpl(this);

    }

    @Override
    public void setView(LoginContainerView view) {
        this.view = view;
    }

    @Override
    public void existUsers(Bundle bndle) {
        if (view != null) {
            interactor.existUsers(bndle);
        }
    }

    @Override
    public void showLoginViewImpl() {
        if (view != null) {
            view.showLoginViewImpl();
        }
    }

    @Override
    public void         showHelpImplView() {
        if (view != null) {
            view.showHelpViewImpl();
        }
    }
}
