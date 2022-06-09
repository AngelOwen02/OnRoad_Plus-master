package com.pnla.onroadplus.z_version2.fragments.changePasswordV2.interfaces;

import android.os.Bundle;

public interface ChangePasswordV2Presenter {

    void setView(ChangePasswordV2View view);

    //requestDataToInteractor
    void getDataFromBundle(Bundle bundle);

    void validateChangePasswordData(String token, String password1, String password2);

    //setDataToView
    void setMessageToView(String message);

    void successChangePassword();

    void setUserDataToView(String token, String email);
}
