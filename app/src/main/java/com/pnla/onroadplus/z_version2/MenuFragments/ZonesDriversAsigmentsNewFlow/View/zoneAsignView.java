package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.View;

import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model.VehicleDriver;

import java.util.List;

public interface zoneAsignView {
   void setasigments(List<VehicleDriver> myAsignments);
   void setDrivers(List<String> drivers);
   void setDrivers2(List<String> d2);
   void setVehicless(List<String> vehicles);

   void showProgressDialog();
   void hideProgressDialog();

    void setD(List<String> d);

   void setV(List<String> v);

    void restartAfterUpdate();

    void showMessage(String message);
}
