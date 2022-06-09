package com.pnla.onroadplus.z_version2.fragments.vehiclesV2.presenters;

import android.content.Context;

import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.interactors.VehicleV2InteractorImpl;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.interfaces.VehiclesV2Interactor;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.interfaces.VehiclesV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.interfaces.VehiclesV2View;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;

import java.util.List;

public class VehiclesV2PresenterImpl implements VehiclesV2Presenter {

    private VehiclesV2View view;
    private VehiclesV2Interactor interactor;

    public VehiclesV2PresenterImpl() {
        interactor = new VehicleV2InteractorImpl(this);
    }

    @Override
    public void setView(VehiclesV2View view) {
        this.view = view;
    }

    @Override
    public void getVehiclesAndGroups(Context context) {
        if (view != null) {
            view.showLoader();
            interactor.getVehiclesAnGroups(context);
        }
    }

    @Override
    public void hideLoaderFromInteractor() {
        if (view != null) {
            view.hideLoader();
        }
    }

    @Override
    public void showLoaderFromInteractor() {
        if (view != null) {
            view.showLoader();
        }
    }

    @Override
    public void setMessageToView(String message) {
        if (view != null) {
            view.hideLoader();
            view.showMessage(message);
        }
    }

    @Override
    public void setVehicleList(List<VehicleV2> vehicles) {
        if (view != null) {  //el que cierra el loader es el método de grupos que se encuentra abajo
            view.hideLoader();
            view.fillVehiclesList(vehicles);
        }
    }

    @Override
    public void setGroupsList(List<GroupV2> groups) {
        if (view != null) {
            view.hideLoader();
            view.fillGroupsList(groups);
        }
    }

    @Override
    public void sessionExpired(String message) {
        if (view != null) {
            view.hideLoader();
            view.sessionExpired(message);
        }
    }

}
