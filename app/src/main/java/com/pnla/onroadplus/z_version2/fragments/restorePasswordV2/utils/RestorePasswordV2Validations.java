package com.pnla.onroadplus.z_version2.fragments.restorePasswordV2.utils;

import android.content.Context;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

public class RestorePasswordV2Validations {
    public static String validateEmailSize(Context context, String email) {
        if (email == null || email.length() == 0) {
            return context.getString(R.string.textEmptyEmail);
        } else {
            return GeneralConstantsV2.SUCCESS;
        }
    }

    public static String validateEmailFormat(Context context, String email) {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email != null && email.length() > 0) {
            if (email.matches(emailPattern)) {
                return GeneralConstantsV2.SUCCESS;
            } else {
                return context.getString(R.string.textInvalidEmailFormat);
            }
        } else {
            return context.getString(R.string.textInvalidEmailFormat);
        }

    }

}
