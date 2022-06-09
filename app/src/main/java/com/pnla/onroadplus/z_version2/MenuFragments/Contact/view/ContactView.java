package com.pnla.onroadplus.z_version2.MenuFragments.Contact.view;

import android.graphics.Typeface;

public interface ContactView {
    void showLoader();
    void hideLoader();
    void showMessage(String message);
    void sessionExpired(String message);

    void successContactService();
    void setUserEmail(String email);
    void setSupportEmail(String email);
    void setFonts(Typeface robotoLight, Typeface robotoRegular, Typeface robotoMedium);

}
