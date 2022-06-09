package com.pnla.onroadplus.z_version2.realmOnRoad;

import android.content.Context;
import android.content.SharedPreferences;

import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmGroup {

    public static void saveGroups(List<GroupV2> groups, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String user = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);

        /** Primero obtenemos la lista de grupos almacenados en la db,
         *  si es null entonces guardamos toda la lista que llega como parametro,
         *  en caso contrario eliminamos todos los grupos asociados al usuario
         *  y despues guardamos toda la lista
         * */
        Realm realm = Realm.getDefaultInstance();
        RealmResults<GroupV2> realmGroups = realm.where(GroupV2.class).equalTo("userGroup", user).findAll();
        if (realmGroups != null && realmGroups.size() > 0) {
            /** primero eliminamos y posterior guardamos los grupos que llegan como parametro */
            realm.beginTransaction();
            realmGroups.deleteAllFromRealm();
            realm.commitTransaction();
            /**Ahora guardamos los grupos que llegan como parámetro*/
            realm = Realm.getDefaultInstance();
            for (int position = 0; position < groups.size(); position++) {
                if (groups.get(position).isSelected()) {
                    realm.beginTransaction();
                    GroupV2 newGroup = realm.createObject(GroupV2.class, groups.get(position).getCve_vehicle_group());
                    newGroup.setUserGroup(groups.get(position).getUserGroup());
                    newGroup.setDesc_vehicle_group(groups.get(position).getDesc_vehicle_group());
                    newGroup.setVehicle_group(groups.get(position).getVehicle_group());
                    newGroup.setSelected(groups.get(position).isSelected());
                    newGroup.setPositionItem(groups.get(position).getPositionItem());
                    realm.commitTransaction();
                }
            }
            //realm.close();
        } else {
            /**Aqui tenemos que guardar toda la lista que lego como parametro*/
            Realm realm1 = Realm.getDefaultInstance();
            /** Guardamos a cada uno de los grupos de la lista solo si el atributo "select" es true  */
            for (int position = 0; position < groups.size(); position++) {
                if (groups.get(position).isSelected()) {
                    realm1.beginTransaction();
                    GroupV2 newGroup = realm1.createObject(GroupV2.class, groups.get(position).getCve_vehicle_group());
                    newGroup.setUserGroup(groups.get(position).getUserGroup());
                    newGroup.setDesc_vehicle_group(groups.get(position).getDesc_vehicle_group());
                    newGroup.setVehicle_group(groups.get(position).getVehicle_group());
                    newGroup.setSelected(groups.get(position).isSelected());
                    newGroup.setPositionItem(groups.get(position).getPositionItem());
                    realm1.commitTransaction();
                }
            }
            //realm1.close();
        }

    }

    public static List<GroupV2> getAllGroupsByUser(String userName) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<GroupV2> realmGroups = realm.where(GroupV2.class).equalTo("userGroup", userName).findAll();
        List<GroupV2> groups = realm.copyFromRealm(realmGroups);
        return groups;
    }

}
