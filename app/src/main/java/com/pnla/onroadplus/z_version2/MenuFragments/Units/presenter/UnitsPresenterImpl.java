package com.pnla.onroadplus.z_version2.MenuFragments.Units.presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.view.UnitAssignSupportViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.interactor.UnitsInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.interactor.UnitsInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.view.UnitsView;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.view.UnitsViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.dataRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.dataresponseUnitsV3;

import java.io.IOException;
import java.util.List;

public class UnitsPresenterImpl implements UnitsPresenter {

    private Context context;
    private UnitsView view;
    private UnitsInteractor interactor;

    public UnitsPresenterImpl(Context context) {
        this.context = context;
        interactor = new UnitsInteractorImpl(this, context);
    }

    @Override
    public void getFullVehicles(boolean ismorethan20) {
        if (view != null){

            interactor.getAllVehiclesFromAPI(ismorethan20);
        }
    }

    @Override
    public void georeferenceformAPI(List<Integer> values) throws IOException {
        if (view != null){
            interactor.getGeoreferencefromAPI(values);
        }
    }

    // @Override
  //  public void getvehiclesINgroups() {
      // if (view != null){
   //         interactor.getGroupsVehicles();
  //      }
  //  }

    @Override
    public void showProgressDialog() {
        if (view != null){
            view.showProgressDialog();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (view != null){
         //   view.hideProgressDialog();
        }
    }

    @Override
    public void askgeofences(List<dataRequest> askgeofences) {
        if (view != null){
            interactor.askgeofences(askgeofences);
        }
    }

    @Override
    public void setVehiclesGeos(List<dataresponseUnitsV3> data) {
        if (view != null){
            view.setGeos(data);
        }
    }


    @Override
    public void setView(UnitsViewImpl view) {
        this.view = view;
    }

    @Override
    public void setVehiclesListToView(List<Unit> unitList) throws IOException {
        if (view != null) {
            view.showProgressDialog();
            view.setUnitList(unitList);
        }
    }

    @Override
    public void setdirectionsToView(List<String> addresList) {
        if (view != null) {
            view.adressList(addresList);
        }
    }

    @Override
    public void failureResponse(String message) {
        view.failureResponse(message);
    }


}
