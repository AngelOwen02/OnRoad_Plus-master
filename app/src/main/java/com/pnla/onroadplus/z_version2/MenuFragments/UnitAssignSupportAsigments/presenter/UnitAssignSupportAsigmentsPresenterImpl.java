package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.interactor.UnitAssignSupportAsigmentsInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.interactor.UnitAssignSupportAsigmentsInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.view.UnitAssignSupportAsigmentsView;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SingleSupportUnitData;

import java.util.List;

public class UnitAssignSupportAsigmentsPresenterImpl implements UnitAssignSupportAsigmentsPresenter {

    private Context context;
    private UnitAssignSupportAsigmentsView view;
    private UnitAssignSupportAsigmentsInteractor interactor;

    public UnitAssignSupportAsigmentsPresenterImpl(UnitAssignSupportAsigmentsView view, Context context){
        this.view = view;
        this.context = context;
        interactor = new UnitAssignSupportAsigmentsInteractorImpl(this, context);
    }

    @Override
    public void setSoportes(List<SingleSupportUnitData> data) {
        if(view!=null){
            view.setSoportes(data);
        }
    }

    @Override
    public void getSoportes(int cveLayer) {
        if(view!=null){
            interactor.requestSoportes(cveLayer);
        }
    }
}
