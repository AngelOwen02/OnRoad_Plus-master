package com.pnla.onroadplus.z_version2.fragments.contactV2.utils;

import android.content.Context;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

public class ContactV2Utils {

    public static String validateContactData(String to, String subject, String message, String token, Context context) {
        if (subject == null || subject.length() == 0 || message == null || message.length() == 0) {
            return context.getString(R.string.textEmptyFieldsContact);
        }
        if (token == null || token.length() == 0) {
            return context.getString(R.string.textEmptyDataContact);
        }
        if (to == null || to.length() == 0) {
            return "Error, el correo electrónico de soprte técnico no es válido.";
        }
        return GeneralConstantsV2.SUCCESS;
    }


}
