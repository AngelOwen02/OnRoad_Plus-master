package com.pnla.onroadplus.z_version2.fragments.vehiclesV2.interfaces;

import android.content.Context;

import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;

import java.util.List;

public interface VehiclesV2Presenter {

    void setView(VehiclesV2View view);

    //requestDataToInteractor
    void getVehiclesAndGroups(Context context);

    //setDataToView
    void hideLoaderFromInteractor();

    void showLoaderFromInteractor();

    void setMessageToView(String message);

    void setVehicleList(List<VehicleV2> vehicles);

    void setGroupsList(List<GroupV2> groups);

    void sessionExpired(String message);

}
