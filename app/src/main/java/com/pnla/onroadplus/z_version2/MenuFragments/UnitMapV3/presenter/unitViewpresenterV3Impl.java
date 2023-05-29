package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.interactor.unitMapInteractorV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.interactor.unitMapInteractorV3Impl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.view.unitMapViewV3;

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
    public void getDataetVehicleDescripcion() {

    }

    @Override
    public void setDataetVehicleDescripcion() {

    }
}
