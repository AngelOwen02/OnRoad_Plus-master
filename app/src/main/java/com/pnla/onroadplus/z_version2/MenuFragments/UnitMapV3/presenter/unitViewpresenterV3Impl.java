package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.interactor.unitMapInteractorV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.interactor.unitMapInteractorV3Impl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.dataVehicleDescV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.view.unitMapViewV3;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;

import java.util.List;

public class unitViewpresenterV3Impl implements unitViewpresenterV3{
    private Context context;
    private unitMapViewV3 view;
    private unitMapInteractorV3 interactor;
    public unitViewpresenterV3Impl(unitMapViewV3 view,Context context){
        this.view=view;
        this.context=context;
        this.interactor=new unitMapInteractorV3Impl(this,context);

    }

    @Override
    public void setDataetVehicleDescripcion(int vehicleCve) {
        if(view!=null) {
            interactor.getVehicleDescription(vehicleCve);
        }
    }

    @Override
    public void setDataVehicleDescripcion(dataVehicleDescV3 data) {
        if(view!=null) {
            view.VehicleDescriptionSucess(data);
        }
    }


    @Override
    public void AsyncTaskOne(int vehicleCve, String sendime, String sendime1, Context context) {
        if(view!=null) {
            interactor.AsyncTaskOne(vehicleCve,sendime,sendime1,context);
        }

    }

    @Override
    public void getTripsByTime(int vehicleCve, String sendime, String sendime1, Context context) {
        if(view!=null) {

            interactor.getTripsbyTime(vehicleCve,sendime,sendime1,context);
        }
    }

    @Override
    public void getEvents(int vehicleCve, String currentDate, Context context) {
        if(view!=null) {
            interactor.reqEvents(vehicleCve,currentDate,context);
        }
    }

    @Override
    public void setEvents(List<TripV2> trips) {
        if(view!=null) {
            view.setEvents(trips);
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
    public void showProgressBar() {
        if(view!=null) {
            view.showProgressBar();
        }
    }

    @Override
    public void hideProgressBar() {
        if(view!=null) {
            view.hideProgressBar();
        }
    }

}
