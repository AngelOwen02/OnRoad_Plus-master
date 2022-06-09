package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Interactor;

import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model.VehicleDriver;

import java.util.List;

public interface zonesAsignInteractor {
    void getAsignments(String cveLayer);
    void getFUnits();
    void getFDrivers();
    void updateFData(List<VehicleDriver> zones);
    void newsetAuditTrail(String descripcion);
}
