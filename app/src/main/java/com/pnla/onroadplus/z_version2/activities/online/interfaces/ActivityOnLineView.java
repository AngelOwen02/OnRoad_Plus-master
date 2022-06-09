package com.pnla.onroadplus.z_version2.activities.online.interfaces;

import android.graphics.drawable.Drawable;

import androidx.fragment.app.Fragment;

import com.pnla.onroadplus.z_version2.activities.online.menuDrawerV2.menuModelV2.MenuModelV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2;

import java.util.HashMap;
import java.util.List;

public interface ActivityOnLineView {

    void showLoader();

    void hideLoader();

    void showMessage(String message);

    void setUserEmployeeName(String employeeName);

    void setEmployeeImage(String employeeImage);

    void setDefaultEmployeeImage();

    void setMenuIcon(Drawable icon);

    void setDrawerMenu(List<MenuModelV2> headerList, HashMap<MenuModelV2, List<MenuModelV2>> childList);

    void closeDrawerMenu();

    void successCloseSession();

    void showFragmentNotifications(Fragment fragment);

    void showFragmentContact(Fragment fragment);

    void showFragmentConfiguration(Fragment fragment);

    void showFragmentHelp(Fragment fragment);

    void showFragmentMap(Fragment fragment);

    void showFragmentVehicles(Fragment fragment);

    void setGroupsList(List<GroupV2> groups);

}
