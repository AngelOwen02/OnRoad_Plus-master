package com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.Interactor.unitsInteractorImplv3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.Interactor.unitsInteractorV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.dataRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.dataresponseUnitsV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitsLegacy.Unitlegacy;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.view.unitsViewv3;

import java.util.List;

public class presenterUnitV3Impl implements presenterUnitV3{
    private Context context;
    private unitsViewv3 view;
    private unitsInteractorV3 interactor;

    public  presenterUnitV3Impl (unitsViewv3 view,Context context){
        this.view=view;
        this.context=context;
        this.interactor=new unitsInteractorImplv3(this,context);
    }
    @Override
    public void AsynckUpdate(List<dataRequest> orderedListAlphanumeric, String origin) {
        if(view!=null) {
            interactor.asyncUpdate(orderedListAlphanumeric,origin);
        }
    }
    @Override
    public void requestVehicles(List<dataRequest> origin) {
        if(view!=null){
            interactor.requestVehicles(origin);
        }
    }
    @Override
    public void getAllVehiclesList() {
        if(view!=null){
            interactor.requestAllvehicles();
        }
    }

    @Override
    public void setUnitsList(List<Unitlegacy> dataUnits) {
        if(view!=null){
            view.unitstoList(dataUnits);
        }
    }



    @Override
    public void setVehicles(List<dataresponseUnitsV3> data) {
        if(view!=null){
            view.setVehicles(data);
        }
    }


}
