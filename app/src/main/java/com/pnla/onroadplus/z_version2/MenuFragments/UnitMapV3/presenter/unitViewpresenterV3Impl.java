package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.interactor.unitMapInteractorV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.interactor.unitMapInteractorV3Impl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.dataVehicleDescV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.view.unitMapViewV3;

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
    public void getDataetVehicleDescripcion() {

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
            view.VehicleDescriptionSucess();
        }
    }
}
