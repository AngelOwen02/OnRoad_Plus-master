package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.interactor;

import android.content.Context;

public interface UnitAssignSupportInteractor {

    void getVehiclesAnGroups(Context context);

    void getVehicles(Context context);
    void getGroups(Context context);
    //void onClickGroups();

    void getUnitsfromgroups(Context context);
}
