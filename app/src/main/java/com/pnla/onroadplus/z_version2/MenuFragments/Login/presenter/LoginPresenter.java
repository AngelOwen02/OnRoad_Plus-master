package com.pnla.onroadplus.z_version2.MenuFragments.Login.presenter;

import android.os.Bundle;

import com.pnla.onroadplus.z_version2.MenuFragments.Login.view.LoginView;

public interface LoginPresenter {

    void getUserDataPreferences();

    void validateDataToStartLoginRequest(String user, String password);
    void auditTrail(String name);



    void successLogin(String name);

    void setView(LoginView view);

    void showLoaderFromInteractor();

    void setUserData(String user,String password);

    void showFragmentChangePasswordV2(Bundle bundle);

    void setMessageToView(String message);

    void setErrorMessage(String message);


}
