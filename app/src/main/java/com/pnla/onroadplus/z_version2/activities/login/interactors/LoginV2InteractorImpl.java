package com.pnla.onroadplus.z_version2.activities.login.interactors;

import com.pnla.onroadplus.z_version2.activities.login.interfaces.LoginV2Interactor;
import com.pnla.onroadplus.z_version2.activities.login.interfaces.LoginV2Presenter;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmUserData;

public class LoginV2InteractorImpl implements LoginV2Interactor {

    private LoginV2Presenter presenter;

    public LoginV2InteractorImpl(LoginV2Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void existUsers() {
        boolean areThereUsers = RealmUserData.existUsers();
        if (areThereUsers) {
            presenter.showFragmentLoginV2();
        } else {
            presenter.showFragmentHelpV2();
        }
    }

}
