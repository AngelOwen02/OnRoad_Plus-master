package com.pnla.onroadplus.z_version2.MenuFragments.ChangePassword.interactor;

import android.os.Bundle;

public interface ChangePasswordInteractor {

    void getDataFromBundle(Bundle bundle);

    void validateChangePasswordData(String token, String password1, String password2);
}
