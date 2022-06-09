package com.pnla.onroadplus.z_version2.fragments.changePasswordV2.interfaces;

public interface ChangePasswordV2View {

    void showLoader();

    void hideLoader();

    void showMessage(String message);

    void successChangePassword();

    void setUserData(String token, String email);

}
