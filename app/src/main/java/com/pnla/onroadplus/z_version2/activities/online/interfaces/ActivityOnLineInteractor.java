package com.pnla.onroadplus.z_version2.activities.online.interfaces;

import android.content.Context;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;

import java.util.List;

public interface ActivityOnLineInteractor {
    void getUserDataPreferences(Context context);

    void getMenuIcon();

    void getDrawerMenu(Context context);

    void validateDataToCloseSession(Context context);

    void findFragmentByName(String fragmentName, FragmentManager manager, FragmentTransaction transaction, Context context);

    void validateVehcleList(List<VehicleV2> vehicles, FragmentManager manager, Context context);

    void validateGroupVehicles(List<GroupV2> groups, FragmentManager manager, Context context);

    void validateVehiclesUserClickedDrawerMenu(String groupName, int cveGroup, List<GroupV2> groups, FragmentManager manager, Context context);

    void saveGroups(List<GroupV2> groups, Context context);

    void getGroupsFromAPI(Context context);

}
