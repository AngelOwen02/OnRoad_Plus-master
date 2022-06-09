package com.pnla.onroadplus.z_version2.MenuFragments.Zones.presenter;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.interactor.zonesInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.interactor.zonesInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.pointsData;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.visitedData;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.zonesData;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.view.zonesView;

import java.util.List;

public class zonesPresenterImpl implements  zonesPresenter {


    private Context context;
    private zonesView view;
    private zonesInteractor interactor;

    public zonesPresenterImpl(zonesView view, Context context) {
        this.view = view;
        this.context = context;
        this.interactor = new zonesInteractorImpl(this, context);
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
    public void setDataofZones(zonesData[] zones) {
        if (view != null) {
            view.getZonesNamesAndColors(zones);
        }
    }

    @Override
    public void setPointperZones( List<pointsData> data) {
        if(view!=null) {
            view.setPointsPerZone(data);
        }
    }

    @Override
    public void setPointsVisited(List<visitedData> dataVisited) {
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

}

