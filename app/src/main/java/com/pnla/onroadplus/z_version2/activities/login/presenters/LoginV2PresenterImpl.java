package com.pnla.onroadplus.z_version2.activities.login.presenters;

import com.pnla.onroadplus.z_version2.activities.login.interactors.LoginV2InteractorImpl;
import com.pnla.onroadplus.z_version2.activities.login.interfaces.LoginV2Interactor;
import com.pnla.onroadplus.z_version2.activities.login.interfaces.LoginV2Presenter;
import com.pnla.onroadplus.z_version2.activities.login.interfaces.LoginV2View;

public class LoginV2PresenterImpl implements LoginV2Presenter {

    private LoginV2View view;
    private LoginV2Interactor interactor;

    public LoginV2PresenterImpl() {
        interactor = new LoginV2InteractorImpl(this);
    }

    @Override
    public void setView(LoginV2View view) {
        this.view = view;
    }

    @Override
    public void existUsers() {
        if (view != null) {
            interactor.existUsers();
        }
    }

    @Override
    public void showFragmentLoginV2() {
        if (view != null) {
            view.showLoginFragmentV2();
        }
    }

    @Override
    public void showFragmentHelpV2() {
        if (view != null) {
            view.showFragmentHelpV2();
        }
    }
}
