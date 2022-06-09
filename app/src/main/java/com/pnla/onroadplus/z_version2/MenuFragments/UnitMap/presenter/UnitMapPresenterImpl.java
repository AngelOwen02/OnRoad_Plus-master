package com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.presenter;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.interactor.UnitMapInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.interactor.UnitMapInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.TripsByDay;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.view.UnitMapView;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.datesList.DateV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.vehicleDescription.VehicleDescriptionData;

import java.text.ParseException;
import java.util.List;

public class UnitMapPresenterImpl implements UnitMapPresenter {

    private Context context;

    private UnitMapView view;
    private UnitMapInteractor interactor;


    public UnitMapPresenterImpl(UnitMapView view, Context context){
        this.context = context;
        this.view = view;
        interactor = new UnitMapInteractorImpl(this,context);
    }



    @Override
    public void putVehicleMarkerInMap(double lat, double lng, String title, String image,int vehicleSwitch) {
        if (view != null){
            interactor.vehicleMarkerSetup(lat,lng,title, image, vehicleSwitch);
        }
    }

    @Override
    public void vehicleMarkerSetup(MarkerOptions markerOptions) {
        if (view != null){
            view.setMarkerOptions(markerOptions);
        }
    }

    @Override
    public void zoomVehicleInMap(double lat, double lng) {
        if (view != null){
            interactor.zoomToVehicle(lat, lng);
        }
    }

    @Override
    public void zoomVehicleSetup(LatLng latLng, float zoom) {
        if (view != null){
            view.setZoomOptions(latLng, zoom);
        }
    }

    @Override
    public void showProgressDialog() {
        if (view != null){
            view.showProgressDialog();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (view != null){
            view.hideProgressDialog();
        }
    }

    @Override
    public void getTripsByDate(int cveVehicle, String date, Context context) {
        if (view != null) {
            //view.showLoader();
            interactor.getTripsByDate(cveVehicle, date, context);
        }
    }

    @Override
    public void getTripsByDay(int vehicleCve, String sendime, Context context) {
        if(view!=null)
        {
            interactor.getTripbyDay(vehicleCve,sendime,context);
        }
    }

    @Override
    public void getTripsByTime(int vehicleCve, String sendime, String sendime1, Context context) {
        if(view!=null) {

        interactor.getTripsbyTime(vehicleCve,sendime,sendime1,context);
        }
    }

    @Override
    public void getVehicleDescription(int vehicleCve, Context context) {
        if (view != null) {
            interactor.getVehicleDescription(vehicleCve, context);
        }
    }


    @Override
    public void getCurrentDateTrip() {
        if (view != null) {
            interactor.getCurrentDate();
        }
    }

    @Override
    public void setErrorMessage(String message) {
        if (view != null) {
            view.setErrorMessage(message);
        }
    }

    @Override
    public void stophandlers() {
        if(view!=null)
        {
            view.stopallhandlers();
        }
    }

    @Override
    public void setDatesToView(List<DateV2> dates) {
        if (view != null) {
            view.fillDatesList(dates);
        }
    }

    @Override
    public void setTripsToView(List<TripV2> trips) {
        if (view != null) {
            //view.hideLoader();
            view.fillTripsToView(trips);
        }
    }

    @Override
    public void setTripsBydayToView(List<TripsByDay> tripsbyday) {
        if (view != null) {
            view.fillTripsbydayToView(tripsbyday);
        }
    }

    @Override
    public void setCurrentDate(String currentDate) {
        if (view != null) {
            view.setCurrentDate(currentDate);
        }
    }

    @Override
    public void setdatafromlistDayLats(List<String> data) {
        if (view != null) {
        view.fillStringTipsbyDaylat(data);
        }
    }

    @Override
    public void setdatafromlistDayLongs(List<String> data) {
        if (view != null) {
            view.fillStringTipsbyDaylong(data);
        }
    }

    @Override
    public void setcalles(List<String> calles) {//calles
        if (view != null) {
            view.fillStringcalles(calles);
        }

    }

    @Override
    public void setVehicleDescriptionToView(VehicleDescriptionData data) {
        if (view != null) {
            try {
                view.fillVehicleDescription(data);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getDates() {
        if (view != null) {
            interactor.getDates();
        }
    }

    @Override
    public void getexternalApi(List<List<Double>> correctedDots) {
        if (view != null) {
        interactor.dataExteralAPI( correctedDots);
        }
    }

    @Override
    public void drawHDdots(List<List<Float>> resumeDots) {
        if (view != null) {
            view.drawResumeDots(resumeDots);
        }
    }

    @Override
    public void tripsBDx2tripsBDy2(List<String> xdots, List<String> ydots) {
        if (view != null) {
            view.drawtripdbxbdy(xdots,ydots);
        }
    }


}
