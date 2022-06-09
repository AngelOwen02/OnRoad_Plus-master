package com.pnla.onroadplus.z_version2.fragments.loginV2.interfaces;

import android.os.Bundle;

public interface    FragmentLoginV2Presenter {

    //view
    void setView(FragmentLoginV2View view);

    void showLoaderFromInteractor();

    //request data to interactor
    void getUserDataPreferences();
    void validateDataToStartLoginRequest(String user, String password);

    //setDataToview;
    void successLogin();

    void setUserData(String user,String password);

    void showFragmentChangePasswordV2(Bundle bundle);

    void setMessageToView(String message);

}
