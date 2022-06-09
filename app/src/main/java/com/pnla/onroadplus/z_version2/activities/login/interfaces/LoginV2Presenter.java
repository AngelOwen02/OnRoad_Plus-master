package com.pnla.onroadplus.z_version2.activities.login.interfaces;

public interface LoginV2Presenter {
    void setView(LoginV2View view);

    //requestDataToInteractor
    void existUsers();

    //setDataToView
    void showFragmentLoginV2();

    void showFragmentHelpV2();
}
