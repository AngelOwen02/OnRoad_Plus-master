package com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.view;

import java.util.List;

public interface menuView {

    void showError(String error);
    void closeAppSessionExpired();
    void listItems(List<String> items);
}
