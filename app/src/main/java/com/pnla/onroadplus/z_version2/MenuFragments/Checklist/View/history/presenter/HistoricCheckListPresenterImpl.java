package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.historicChecklitView;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.interactor.HistoricCheckListInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.interactor.HistoricCheckListInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.model.Historic;

import java.util.List;

public class HistoricCheckListPresenterImpl implements HistoricCheckListPresenter {

    private Context context;
    private historicChecklitView view;
    private HistoricCheckListInteractor interactor;

    public HistoricCheckListPresenterImpl(Context context) {
        this.context = context;
        interactor = new HistoricCheckListInteractorImpl(this, context);
    }

    @Override
    public void setView(historicChecklitView view) {
        this.view = view;
        view.showProgressDialog();
    }

    @Override
    public void requestHistoric() {
        if(view!=null) {
            interactor.requestHistoric();
        }
    }

    @Override
    public void setHistoric(List<Historic> data) {
        if(view!=null) {
            view.setHistoric(data);
        }
    }

    @Override
    public void failureResponse(String message) {
        view.failureResponse(message);
    }

    @Override
    public void showProgressDialog() {
        if(view!=null) {
            view.showProgressDialog();
        }
    }

    @Override
    public void hideProgressDialog() {
        if(view!=null) {
            view.hideProgressDialog();
        }
    }
}
