package com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.Interactor;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.dataRequest;

import java.util.List;

public interface unitsInteractorV3 {
    void requestVehicles(List<dataRequest> origin);

    void requestAllvehicles();

    void asyncUpdate(List<dataRequest> orderedListAlphanumeric, String origin);
}
