package com.pnla.onroadplus.z_version2.MenuFragments.Zones.interactor;

import java.util.List;

public interface zonesInteractor {
     void getZones();
     void getInterestPoints(List<Integer> cveZonesfullList);
     void getpointsStatus(List<Integer> cveZonesfullList);
}
