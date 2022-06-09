package com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.presenter;

import java.util.List;

public interface menuPresenter {


    void itemsMenu();
    void setErrorToView(String error);
    void nameslistitems(List<String> items);
    void closeAppSessionExpired();
}
