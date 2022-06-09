package com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.interactor;

import java.util.List;

public interface zonesRecyclerInteractor {
    void getZones();
    void getInterestPoints(List<Integer> cveZonesfullList);
    void getpointsStatus(List<Integer> cveZonesfullList);
}
