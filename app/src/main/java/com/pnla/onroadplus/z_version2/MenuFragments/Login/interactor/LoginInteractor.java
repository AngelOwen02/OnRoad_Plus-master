package com.pnla.onroadplus.z_version2.MenuFragments.Login.interactor;

public interface LoginInteractor {
    void getUserDataPreferences();
    void validateData(String user, String password);

    void newsetAuditTrail(String name);
}
