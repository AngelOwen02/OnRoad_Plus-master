package com.pnla.onroadplus.z_version2.Containers.LoginContainer.interactor;

import android.os.Bundle;

import com.pnla.onroadplus.z_version2.Containers.LoginContainer.presenter.LoginContainerPresenter;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmUserData;

public class LoginContainerInteractorImpl implements LoginContainerInteractor {

    private LoginContainerPresenter presenter;

    public LoginContainerInteractorImpl(LoginContainerPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void existUsers(Bundle bndl) {
        boolean areThereUsers = RealmUserData.existUsers();
       // SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        //String userName = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);
        if (areThereUsers) {
            presenter.showLoginViewImpl();

        } else {
            if(bndl!=null) {
                if (bndl.getBoolean("HelpStatus")) {
                    presenter.showLoginViewImpl();
                } else if (bndl == null) {
                    presenter.showHelpImplView();
                } else {
                    presenter.showHelpImplView();

                }
            }
        }
    }
}
