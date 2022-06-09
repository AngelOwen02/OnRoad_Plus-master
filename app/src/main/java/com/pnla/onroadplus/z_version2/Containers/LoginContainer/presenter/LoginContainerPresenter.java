package com.pnla.onroadplus.z_version2.Containers.LoginContainer.presenter;

import android.os.Bundle;

import com.pnla.onroadplus.z_version2.Containers.LoginContainer.view.LoginContainerView;

public interface LoginContainerPresenter {

    void existUsers(Bundle bndl);

    void setView(LoginContainerView view);


    void showLoginViewImpl();

    void showHelpImplView();
}
