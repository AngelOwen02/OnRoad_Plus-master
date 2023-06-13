package com.pnla.onroadplus.z_version2.MenuFragments.Units.presenter;

import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.view.UnitsViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.dataRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.dataresponseUnitsV3;

import java.io.IOException;
import java.util.List;

public interface UnitsPresenter {

    void setView(UnitsViewImpl view);

    //void setView2(UnitAssignSupportViewImpl view);

    void getFullVehicles(boolean ismorethan20);
  //  void getvehiclesINgroups();
    void georeferenceformAPI(List<Integer> values) throws IOException;
    void setVehiclesListToView(List<Unit> unitList) throws IOException;
    void setdirectionsToView(List<String> addresList);
    void failureResponse(String message);

    void showProgressDialog();

    void hideProgressDialog();
    void auditTrail(String s);

    void askgeofences(List<dataRequest> askgeofences);

    void setVehiclesGeos(List<dataresponseUnitsV3> data);
}
