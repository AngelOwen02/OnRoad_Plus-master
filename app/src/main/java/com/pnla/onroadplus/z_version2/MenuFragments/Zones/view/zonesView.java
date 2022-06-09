package com.pnla.onroadplus.z_version2.MenuFragments.Zones.view;

import com.google.android.gms.maps.model.LatLng;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.pointsData;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.visitedData;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.zonesData;

import java.util.List;

public interface zonesView {

    void method();

    void hideProgressDialog();

    void getZonesNamesAndColors(zonesData[] zones);

    void setPointsVisited(List<visitedData> dataVisited);
    void showProgressDialog();

    void setZonesAndColors(List<String> zonesAndColors);

    void setPointsPerZone( List<pointsData> data);

    void setColorofZones(List<String> colorsZones);
    void draRedDots(List<String> dotsCve,List<LatLng> pointsdouble);
    void setGreenDots(List<List<String>> visited);



}
