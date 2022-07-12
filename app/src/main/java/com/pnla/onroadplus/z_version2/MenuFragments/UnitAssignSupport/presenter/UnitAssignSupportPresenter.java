package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.view.UnitAssignSupportView;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.view.UnitTrackingView;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.Group;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SupportUnitData;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;

import java.io.IOException;
import java.util.List;

public interface UnitAssignSupportPresenter {

    void setView(UnitAssignSupportView view);
    //void getFullVehicles();
    //void georeferenceformAPI(List<Integer> values) throws IOException;
    void setSoportes(List<SupportUnitData> data);
    void requestVehicles();
    void failureResponse(String message);
    void showProgressDialog();
    void hideProgressDialog();
}
