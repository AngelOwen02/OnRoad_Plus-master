package com.pnla.onroadplus.z_version2.fragments.contactV2.interfaces;

import android.content.Context;

public interface ContactV2Presenter {

    void setView(ContactV2View view);

    //requestDataToInteractor
    void getUserEmail(Context context);

    void validateUserDataToSend(String to, String subject,String message,Context context);

    //setDataToView
    void setMessageToView(String message);

    void setUserEmail(String email);

    void sessionExpired(String message);

    void successContactService();
}
