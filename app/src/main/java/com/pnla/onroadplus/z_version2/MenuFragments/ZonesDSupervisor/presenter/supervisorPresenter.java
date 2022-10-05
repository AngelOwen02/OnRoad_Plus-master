package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.presenter;

import java.util.List;

public interface supervisorPresenter {

    void requestCatalog();
    void getDriversCatalog(List<String> tripulantesdata);
    void setZones(int zonecveLayer, int newCveEmploye);

    void showDialog();
    void hideDialog();


    void restartView();

    void auditTrail(String name);
    void auditTrail2(String name);
    void auditTrail3(String name);
}
