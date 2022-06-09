package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.view;

import java.util.List;

public interface supervisorView {
    void setEmployes(List<String> tripulantesdata);
    void showProgressDialog();
    void hideProgressDialog();

    void restarView();
}
