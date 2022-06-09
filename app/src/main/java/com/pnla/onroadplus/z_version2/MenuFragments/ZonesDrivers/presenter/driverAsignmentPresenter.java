package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.presenter;

import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.arrayasignments.VhicleDriver;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.asignment.vehicleZones;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.newUpdate.VehicleDriver;

import java.util.List;

public interface driverAsignmentPresenter {

    void requestAsignment(String asign);
    void requestUnitsCatalog();
    void requesDriverCatalog();
    void updateAsignments( List<vehicleZones> zones);
    void requestTripulantes();
    void setTripulantes(List<VhicleDriver>  myAsignments);
    //requesVehicles();
    //requestDrivers();
    //savesignments();
    void getDriversCatalog(List<String> driversCatalog);
    void CheckDataAsignment(List<String> asingment);


    void showDialog();
    void hideDialog();


    void newUPdate(List<VehicleDriver> vehicleDrivers);
}
