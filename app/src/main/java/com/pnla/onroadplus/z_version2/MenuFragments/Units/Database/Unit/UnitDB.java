package com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit;


import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class UnitDB {

    public Realm realm;

    public UnitDB(Realm realm) {
        this.realm = realm;
    }

    public static void createNewUnit(boolean vehicleStatus, int cveVehicle, int vehicleSwitch, String vehicleName, String vehicleImage, String sendTime, String descBrand, String descModel, String vehicleYear, String vehicleVin, String vehiclePlate, String georeference, String timeTravel, String timeElapsed, double latitude, double longitude, double mileage, double kmTravel, double currentSpeed, double maxSpeed) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Unit unit = new Unit(vehicleStatus, cveVehicle, vehicleSwitch, vehicleName, vehicleImage, sendTime, descBrand, descModel, vehicleYear, vehicleVin, vehiclePlate, georeference, timeTravel, timeElapsed, latitude, longitude, mileage, kmTravel, currentSpeed, maxSpeed);
        List<Unit> unitList = new ArrayList<>();
        unitList.add(unit);
        realm.copyToRealm(unitList);
        realm.commitTransaction();
    }

    public static List<Unit> getUnitList() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Unit> unitRealmResults = realm.where(Unit.class).findAll();
        List<Unit> unitList = new ArrayList<>();
        for (Unit unit : unitRealmResults) {
            unitList.add(unit);
        }
        return unitList;
    }

    public static List<Unit> getUnitListActive() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Unit> unitRealmResults = realm.where(Unit.class).equalTo("vehicleStatus", true).findAll();
        List<Unit> unitList = new ArrayList<>();
        for (Unit unit : unitRealmResults) {
            unitList.add(unit);
        }
        return unitList;
    }

    public static List<Unit> getUnitListAsync(Realm realmInstance) {
        RealmResults<Unit> unitRealmResults = realmInstance.where(Unit.class).equalTo("vehicleStatus", true).findAll();
        List<Unit> unitList = new ArrayList<>();
        for (Unit unit : unitRealmResults) {
            unitList.add(unit);
        }
        return unitList;
    }

    public static void updateUnits(int id, boolean vehicleStatus, int cveVehicle, int vehicleSwitch, String vehicleName, String vehicleImage, String sendTime, String descBrand, String descModel, String vehicleYear, String vehicleVin, String vehiclePlate, String georeference, String timeTravel, String timeElapsed, double latitude, double longitude, double mileage, double kmTravel, double currentSpeed, double maxSpeed) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Unit unit = realm.where(Unit.class).equalTo("id", id).findFirst();
        ArrayList<Unit> unitList = new ArrayList<>();
        unitList.add(unit);
        for (Unit units : unitList) {
            units.setVehicleStatus(vehicleStatus);
            units.setCveVehicle(cveVehicle);
            units.setVehicleSwitch(vehicleSwitch);
            units.setVehicleName(vehicleName);
            units.setVehicleImage(vehicleImage);
            units.setSendTime(sendTime);
            units.setDescBrand(descBrand);
            units.setDescModel(descModel);
            units.setVehicleVin(vehicleVin);
            units.setVehicleYear(vehicleYear);
            units.setVehiclePlate(vehiclePlate);
            units.setGeoreference(georeference);
            units.setTimeTravel(timeTravel);
            units.setTimeElapsed(timeElapsed);
            units.setLatitude(latitude);
            units.setLongitude(longitude);
            units.setMileage(mileage);
            units.setKmTravel(kmTravel);
            units.setCurrentSpeed(currentSpeed);
            units.setMaxSpeed(maxSpeed);
        }
        realm.commitTransaction();
    }

    public static void deleteDB() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Unit> unitRealmResults = realm.where(Unit.class).findAll();
        unitRealmResults.deleteAllFromRealm();
        realm.commitTransaction();
    }


    public static void updateCheckedStatus(String vehicleName, boolean isChecked) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Unit unit = realm.where(Unit.class).equalTo("vehicleName", vehicleName).findFirst();
        unit.setVehicleStatus(isChecked);
        realm.copyToRealm(unit);
        realm.commitTransaction();
    }

    public static boolean todasneg() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Unit> unitRealmResults = realm.where(Unit.class).equalTo("", "").findAll();
        for (Unit unit : unitRealmResults) {
            if (unit.isVehicleStatus()) {

            }
        }
        return false;
    }

    public static void updateNameSong(int id, String name) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Unit unit = realm.where(Unit.class).equalTo("id", id).findFirst();
        unit.setVehicleName(name);
        realm.commitTransaction();

    }

    public static void updateSendTime(int id, String name) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Unit unit = realm.where(Unit.class).equalTo("id", id).findFirst();
        unit.setSendTime(name);
        realm.commitTransaction();
    }


    public static void updateData(int id, String vehicleName) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        Unit unitData = realm.where(Unit.class).equalTo("id", id).findFirst();
        unitData.setVehicleName(vehicleName);
      /*  unitData.setVehicleImage(unit.getVehicleImage());
        unitData.setSendTime(unit.getSendTime());
        unitData.setDescBrand(unit.getDescBrand());
        unitData.setDescModel(unit.getDescModel());
        unitData.setVehicleVin(unit.getVehicleVin());
        unitData.setVehicleYear(unit.getVehicleYear());
        unitData.setVehiclePlate(unit.getVehiclePlate());
        unitData.setGeoreference(unit.getGeoreference());
        unitData.setTimeTravel(unit.getTimeTravel());
        unitData.setTimeElapsed(unit.getGeoreference());
        unitData.setLatitude(unit.getLatitude());
        unitData.setLongitude(unit.getLongitude());
        unitData.setMileage(unit.getMileage());
        unitData.setKmTravel(unit.getKmTravel());
        unitData.setCurrentSpeed(unit.getCurrentSpeed());
        unitData.setMaxSpeed(unit.getMaxSpeed());*/
        realm.commitTransaction();
    }
}
