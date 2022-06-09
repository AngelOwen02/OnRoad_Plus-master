package com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.presenter;


import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.interactor.zonesRecyclerInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.interactor.zonesRecyclerInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.pointsData1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.visitedData1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.zonesData1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.view.ZonesViewR;

import java.util.List;

public class presenterRecyclerZonesImpl implements  presenterRecyclerZones {


    private Context context;
    private ZonesViewR view;
    private zonesRecyclerInteractor interactor;

    public presenterRecyclerZonesImpl(ZonesViewR view, Context context) {
        this.view = view;
        this.context = context;
        this.interactor = new zonesRecyclerInteractorImpl(this, context);
    }

    @Override
    public void getZones() {
        if (view != null) {
            interactor.getZones();
        }
    }

    @Override
    public void requestZonePoints(List<Integer> cveZonesfullList) {
        if (view != null) {
            interactor.getInterestPoints(cveZonesfullList);
        }
    }

    @Override
    public void requestvisitedPoints(List<Integer> cveZonesfullList) {
        if (view != null) {
            interactor.getpointsStatus(cveZonesfullList);
        }
    }

    @Override
    public void setDataofZones(zonesData1[] zones) {
        if (view != null) {
            view.getZonesNamesAndColors(zones);
        }
    }

    @Override
    public void setPointperZones( List<pointsData1> data) {
        if(view!=null) {
            view.setPointsPerZone(data);
        }
    }

    @Override
    public void setPointsVisited(List<visitedData1> dataVisited) {
        if (view != null) {
            view.setPointsVisited(dataVisited);
        }
    }

    @Override
    public void drawRedDots(List<String> dotsCve, List<LatLng> pointsdouble) {
        if (view != null) {
            view.draRedDots(dotsCve,pointsdouble);

        }
    }

    @Override
    public void setGreenDots(List<List<String>> visited) {
        if (view != null) {
            view.setGreenDots(visited);
        }
    }

    @Override
    public void showProgressDialog() {
        view.showProgressDialog();
    }

    @Override
    public void hideProgressDialog() {
        view.hideProgressDialog();
    }

    @Override
    public void cveZones(List<Integer> cvesZone) {
        if (view != null) {
            //view.setcveZones
        }
    }

    @Override
    public void colorZones(List<String> colorsZones) {
        if (view != null) {
            view.setColorofZones(colorsZones);
        }
    }

    @Override
    public void colorAndCvesZones(List<String> zonesAndColors) {
        if (view != null) {
            view.setZonesAndColors(zonesAndColors);
        }
    }

    @Override
    public void setListays(List<String> dayAvalable) {
        if(view!=null)
        {
            view.setDayofweek(dayAvalable);
        }
    }

    @Override
    public void setVolante(List<String> adapterCheck) {
        if(view!=null)
        {
            view.setColorVolante(adapterCheck);
        }
    }
}
