package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Interactor.zoneAsignInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Interactor.zonesAsignInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model.VehicleDriver;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.View.zoneAsignView;

import java.util.List;

public class zonePresenterImpl implements  zonePresenter {

    private zoneAsignView view;
    private Context context;
    private zonesAsignInteractor interactor;

    public  zonePresenterImpl (zoneAsignView view ,Context context)
    {
        this.view=view;
        this.context=context;
        this.interactor=new zoneAsignInteractorImpl(this,context);
    }

    @Override
    public void requestAsignment(String asign) {
        if(view!=null) {
         interactor.getAsignments(asign);
        }
    }

    @Override
    public void requestUnitsCatalog() {
        if(view!=null) {
            interactor.getFUnits();
        }
    }

    @Override
    public void requesDriverCatalog() {
        if(view!=null) {
        interactor.getFDrivers();
        }
    }

    @Override
    public void updateAsignments(List<VehicleDriver> zones) {
        if(view!=null) {
            interactor.updateFData(zones);
        }
    }
    @Override
    public void auditTrail(String name) {
        if (view != null) {
            interactor.newsetAuditTrail(name);
        }
    }
    @Override
    public void setAsignments(List<VehicleDriver> myAsignments) {
        if(view!=null) {
            view.setasigments(myAsignments);
        }
    }

    @Override
    public void setVehiclesList(List<String> vehicles) {
        if(view!=null) {
            view.setVehicless(vehicles);
        }
    }

    @Override
    public void setDrivers(List<String> drivers) {
        if(view!=null) {
            view.setDrivers(drivers);
        }
    }

    @Override
    public void setV(List<String> V) {
        if(view!=null) {
            view.setV(V);
        }
    }

    @Override
    public void setD(List<String> D) {
        if(view!=null) {
            view.setD(D);
        }
    }

    @Override
    public void setDriversNodefaulValue(List<String> d2) {
        if(view!=null) {
            view.setDrivers2(d2);
        }
    }

    @Override
    public void showDialog() {
        if(view!=null) {
        view.showProgressDialog();
        }
    }

    @Override
    public void hideDialog() {
        if(view!=null) {
        view.hideProgressDialog();
        }
    }

    @Override
    public void restartAfterUpdate() {
        if(view!=null) {
            view.restartAfterUpdate();
        }
    }
    @Override
    public void setMessageToView(String message) {
        if (view != null) {
            view.showMessage(message);
        }
    }
}
