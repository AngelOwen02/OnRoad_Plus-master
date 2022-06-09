package com.pnla.onroadplus.z_version2.MenuFragments.Zones.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.pointsData;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.pointsInterest;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.pointsRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.pointsResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.visitedData;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.visitedRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.visitedResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.zoneResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.zonesData;
//import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.zonesDatum;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.zonesRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.presenter.zonesPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.util.serviceZones;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.view.zonesFragment;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class zonesInteractorImpl implements  zonesInteractor {

    private Context context;
    private zonesPresenter presenter;
    private Retrofit retrofitClient;
    private serviceZones service;

    private List<Integer> cvesZone = new ArrayList<>();
    private List<String> colorsZones = new ArrayList<>();

    private List<String> zonesAndColors = new ArrayList<>();


    public zonesInteractorImpl(zonesPresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        service = retrofitClient.create(serviceZones.class);
    }


    @Override
    public void getZones() {

        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if (token != null) {
            getZonesdata(token);/** aqui solo obtendremos los siguientes datos   cve_layer & tab_layer_color      */
        }
    }

    @Override
    public void getInterestPoints(List<Integer> cveZonesfullList) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if (token != null) {
            getPointsperZone(cveZonesfullList, token);
        }
    }


    @Override
    public void getpointsStatus(List<Integer> cveZonesfullList) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if (token != null) {
            getvisitedPoints(cveZonesfullList,token);
        }
    }
    private void getZonesdata(String token) {
        zonesRequest request = new zonesRequest(token);
        presenter.showProgressDialog();
        Call<zoneResponse> call = service.getZones(request);
        call.enqueue(new Callback<zoneResponse>() {
            @Override
            public void onResponse(Call<zoneResponse> call, Response<zoneResponse> response) {
                validateCode(response, context);
            }

            @Override
            public void onFailure(Call<zoneResponse> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void validateCode(Response<zoneResponse> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                getzonesalldata(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getzonesalldata(Response<zoneResponse> response, Context context) {
        zoneResponse responseinfo = response.body();
        if (responseinfo != null) {
            String message = responseinfo.getMessage();
            int responseCode = responseinfo.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                Log.e("zonesnames&colors", "" + responseCode + "  " + message);
                zonesData[] zones = responseinfo.getData();

                Log.e("zonesnames&colors", "" + zones);
                if (zones != null)//data
                {
                    //List<zonesData> data=zones.getData();
                    Log.e("zonesnames&colors", "" + zones[0].getCveLayer());
                    int cves;
                    cvesZone.clear();
                    colorsZones.clear();
                    zonesAndColors.clear();
                    String colors;

                    String mixedInfo;

                    for (int i = 0; i < zones.length; i++) {
                        cves = zones[i].getCveLayer();
                        cvesZone.add(cves);

                        colors = zones[i].getTabLayerColor();
                        colorsZones.add(colors);

                        mixedInfo = String.valueOf(zones[i].getCveLayer()) + "|" + zones[i].getTabLayerColor();
                        zonesAndColors.add(mixedInfo);

                    }
                    Log.e("zonesnames&colors", "" + cvesZone);
                    presenter.setDataofZones(zones);
                    presenter.cveZones(cvesZone);
                    presenter.colorZones(colorsZones);
                    presenter.colorAndCvesZones(zonesAndColors);
                   // presenter.hideProgressDialog();
                }
            }
        }


    }

    private void getPointsperZone(List<Integer> cveZonesfullList, String token) {
        pointsRequest request = new pointsRequest(cveZonesfullList, token);
        //aqui deberia ir un loader/
        presenter.showProgressDialog();
        Call<pointsResponse> call = service.getZonepoints(request);
        call.enqueue(new Callback<pointsResponse>() {
            @Override
            public void onResponse(Call<pointsResponse> call, Response<pointsResponse> response) {
                validateCodezonePoints(response, context);
            }

            @Override
            public void onFailure(Call<pointsResponse> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateCodezonePoints(Response<pointsResponse> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                getPointsZoneData(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getPointsZoneData(Response<pointsResponse> response, Context context) {
        pointsResponse responseP=response.body();

        if(responseP!=null)
        {
            int code=responseP.getResponseCode();
            String message=responseP.getMessage();
            if(code==GeneralConstantsV2.RESPONSE_CODE_OK)
            {
                Log.e("pointperZone",code+"  "+ message);
                List<pointsData> data=responseP.getData();
                if(data!=null)
                {
                    Log.e("zonesnames&colors", "static zones value " + zonesFragment.pointInZone);
                    if(zonesFragment.pointInZone==null||zonesFragment.pointInZone.isEmpty()==true) {
                    }else{
                          List<pointsInterest> points= new ArrayList<>();
                        points.clear();
                        List<String> latslongsAlone=new ArrayList<>();
                        latslongsAlone.clear();
                        List<LatLng> pointsdouble=new ArrayList<>();
                         pointsdouble.clear();
                         List<String> dotsCve=new ArrayList<>();
                         dotsCve.clear();
                        for (int i = 0; i < data.size(); i++) {
//                        Log.e("alonePoints", data[i].getCveLayer() + "   " + data[i].getDescLayer() + '\n' + data[i].getLats() + '\n' + data[i].getLngs());
                         //   Log.e("alonePoints", "alonepoints" + data[i].getInterests() + '\n');
                            points= data.get(i).getInterests();
                            if(points!=null)
                            {

                               for(int k=0;k<points.size();k++)
                               {
                                   latslongsAlone.add(points.get(k).getInterestLat()+"|"+ points.get(k).getInterestLon());
                                   LatLng point = new LatLng(points.get(k).getInterestLat(),  points.get(k).getInterestLon());
                                   pointsdouble.add(point);
                                   dotsCve.add(points.get(k).getCveInterestPoints());
                               }

                            }
                        }

                        Log.e("latlongalone", "latspoints "+ latslongsAlone+ '\n');
                        presenter.drawRedDots(dotsCve,pointsdouble);

                    }

                    presenter.setPointperZones(data);
                   //

                }
            }
        }
    }
    private void getvisitedPoints(List<Integer> cveZonesfullList, String token) {
         visitedRequest request = new visitedRequest(cveZonesfullList,token);
         Call<visitedResponse> call= service.getPointsVisited(request);
         call.enqueue(new Callback<visitedResponse>() {
             @Override
             public void onResponse(Call<visitedResponse> call, Response<visitedResponse> response) {
                 validateCodeVisitedPoints(response,context);
             }

             @Override
             public void onFailure(Call<visitedResponse> call, Throwable t) {
                 Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
             }
         });
    }
    private void validateCodeVisitedPoints(Response<visitedResponse> response,Context context) {
        if (response != null) {
            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                getPointsVisited(response,context);

            }else
            {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getPointsVisited(Response<visitedResponse> response, Context context) {
        visitedResponse responseVisited=response.body();
        if (responseVisited!=null)
        {
            int code=responseVisited.getResponseCode();
            String message=responseVisited.getMessage();
            if(code==GeneralConstantsV2.RESPONSE_CODE_OK)
            {
                List<visitedData> dataVisited=responseVisited.getData();
                if(dataVisited!=null)
                {
                    List<List<String>> visited = new ArrayList<List<String>>();
                    List<String> visited2=new ArrayList<>();
                    visited2.clear();
                    visited.clear();
                    for (int i=0;i<dataVisited.size();i++)
                    {
                        Log.e("visited","NOMBRE: "+dataVisited.get(i).getDescLayer()+"  PORCENTAGE: "+dataVisited.get(i).getPercentComplete()
                                +"%  ("+dataVisited.get(i).getPointsVisited()+"/"+dataVisited.get(i).getPointsNotVisited()+")"+'\n') ;// '\n'
                        visited2=dataVisited.get(i).getVisitedpoints();
                        if (visited2.contains("0")) {
                            visited2.remove("0");
                        }
                        visited.add(visited2);

                        Log.e("visitedDots",""+visited);
                        presenter.setGreenDots(visited);
                    }

                    presenter.setPointsVisited(dataVisited);
                }
            }
        }
    }
}