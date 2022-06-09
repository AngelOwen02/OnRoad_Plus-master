package com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.presenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.view.UnitTrackingView;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.Group;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;

import java.util.List;

public interface UnitTrackingPresenter {
    void setView(UnitTrackingView view);

    //requestDataToInteractor
    void getVehicles(Context context);

    void getGroups(Context context);
    void getVehiclesinGroups(Context context);
    void doiteverytime();
    //setDataToView
    void showProgressBar();

    void hideProgressBar();

    void showErrorMessage(String message);

    void setMessageToView(String message);

    void setVehicleList(List<Unit> vehicles);

    void setGroupsList(List<Group> groups);

    void sessionExpired(String message);

    void hideUnitRV();

    void showUnitRV();

    void hideGroupRV();

    void showGroupRV();


    void hideEmptyGroupsImage();

    void showEmptyGroupsImage();
}
