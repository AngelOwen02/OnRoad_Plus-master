package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.interactor;

public interface UnitAssignSupportAsigmentsInteractor {

    void requestSoportes(int cveLayer);

    void setAssignSupport(Integer layer, int cveLayer, String cve_vehicle);

    void deleteUnitAssign(int cveLayer, String cve_vehicle, int cve_layer_support);
}
