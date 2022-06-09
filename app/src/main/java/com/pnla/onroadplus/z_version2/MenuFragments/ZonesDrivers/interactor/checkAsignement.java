package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.interactor;

import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.asignment.vehicleZones;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.newUpdate.VehicleDriver;

import java.util.List;

public interface checkAsignement {

    void checkList(String asigment);
    void requestDrivers();
    void requestUnits();


    void asigmentDrivers( List<vehicleZones> zones);

    void requestTripulantes();
    //requesVehicles();
    //requestDrivers();
    void savesignments();

    void newUpdate(List<VehicleDriver> vehicleDrivers);
}
