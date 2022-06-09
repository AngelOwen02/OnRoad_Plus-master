package com.pnla.onroadplus.z_version2.MenuFragments.Units.presenter;

import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.view.UnitsViewImpl;

import java.io.IOException;
import java.util.List;

public interface UnitsPresenter {

    void setView(UnitsViewImpl view);

    void getFullVehicles();
  //  void getvehiclesINgroups();
    void georeferenceformAPI(List<Integer> values) throws IOException;
    void setVehiclesListToView(List<Unit> unitList) throws IOException;
    void setdirectionsToView(List<String> addresList);
    void failureResponse(String message);

    void showProgressDialog();

    void hideProgressDialog();





}
