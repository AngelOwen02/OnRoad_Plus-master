package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.view.*;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.interactor.*;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.model.*;

import java.util.List;

public class DialogsPresenterImpl implements DialogsPresenter {

    private Context context;
    private DialogsView view;
    private DialogsInteractor interactor;

    public DialogsPresenterImpl(DialogsView view, Context context) {
        this.view = view;
        this.context = context;
        this.interactor = new DialogsInteractorImpl(this, context);
    }

    @Override
    public void requestVehicles() {
        if(view!=null){
            interactor.requestVehicles();
        }
    }

    @Override
    public void setVehicles(List<DialogsData> data) {
        if(view!=null){
            view.setVehicles(data);
        }
    }

    @Override
    public void showDialog() {
        if(view!=null) {
            view.showDialog();
        }
    }

    @Override
    public void hideDialog() {
        if(view!=null) {
            view.hideProgressDialog();
        }
    }
}
