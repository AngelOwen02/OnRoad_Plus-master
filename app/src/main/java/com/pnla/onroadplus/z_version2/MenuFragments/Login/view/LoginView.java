package com.pnla.onroadplus.z_version2.MenuFragments.Login.view;

import android.os.Bundle;

public interface LoginView {

    void showLoader();

    void hideLoader();

    void showMessage(String message);

    void successLogin(String name);

    void showFragmentChangePasswordV2(Bundle bundle);

    void setUserData(String user, String password);

}
