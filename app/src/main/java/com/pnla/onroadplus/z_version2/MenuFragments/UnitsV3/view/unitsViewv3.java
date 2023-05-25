package com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.view;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.dataresponseUnitsV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitsLegacy.Unitlegacy;

import java.util.List;

public interface unitsViewv3 {

    void setVehicles(List<dataresponseUnitsV3> data);

    void unitstoList(List<Unitlegacy> dataUnits);
}
