package com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.view;

import com.google.android.gms.maps.model.LatLng;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.pointsData1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.visitedData1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.zonesData1;

import java.util.List;

public interface ZonesViewR {



    void hideProgressDialog();

    void getZonesNamesAndColors(zonesData1[] zones);

    void setPointsVisited(List<visitedData1> dataVisited);
    void showProgressDialog();

    void setZonesAndColors(List<String> zonesAndColors);

    void setPointsPerZone( List<pointsData1> data);

    void setColorofZones(List<String> colorsZones);
    void draRedDots(List<String> dotsCve,List<LatLng> pointsdouble);
    void setGreenDots(List<List<String>> visited);

    void setDayofweek(List<String> dayAvalable);

    void setColorVolante(List<String> adapterCheck);
}
