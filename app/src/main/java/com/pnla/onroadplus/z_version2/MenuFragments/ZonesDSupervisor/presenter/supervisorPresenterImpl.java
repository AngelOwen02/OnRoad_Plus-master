package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.interactor.supervisorInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.interactor.supervisorInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.view.supervisorView;

import java.util.List;

public class supervisorPresenterImpl implements  supervisorPresenter {
    private supervisorView view;
    private supervisorInteractor interactor;
    private Context context;

    public supervisorPresenterImpl(supervisorView view,Context context)
    {
        this.view=view;
        this.context=context;
        this.interactor=new supervisorInteractorImpl(this,context);
    }

    @Override
    public void requestCatalog() {
        if(view!=null)
        {
            interactor.requestEployes();
        }
    }
    @Override
    public void setZones(int zonecveLayer, int newCveEmploye) {
        if(view!=null)
        {
            interactor.setZones(zonecveLayer,newCveEmploye);
        }
    }
    @Override
    public void getDriversCatalog(List<String> tripulantesdata) {
        if(view!=null)
        {
            view.setEmployes(tripulantesdata);
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
    public void restartView() {
        if(view!=null)
        {
            view.restarView();
        }
    }
}
