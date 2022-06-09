package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.view;

import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.arrayasignments.VhicleDriver;

import java.util.List;

public interface driverAsignmentView {
    void chekcAsignments(List<String> asingment);
    void getdivers(List<String> driversCatalog);
    void showProgressDialog();

    void hideProgressDialog();



    void setTripulantes(List<VhicleDriver> myAsignments);
}
