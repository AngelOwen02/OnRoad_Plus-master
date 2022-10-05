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
    void restartAfterUpdate();

    //Editar 1
    void auditTrail(String name);
    //Agregar 2
    void auditTrail2(String name);
    //Eliminar 3
    void auditTrail3(String name);
    //Cuando se modifican datos del icono Volante
    void auditTrail4(String name);
    //cuando se modifican los tripulantes
    void auditTrail5(String name);
}
