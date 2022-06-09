package com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.presenter;

import com.google.android.gms.maps.model.LatLng;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.pointsData1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.visitedData1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.zonesData1;

import java.util.List;

public interface presenterRecyclerZones {
    void getZones();
    void requestZonePoints(List<Integer> cveZonesfullList);
    void requestvisitedPoints(List<Integer> cveZonesfullList);
    void setDataofZones(zonesData1[] data);
    void setPointperZones( List<pointsData1> data);

    void setPointsVisited(List<visitedData1> dataVisited);
    void drawRedDots(List<String> dotsCve, List<LatLng> pointsdouble);
    void setGreenDots(List<List<String>> visited);
    void showProgressDialog();
    void hideProgressDialog();



    void cveZones(List<Integer> cvesZone);
    void colorZones(List<String> colorsZones);
    void colorAndCvesZones(List<String> zonesAndColors);

    void setListays(List<String> dayAvalable);

    void setVolante(List<String> adapterCheck);
}
