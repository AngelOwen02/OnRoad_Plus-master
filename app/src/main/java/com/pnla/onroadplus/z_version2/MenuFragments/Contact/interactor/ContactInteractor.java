package com.pnla.onroadplus.z_version2.MenuFragments.Contact.interactor;

import android.content.Context;

public interface ContactInteractor {

    void getUserEmail(Context context);
    void getSupportEmail();
    void validateUserDataToSend(String to, String subject, String message, Context context);
    void getFonts();

    void getSendConfirmationDialog();
}
