package com.pnla.onroadplus.z_version2.MenuFragments.Units.interactor;

import java.io.IOException;
import java.util.List;

public interface UnitsInteractor {
    void getAllVehiclesFromAPI();
     //void getGroupsVehicles();

    void getGeoreferencefromAPI(List<Integer> cves) throws IOException;
}
