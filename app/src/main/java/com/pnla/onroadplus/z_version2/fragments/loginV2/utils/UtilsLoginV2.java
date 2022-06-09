package com.pnla.onroadplus.z_version2.fragments.loginV2.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

public class UtilsLoginV2 {

    public static void saveUserDataSharedPreferences(String urlImage, String user, String token, String email, String password, String employeeName, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(GeneralConstantsV2.USER_PREFERENCES, user);
        editor.putString(GeneralConstantsV2.URL_USER_IMAGE_PREFERENCES, urlImage);
        editor.putString(GeneralConstantsV2.TOKEN_PREFERENCES, token);
        editor.putString(GeneralConstantsV2.EMAIL_PREFERENCES, email);
        editor.putString(GeneralConstantsV2.PASSWORD_PREFERENCES, password);
        editor.putString(GeneralConstantsV2.EMPLOYEE_NAME_PREFERENCES, employeeName);
        editor.putString(GeneralConstantsV2.CLOSE_SESSION_PREFERENCES, "NO");
        editor.commit();
    }

}
