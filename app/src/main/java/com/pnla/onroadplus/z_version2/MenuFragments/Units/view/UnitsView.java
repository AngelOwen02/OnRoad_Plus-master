package com.pnla.onroadplus.z_version2.MenuFragments.Units.view;

import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;

import java.io.IOException;
import java.util.List;

public interface UnitsView {

    void setUnitList(List<Unit> vehicles) throws IOException;
    void adressList(List<String> adress);
    void failureResponse(String message);

    void showProgressDialog();

    void hideProgressDialog();
}
