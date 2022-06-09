package com.pnla.onroadplus.z_version2.fragments.changePasswordV2.interfaces;

import android.os.Bundle;

public interface ChangePasswordV2Interactor {
    void getDataFromBundle(Bundle bundle);

    void validateChangePasswordData(String token, String password1, String password2);
}
