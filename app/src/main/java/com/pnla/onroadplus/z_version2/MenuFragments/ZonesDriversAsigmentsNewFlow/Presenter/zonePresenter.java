package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Presenter;


import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model.VehicleDriver;

import java.util.List;

public interface zonePresenter {

    void requestAsignment(String asign);
    void requestUnitsCatalog();
    void requesDriverCatalog();
    void updateAsignments( List<VehicleDriver> zones);

    void setAsignments(List<VehicleDriver> myAsignments);
    void setVehiclesList(List<String> vehicles);
    void setDrivers(List<String> drivers);

    void setV(List<String> V);
    void setD(List<String> D);

    void setDriversNodefaulValue(List<String> d2);
    void setMessageToView(String message);
    void showDialog();
    void hideDialog();
    void auditTrail(String name);
    void restartAfterUpdate();
}
