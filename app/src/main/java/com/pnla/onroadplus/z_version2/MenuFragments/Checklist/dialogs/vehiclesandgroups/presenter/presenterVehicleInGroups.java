package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.interactor.interactorVehicleinGroups;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.interactor.interactorVehiclesinGroupsImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.model.DataGroups;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.view.dialogViewGroups;

public class presenterVehicleInGroups implements  presenterVehicleInGroupsImpl {
    private dialogViewGroups view;
    private Context context;
    private interactorVehicleinGroups interactor;
    public presenterVehicleInGroups(dialogViewGroups view,Context context){
        this.context=context;
        this.view=view;
        this.interactor= new interactorVehiclesinGroupsImpl(this,context);
    }
    @Override
    public void requestGroups() {
        if(view!=null)
        {
            interactor.getGroups();
        }
    }

    @Override
    public void setGroups(DataGroups data) {
        if(view!=null)
        {
            view.setVehiclesGroups(data);
        }
    }
}
