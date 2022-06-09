package com.pnla.onroadplus.z_version2.fragments.changePasswordV2.utils;

import android.content.Context;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

public class ChangePasswordV2Validations {

    public static String validateToken(String token, Context context) {
        if (token != null && token.length() > 0) {
            return GeneralConstantsV2.SUCCESS;
        } else {
            return context.getString(R.string.textGetEmptyUserData);
        }
    }

    public static String validatePasswords(String password1, String password2, Context context) {
        if (password1 == null || password1.length() == 0 || password2 == null || password2.length() == 0) {
            return context.getString(R.string.textEmptyPasswordsFields);
        }
        if (!password1.equals(password2)) {
            return context.getString(R.string.textDifferentPasswords);
        }

        return GeneralConstantsV2.SUCCESS;
    }

}
