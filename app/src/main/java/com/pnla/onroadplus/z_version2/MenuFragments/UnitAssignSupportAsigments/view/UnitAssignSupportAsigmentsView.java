package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.view;

import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SingleSupportUnitData;

import java.util.List;

public interface UnitAssignSupportAsigmentsView {

    void setSoportes(List<SingleSupportUnitData> data);
    void failureResponse(String message);
    void showProgressDialog();
    void hideProgressDialog();

    void AssignmentSupportSuccess();
    void deleteUnitAssign();
}
