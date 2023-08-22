package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Interactor;

import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model.VehicleDriver;

import java.util.List;

public interface zonesAsignInteractor {
    void getAsignments(String cveLayer);
    void getFUnits();
    void getFDrivers();
    void getFDriversT();
    void updateFData(List<VehicleDriver> zones);

    void newsetAuditTrail(String descripcion);
    void newsetAuditTrail2(String descripcion);
    void newsetAuditTrail3(String descripcion);
    void newsetAuditTrail4(String descipcion);
    void newsetAuditTrail5(String descripcion);


}
