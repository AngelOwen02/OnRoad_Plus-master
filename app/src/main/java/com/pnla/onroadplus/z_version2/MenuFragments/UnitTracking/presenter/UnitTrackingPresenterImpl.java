package com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.interactor.UnitTrackingInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.interactor.UnitTrackingInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.view.UnitTrackingView;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.Group;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;

import java.util.List;

public class UnitTrackingPresenterImpl implements UnitTrackingPresenter {

    private UnitTrackingView view;
    private UnitTrackingInteractor interactor;

    public UnitTrackingPresenterImpl() {
        interactor = new UnitTrackingInteractorImpl(this);
    }

    @Override
    public void setView(UnitTrackingView view) {
        this.view = view;
    }

    @Override
    public void getVehicles(Context context) {
        if (view != null) {
            view.showProgressBar();
            interactor.getVehicles(context);
        }
    }

    @Override
    public void getGroups(Context context) {
        if (view != null) {
            view.showProgressBar();
            interactor.getGroups(context);
        }
    }

    @Override
    public void getVehiclesinGroups(Context context) {/** este metodo es para probar requesto por toogles*/
        if (view != null) {

           // interactor.getGroups(context);
            interactor.getUnitsfromgroups(context);
         }
    }

    @Override
    public void doiteverytime() {
        if (view != null) {
            view.vehiclesinsiderequest();
        }
    }


    @Override
    public void showProgressBar() {
        if (view != null) {
            view.showProgressBar();
        }
    }

    @Override
    public void hideProgressBar() {
        if (view != null) {
            view.hideProgressBar();
        }
    }

    @Override
    public void showErrorMessage(String message) {
        if (view != null) {
            view.showErrorMessage(message);
        }
    }


    @Override
    public void setMessageToView(String message) {
        if (view != null) {
            //view.hideLoader();
            //view.showMessage(message);
        }
    }

    @Override
    public void setVehicleList(List<Unit> vehicles) {
        if (view != null) {
            view.hideProgressBar();
            view.fillVehiclesList(vehicles);
        }
    }

    @Override
    public void setGroupsList(List<Group> groups) {
        if (view != null) {
            view.hideProgressBar();
            view.fillGroupsList(groups);
        }
    }



    @Override
    public void sessionExpired(String message) {
        if (view != null) {
            //view.hideLoader();
            //view.sessionExpired(message);
        }
    }

    @Override
    public void hideUnitRV() {
        if (view != null) {
            view.hideUnitRV();
        }
    }

    @Override
    public void showUnitRV() {
        if (view != null) {
            view.showUnitRV();
        }
    }

    @Override
    public void hideGroupRV() {
        if (view != null) {
            view.hideGroupRV();
        }
    }

    @Override
    public void showGroupRV() {
        if (view != null) {
            view.showGroupRV();
        }
    }

    @Override
    public void hideEmptyGroupsImage() {
        if (view != null) {
            view.hideEmptyGroupsImage();
        }
    }

    @Override
    public void showEmptyGroupsImage() {
        if (view != null) {
            view.showEmptyGroupsImage();
        }
    }
}
