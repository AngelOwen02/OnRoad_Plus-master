package com.pnla.onroadplus.z_version2.MenuFragments.ChangePassword.presenter;

import android.os.Bundle;

import com.pnla.onroadplus.z_version2.MenuFragments.ChangePassword.view.ChangePasswordView;

public interface ChangePasswordPresenter {
    void setView(ChangePasswordView view);

    //requestDataToInteractor
    void getDataFromBundle(Bundle bundle);

    void validateChangePasswordData(String token, String password1, String password2);

    //setDataToView
    void setMessageToView(String message);

    void successChangePassword();

    void setUserDataToView(String token, String email);
}
