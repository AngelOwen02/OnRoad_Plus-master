package com.pnla.onroadplus.z_version2.MenuFragments.Zones.util;

import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.PositionV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class zoneAlonePoints {
    public static List<TripV2> pointscheckZones(List<TripV2> tripsList) {
        for (int i = 0; i < tripsList.size(); i++) {
            Collections.sort(tripsList.get(i).getPositions(), new Comparator<PositionV2>() {
                @Override
                public int compare(PositionV2 o1, PositionV2 o2) {
                    if (o1.getOrder() > o2.getOrder()) {
                        return 1;
                    } else if (o1.getOrder() < o2.getOrder()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });
        }
        return tripsList;
    }
}
