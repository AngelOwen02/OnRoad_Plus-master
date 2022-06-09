package com.pnla.onroadplus.z_version2.MenuFragments.Contact.presenter;

import android.content.Context;
import android.graphics.Typeface;

import com.pnla.onroadplus.z_version2.MenuFragments.Contact.view.ContactView;

public interface ContactPresenter {


    // Request Profile Interactor //
    void getUserEmail(Context context);

    void validateUserDataToSend(String to, String subject,String message,Context context);

    // Set Data To View //

    void setMessageToView(String message);

    void setUserEmail(String email);
    void setSupportEmail(String email);


    void sessionExpired(String message);

    void successContactService();

    void getSupportEmail();
    void setView(ContactView view);

    void initThemeSettings();

    void setFonts(Typeface robotoLight, Typeface robotoRegular, Typeface robotoMedium);

    void sendEmail();
    void getSendConfirmationDialog();
}
