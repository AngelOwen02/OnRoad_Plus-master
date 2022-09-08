package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Interactor.checkListInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Interactor.checkListInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.checkListView;

public class checkListPresenterImpl implements  checkListPresenter{

    private Context context;
    private checkListView view;
    private checkListInteractor interactor;

    public checkListPresenterImpl(checkListView view,Context context) {
        this.view=view;
        this.context=context;
        this.interactor=new checkListInteractorImpl(this,context);
    }

    @Override
    public void requestChecklist() {
        if(view!=null) {
            interactor.requestCheckList();
        }
    }

    @Override
    public void setChecList() {
        if(view!=null) {
            view.setCheckList();
        }
    }
}
