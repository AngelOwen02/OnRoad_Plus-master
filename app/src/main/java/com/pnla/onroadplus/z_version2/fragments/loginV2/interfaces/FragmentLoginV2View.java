package com.pnla.onroadplus.z_version2.fragments.loginV2.interfaces;

import android.os.Bundle;

public interface FragmentLoginV2View {
    void showLoader();

    void hideLoader();

    void showMessage(String message);

    void successLogin();

    void showFragmentChangePasswordV2(Bundle bundle);

    void setUserData(String user, String password);
}
