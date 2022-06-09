package com.pnla.onroadplus.z_version2.fragments.contactV2.interfaces;

public interface ContactV2View {

    void showLoader();
    void hideLoader();
    void showMessage(String message);
    void sessionExpired(String message);
    void successContactService();
    void setUserEmail(String email);

}
