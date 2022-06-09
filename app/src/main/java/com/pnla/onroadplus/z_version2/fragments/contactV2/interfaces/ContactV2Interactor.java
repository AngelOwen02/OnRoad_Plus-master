package com.pnla.onroadplus.z_version2.fragments.contactV2.interfaces;

import android.content.Context;

public interface ContactV2Interactor {

    void getUserEmail(Context context);

    void validateUserDataToSend(String to, String subject,String message,Context context);

}
