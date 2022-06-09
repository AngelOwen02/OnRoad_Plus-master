package com.pnla.onroadplus.z_version2.MenuFragments.ChangePassword.view;

public interface ChangePasswordView {

    void showLoader();

    void hideLoader();

    void showMessage(String message);

    void successChangePassword();

    void setUserData(String token, String email);

}
