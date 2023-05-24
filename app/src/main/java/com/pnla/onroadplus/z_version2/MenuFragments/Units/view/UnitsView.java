package com.pnla.onroadplus.z_version2.MenuFragments.Units.view;

import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.dataresponseUnitsV3;

import java.io.IOException;
import java.util.List;

public interface UnitsView {

    void setUnitList(List<Unit> vehicles) throws IOException;
    void adressList(List<String> adress);
    void failureResponse(String message);

    void showProgressDialog();

    void hideProgressDialog();

    void setGeos(List<dataresponseUnitsV3> data);
}
