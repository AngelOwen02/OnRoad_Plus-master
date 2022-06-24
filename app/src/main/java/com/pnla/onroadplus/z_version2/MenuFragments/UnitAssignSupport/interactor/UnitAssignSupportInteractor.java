package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.interactor;

import android.content.Context;
import java.io.IOException;
import java.util.List;

public interface UnitAssignSupportInteractor {

    void getAllVehiclesFromAPI();
    //void getGroupsVehicles();

    void getGeoreferencefromAPI(List<Integer> cves) throws IOException;
}
