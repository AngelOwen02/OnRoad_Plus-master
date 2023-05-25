package com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.presenter;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.dataRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.dataresponseUnitsV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitsLegacy.Unitlegacy;

import java.util.List;

public interface presenterUnitV3 {
    void requestVehicles(List<dataRequest> integer);

    void setVehicles(List<dataresponseUnitsV3> data);

    void getAllVehiclesList();

    void setUnitsList(List<Unitlegacy> dataUnits);

    void AsynckUpdate(List<dataRequest> orderedListAlphanumeric, String origin);
}
