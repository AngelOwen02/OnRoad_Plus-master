package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.presenter;

import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SingleSupportUnitData;

import java.util.List;

public interface UnitAssignSupportAsigmentsPresenter {

    void setSoportes(List<SingleSupportUnitData> data);
    void getSoportes(int cveLayer);
    void showProgressDialog();
    void hideProgressDialog();

    void requestSetAssignSupport(int cveLayer, String cve_vehicle);

    void setUnitAssignSupport(String data);

    void deleteUnitAssign(int cveLayer, String cve_vehicle);

    void deleteSuccess(String data);
}
