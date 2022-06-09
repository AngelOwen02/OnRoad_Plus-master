package com.pnla.onroadplus.z_version2.activities.online.utils;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.view.FragmentVehiclesV2;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.realm.RealmList;

public class ActivityOnLineUtils {

    public static String validateTokenCloseSession(String token) {
        if (token == null || token.length() == 0) {
            return GeneralConstantsV2.FAILTURE;
        } else {
            return GeneralConstantsV2.SUCCESS;
        }
    }

    public static boolean areThereSelectedVehicles(List<VehicleV2> vehicles) {
        boolean validation = false;
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).isSelected()) {
                validation = true;
                break;
            }
        }
        return validation;
    }

    public static boolean areThereSelectedGroups(List<GroupV2> groups) {
        boolean hasSelectedGroups = false;
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).isSelected()) {
                hasSelectedGroups = true;
                break;
            }
        }
        return hasSelectedGroups;
    }

    public static List<VehicleV2> getVehiclesFromSelectedGroups(List<GroupV2> groups) {
        List<VehicleV2> vehicles = new ArrayList<>();

        /**Obtenemos los vehiculos de todos los grupos*/
        if (groups != null && groups.size() > 0) {
            for (int i = 0; i < groups.size(); i++) {
                if (groups.get(i).isSelected()) {
                    List<VehicleV2> groupVehicles = groups.get(i).getVehicles();
                    if (groupVehicles != null && groupVehicles.size() > 0) {
                        for (int j = 0; j < groupVehicles.size(); j++) {
                            vehicles.add(groupVehicles.get(j));
                        }
                    }
                }
            }
        }

        /**Una unidad se puede repetir en diferentes grupos, por esa razon debemos filtrar y no
         * repetir alguna unidad*/
        List<VehicleV2> vehicleListSuccess = new ArrayList<>();
        if (vehicles != null && vehicles.size() > 0) {
            HashMap<Integer, VehicleV2> map = new HashMap<Integer, VehicleV2>();
            for (VehicleV2 vehicle : vehicles) {
                map.put(vehicle.getCveVehicle(), vehicle);
            }

            /**iterar el map para obtener el resultado filtrado*/
            Set<Map.Entry<Integer, VehicleV2>> set = map.entrySet();
            int i = 0;
            for (Map.Entry<Integer, VehicleV2> entry : set) {
                entry.getValue().setPositionItem(i);
                vehicleListSuccess.add(entry.getValue());
                i++;
            }
        }

        return vehicleListSuccess;
    }

    public static String getJsonStringFromVehicleList(List<VehicleV2> vehicleV2s) {
        String jsonString = null;

        if (vehicleV2s != null && vehicleV2s.size() > 0) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<VehicleV2>>() {
            }.getType();
            jsonString = gson.toJson(vehicleV2s, type);
        }
        return jsonString;
    }

    public static List<GroupV2> getGroupsList(RealmList<GroupV2> realmList) {
        List<GroupV2> groups = new ArrayList<>();
        for (int i = 0; i < realmList.size(); i++) {
            GroupV2 group = realmList.get(i);
            groups.add(group);
        }
        return groups;
    }

    public static List<VehicleV2> findVehiclesByGroupName(String groupName, List<GroupV2> groups) {
        List<VehicleV2> vehicles = new ArrayList<>();
        for (int i = 0; i < groups.size(); i++) {
            String currentGroupName = groups.get(i).getVehicle_group();
            if (currentGroupName.equals(groupName)) {
                vehicles = groups.get(i).getVehicles();
                break;
            }
        }
        return vehicles;
    }

    public static boolean userIsInFragmentVehiclesV2(FragmentManager manager) {
        int count = manager.getBackStackEntryCount();
        Log.e("TAG", "::: " + count);
        Fragment currentFragment = manager.findFragmentByTag(FragmentVehiclesV2.TAG);
        if (currentFragment != null) {
            currentFragment = null;
            return true;
        } else {
            currentFragment = null;
            return false;
        }
    }

}
