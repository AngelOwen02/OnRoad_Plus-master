package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.presenter;

import android.content.Context;
import android.util.Log;

import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.interactor.checkAsignement;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.interactor.driverAsignmentInteractorImplements;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.arrayasignments.VhicleDriver;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.asignment.vehicleZones;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.newUpdate.VehicleDriver;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.view.driverAsignmentView;

import java.util.List;

public class driverAsignmentPresenterImpl  implements driverAsignmentPresenter{

    private driverAsignmentView view;
    private Context  context;
    private checkAsignement interactor;

    public driverAsignmentPresenterImpl(driverAsignmentView view,Context  context)
    {
        this.view=view;
        this.context=context;
        this.interactor=new driverAsignmentInteractorImplements(this,context);
    }


    @Override
    public void requestAsignment(String asign) {
        if(view!=null)
        {
            interactor.checkList(asign);
        }

    }

    @Override
    public void requestUnitsCatalog() {
        if(view!=null)
        {
            interactor.requestDrivers();
        }
    }

    @Override
    public void requesDriverCatalog() {
        if(view!=null)
        {
            interactor.requestUnits();
        }
    }

    @Override
    public void updateAsignments(List<vehicleZones> zones) {
        if(view!=null)
        {
            interactor.asigmentDrivers(zones);
            Log.e("updateAsignments",""+zones);
        }
    }

    @Override
    public void requestTripulantes() {
        if(view!=null)
        {
            interactor.requestTripulantes();
        }
    }

    @Override
    public void setTripulantes(List<VhicleDriver>  myAsignments) {
        if(view!=null) {
            view.setTripulantes( myAsignments);
        }
    }
    @Override
    public void getDriversCatalog(List<String> driversCatalog) {
        if(view!=null) {
            view.getdivers(driversCatalog);
        }
    }
    @Override
    public void CheckDataAsignment(List<String> asingment) {
        if(view!=null)
        {
            view.chekcAsignments(asingment);
           // view.hideProgressDialog();
        }
    }



    @Override
    public void showDialog() {
        if(view!=null)
        {
            view.showProgressDialog();
        }
    }

    @Override
    public void hideDialog() {
        if(view!=null)
        {
            view.hideProgressDialog();
        }
    }

    @Override
    public void newUPdate(List<VehicleDriver> vehicleDrivers) {
        if(view!=null)
        {
            interactor.newUpdate(vehicleDrivers);
        }
    }
}
