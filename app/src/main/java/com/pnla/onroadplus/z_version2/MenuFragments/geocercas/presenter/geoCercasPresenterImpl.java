package com.pnla.onroadplus.z_version2.MenuFragments.geocercas.presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.geocercas.interactor.geCercasInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.geocercas.interactor.geoCercasInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.geocercas.model.Data;
import com.pnla.onroadplus.z_version2.MenuFragments.geocercas.model.Geofences;
import com.pnla.onroadplus.z_version2.MenuFragments.geocercas.view.geoCercasView;

import java.util.List;

public class geoCercasPresenterImpl implements geoCercasPresenter {

    private Context context;
    private geoCercasView view;
    private geCercasInteractor interactor;

    public geoCercasPresenterImpl(geoCercasView view,Context context)
    {
        this.view=view;
        this.context=context;
        this.interactor= new geoCercasInteractorImpl(this,context);
    }



    @Override
    public void getGeoCercas() {
        if(view!=null){
            interactor.geoCercasRequest();

        }
    }

    @Override
    public void showProgressDialog() {
        view.showProgressDialog();
    }

    @Override
    public void hideProgressDialog() {
        view.hideProgressDialog();
    }

    @Override
    public void setGeoCercass(List<Geofences> geoCercas) {
        if(view!=null){


        }
    }

    @Override
    public void setDataofGeozones(List<Data> data) {
        if(view!=null){

            view.data(data);
        }
    }
}
