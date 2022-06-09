package com.pnla.onroadplus.z_version2.MenuFragments.RecoveryPassword.presenter;

import com.pnla.onroadplus.z_version2.MenuFragments.RecoveryPassword.view.RecoveryPasswordView;

public interface RecoveryPasswordPresenter {

    void setView(RecoveryPasswordView view);

    void validateRestorePasswordData(String email);

    //setDataToView
    void setMessageToView(String message);

    void successRestorePassword();
}
