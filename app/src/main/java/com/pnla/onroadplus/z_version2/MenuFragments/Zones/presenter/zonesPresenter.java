package com.pnla.onroadplus.z_version2.MenuFragments.Zones.presenter;

import com.google.android.gms.maps.model.LatLng;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.pointsData;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.visitedData;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.zonesData;

import java.util.List;

public interface zonesPresenter {

    void getZones();
    void requestZonePoints(List<Integer> cveZonesfullList);
    void requestvisitedPoints(List<Integer> cveZonesfullList);
    void setDataofZones(zonesData[] data);
    void setPointperZones( List<pointsData> data);

    void setPointsVisited(List<visitedData> dataVisited);
    void drawRedDots(List<String> dotsCve, List<LatLng> pointsdouble);
    void setGreenDots(List<List<String>> visited);
    void showProgressDialog();
    void hideProgressDialog();



    void cveZones(List<Integer> cvesZone);
    void colorZones(List<String> colorsZones);
    void colorAndCvesZones(List<String> zonesAndColors);



}
