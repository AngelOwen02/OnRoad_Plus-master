package com.pnla.onroadplus.z_version2.fragments.restorePasswordV2.interfaces;

public interface FragmentRestorePasswordV2Presenter {
    void setView(FragmentRestorePasswordV2View view);

    void validateRestorePasswordData(String email);

    //setDataToView
    void setMessageToView(String message);

    void successRestorePassword();
}
