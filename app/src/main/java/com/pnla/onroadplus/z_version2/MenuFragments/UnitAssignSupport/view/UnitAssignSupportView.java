package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.view;

import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.Group;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SupportUnitData;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;

import java.io.IOException;
import java.util.List;

public interface UnitAssignSupportView {

    void setSoportes(List<SupportUnitData> data);
    void failureResponse(String message);
    void showProgressDialog();
    void hideProgressDialog();

    void returnToMap();
}
