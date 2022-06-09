package com.pnla.onroadplus.z_version2.activities.online.interfaces;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.pnla.onroadplus.z_version2.activities.online.menuDrawerV2.menuModelV2.MenuModelV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;

import java.util.HashMap;
import java.util.List;

public interface ActivityOnLinePresenter {

    void setView(ActivityOnLineView view);

    //Request Data To Interactor
    void getUserDataPreferences(Context context);

    void getMenuIcon();

    void getDrawerMenu(Context context);

    void validateDataToCloseSession(Context context);

    void findFragmentByName(String fragmentName, FragmentManager manager, FragmentTransaction transaction, Context context);

    void validateVehcleList(List<VehicleV2> vehicles, FragmentManager manager, Context context);

    void saveGroups(List<GroupV2> groups, Context context);

    void getGroupsFromAPI(Context context);

    void validateGroupVehicles(List<GroupV2> groups, FragmentManager manager, Context context);

    void validateVehiclesUserClickedDrawerMenu(String groupName, int cveGroup, List<GroupV2> groups, FragmentManager manager, Context context);

    //SetDataToView

    void setCloseDrawerMenu();

    void successCloseSession();

    void setUserEmployeeName(String employeeName);

    void setEmployeeImage(String employeeImage);

    void setDefaultEmployeeImage();

    void setMenuIconToView(Drawable icon);

    void setDrawerMenu(List<MenuModelV2> headerList, HashMap<MenuModelV2, List<MenuModelV2>> childList);

    void setMessageToView(String message);

    void setFragmentNotifications(Fragment fragment);

    void setFragmentContact(Fragment fragment);

    void setFragmentConfiguration(Fragment fragment);

    void setFragmentHelp(Fragment fragment);

    void setFragmentMap(Fragment fragment);

    void setFragmentVehicles(Fragment fragment);

    void setGroupsList(List<GroupV2> groups);

    void hideLoaderFromInteractor();

}
