package com.pnla.onroadplus.z_version2.realmOnRoad;

import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.model.VehicleTracking;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class FinalTrackingDBHelper {
    public Realm realm;
    public FinalTrackingDBHelper(Realm realm) {
        this.realm = realm;
    }

    public static void createVehicleList(String userVehicle, String vehicle_name, String vehicle_image, double latitude,
                                         double longitude, boolean selected, int cve_vehicle, int positionItem, int vehicle_switch){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        VehicleTracking vehicleTracking = new VehicleTracking(userVehicle,vehicle_name,vehicle_image,latitude,longitude,selected,cve_vehicle,positionItem,vehicle_switch);
        List<VehicleTracking> vehicleTrackingList = new ArrayList<>();
        vehicleTrackingList.add(vehicleTracking);
        realm.copyToRealm(vehicleTrackingList);
        realm.commitTransaction();

    }

  /*  public static void deleteDatabase(){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Song> songRealmResults = realm.where(Song.class).findAll();
        songRealmResults.deleteAllFromRealm();
        realm.commitTransaction();
    }*/



    public static List<VehicleTracking> readVehicleList(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<VehicleTracking> realmresults = realm.where(VehicleTracking.class).equalTo("selected",true).findAll();
        ArrayList<VehicleTracking> songArrayList = new ArrayList<>();
        for (VehicleTracking s: realmresults){
            songArrayList.add(s);
        }

        return songArrayList;
    }

    public static void updateAllVehicleList(int id, String userVehicle, String vehicle_name, String vehicle_image, double latitude,
                                            double longitude, boolean selected, int cve_vehicle, int positionItem, int vehicle_switch){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        VehicleTracking vehicleTracking = realm.where(VehicleTracking.class).equalTo("id", id).findFirst();
        ArrayList<VehicleTracking> vehicleTrackingList = new ArrayList<>();
        vehicleTrackingList.add(vehicleTracking);
        for (VehicleTracking vehicles : vehicleTrackingList){
            vehicles.setUserVehicle(userVehicle);
            vehicles.setVehicleName(vehicle_name);
            vehicles.setVehicleImage(vehicle_image);
            vehicles.setLatitude(latitude);
            vehicles.setLongitude(longitude);
            vehicles.setSelected(selected);
            vehicles.setCveVehicle(cve_vehicle);
            vehicles.setPositionItem(positionItem);
            vehicles.setVehicleSwitch(vehicle_switch);
        }
        realm.commitTransaction();
    }

    public static void updateCheckedStatus(int id, boolean isChecked){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        VehicleTracking vehicleTracking = realm.where(VehicleTracking.class).equalTo("id", id).findFirst();
        vehicleTracking.setSelected(isChecked);
        realm.commitTransaction();

    }
}
