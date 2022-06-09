package com.pnla.onroadplus.z_version2.fragments.vehiclesV2.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import java.util.List;

public class VehiclesV2Utils {

    public static List<VehicleV2> setUserSelectedPositionAndImageToEveryVehicle(List<VehicleV2> vehicles, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String user = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);
        for (int i = 0; i < vehicles.size(); i++) {
            vehicles.get(i).setUserVehicle(user);                               /** Seteamos al usuario */
            vehicles.get(i).setSelected(false);                                 /** Seteamos selected   */
            vehicles.get(i).setPositionItem(i);                                 /** Seteamos posición   */
            if (vehicles.get(i).getVehicleImage() == null) {                   /** Seteamo "No_Image"  */
                vehicles.get(i).setVehicleImage(GeneralConstantsV2.NO_IMAGE);
            }
        }
        return vehicles;
    }

    public static List<VehicleV2> getSelectedVehicles(List<VehicleV2> vehiclesWebService, List<VehicleV2> realmVehicles) {
        if (vehiclesWebService.size() == realmVehicles.size() || vehiclesWebService.size() < realmVehicles.size()) {
            for (int i = 0; i < realmVehicles.size(); i++) {
                int realmCveVehicle = realmVehicles.get(i).getCveVehicle();
                /**Buscamos el vehículo y le seteamos el estado en caso de encontrarlo*/
                for (int j = 0; j < vehiclesWebService.size(); j++) {
                    if (vehiclesWebService.get(j).getCveVehicle() == realmCveVehicle) {
                        vehiclesWebService.get(j).setSelected(realmVehicles.get(i).isSelected());
                        break;
                    }
                }
            }
        } else if (vehiclesWebService.size() > realmVehicles.size()) {
            for (int i = 0; i < realmVehicles.size(); i++) {
                int realmCveVehicle = realmVehicles.get(i).getCveVehicle();
                /**Buscamos el vehículo y le seteamos el estado en caso de encontrarlo*/
                for (int j = 0; j < vehiclesWebService.size(); j++) {
                    if (vehiclesWebService.get(j).getCveVehicle() == realmCveVehicle) {
                        vehiclesWebService.get(j).setSelected(realmVehicles.get(i).isSelected());
                        break;
                    }
                }
            }
        }
        return vehiclesWebService;
    }

    //public static RealmList<GroupV2> setUserSelectedPositionToEveryGroup(RealmList<GroupV2> groups, Context context) {
    //    SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
    //    String user = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);
    //    for (int i = 0; i < groups.size(); i++) {
    //        groups.get(i).setUserGroup(user);       /**Seteamos usuario*/
    //        groups.get(i).setSelected(false);       /**Seteamos selected*/
    //        groups.get(i).setPositionItem(i);       /**Seteamos posicion*/
    //    }
    //    return groups;
    //}

    public static List<GroupV2> setUserSelectedPositionToEveryGroup(List<GroupV2> groups, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String user = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);
        for (int i = 0; i < groups.size(); i++) {
            groups.get(i).setUserGroup(user);       /**Seteamos usuario*/
            groups.get(i).setSelected(false);       /**Seteamos selected*/
            groups.get(i).setPositionItem(i);       /**Seteamos posicion*/
        }
        return groups;
    }

    public static List<GroupV2> getSelectedGroups(List<GroupV2> groupsWebService, List<GroupV2> realmGroups) {
        if (groupsWebService.size() == realmGroups.size() || groupsWebService.size() < realmGroups.size()) {
            for (int i = 0; i < realmGroups.size(); i++) {
                int cveGroup = realmGroups.get(i).getCve_vehicle_group();
                /**Buscamos el grupo y le seteamos el estado en caso de encontrarlo*/
                for (int j = 0; j < groupsWebService.size(); j++) {
                    if (groupsWebService.get(j).getCve_vehicle_group() == cveGroup) {
                        groupsWebService.get(j).setSelected(realmGroups.get(i).isSelected());
                        break;
                    }
                }
            }
        } else if (groupsWebService.size() > realmGroups.size()) {

            for (int i = 0; i < realmGroups.size(); i++) {
                int realmCveVehicle = realmGroups.get(i).getCve_vehicle_group();
                /**Buscamos el grupo y le seteamos el estado en caso de encontrarlo*/
                for (int j = 0; j < groupsWebService.size(); j++) {
                    if (groupsWebService.get(j).getCve_vehicle_group() == realmCveVehicle) {
                        groupsWebService.get(j).setSelected(realmGroups.get(i).isSelected());
                        break;
                    }
                }
            }
        }
        return groupsWebService;
    }

}
