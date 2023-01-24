package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.interactor.UnitAssignSupportInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.interactor.UnitAssignSupportInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.view.UnitAssignSupportView;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.view.UnitAssignSupportViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.interactor.UnitTrackingInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.interactor.UnitTrackingInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.view.UnitTrackingView;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.Group;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SupportUnitData;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.view.UnitsViewImpl;

import java.io.IOException;
import java.util.List;

public class UnitAssignSupportPresenterImpl implements UnitAssignSupportPresenter {

    private Context context;
    private UnitAssignSupportView view;
    private UnitAssignSupportInteractor interactor;

    public UnitAssignSupportPresenterImpl(Context context) {
        this.context = context;
        interactor = new UnitAssignSupportInteractorImpl(this, context);
    }

    @Override
    public void setView(UnitAssignSupportView view) {
        this.view = view;
    }

    @Override
    public void requestVehicles() {
        if(view!=null){
            interactor.requestSoportes();
        }
    }

    @Override
    public void setSoportes(List<SupportUnitData> data) {
        if(view!=null){
            //view.showProgressDialog();
            view.setSoportes(data);
        }
    }

    @Override
    public void showProgressDialog() {
        if (view != null){
            view.showProgressDialog();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (view != null){
            view.hideProgressDialog();
        }
    }

    @Override
    public void goBackMap() {
        if (view != null){
            view.returnToMap();
        }
    }

    @Override
    public void failureResponse(String message) {
        view.failureResponse(message);
    }
}
