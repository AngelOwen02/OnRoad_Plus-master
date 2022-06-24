package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.view.UnitAssignSupportView;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.view.UnitTrackingView;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.Group;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;

import java.io.IOException;
import java.util.List;

public interface UnitAssignSupportPresenter {

    void setView(UnitAssignSupportView view);

    void getFullVehicles();
    //  void getvehiclesINgroups();
    void georeferenceformAPI(List<Integer> values) throws IOException;
    void setVehiclesListToView(List<Unit> unitList) throws IOException;
    void setdirectionsToView(List<String> addresList);
    void failureResponse(String message);

    void showProgressDialog();

    void hideProgressDialog();
}
