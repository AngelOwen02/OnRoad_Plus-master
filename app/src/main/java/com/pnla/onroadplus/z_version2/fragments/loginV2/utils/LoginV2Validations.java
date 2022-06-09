package com.pnla.onroadplus.z_version2.fragments.loginV2.utils;

import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

public class LoginV2Validations {
    public static String validateUserAndPassword(String user, String password) {
        if (user == null || user.length() == 0 || password == null || password.length() == 0) {
            return GeneralConstantsV2.FAILTURE;
        } else {
            return GeneralConstantsV2.SUCCESS;
        }
    }
}
