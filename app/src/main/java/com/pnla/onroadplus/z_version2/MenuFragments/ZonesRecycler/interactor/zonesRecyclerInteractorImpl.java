package com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.interactor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.pnla.onroadplus.z_version2.Containers.LoginContainer.view.LoginContainerActivity;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Group.GroupDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.UnitDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.view.zonesFragment;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.pointsData1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.pointsInterest1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.pointsRequest1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.pointsResponse1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.visitedData1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.visitedRequest1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.visitedResponse1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.zoneResponse1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.zonesData1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.zonesRequest1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.zonesVehicles1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.presenter.presenterRecyclerZonesImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.util.serviceRecyclerZones;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmUserData;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class zonesRecyclerInteractorImpl implements  zonesRecyclerInteractor{

    private Context context;
    private presenterRecyclerZonesImpl presenter;

    private Retrofit retrofitClient;
    private serviceRecyclerZones service;


    private List<Integer> cvesZone = new ArrayList<>();
    private List<String> colorsZones = new ArrayList<>();
    private List<String> dayAvalable=new ArrayList<>();

    private List<String> zonesAndColors = new ArrayList<>();

    public static  List<String> adapterCheck=new ArrayList<>();

    public zonesRecyclerInteractorImpl(presenterRecyclerZonesImpl presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        service = retrofitClient.create(serviceRecyclerZones.class);

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
        zonesRequest1 request = new zonesRequest1(token);
        presenter.showProgressDialog();
        Call<zoneResponse1> call = service.getZones(request);
        call.enqueue(new Callback<zoneResponse1>() {
            @Override
            public void onResponse(Call<zoneResponse1> call, Response<zoneResponse1> response) {
                validateCode(response, context);
            }

            @Override
            public void onFailure(Call<zoneResponse1> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void validateCode(Response<zoneResponse1> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                getzonesalldata(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getzonesalldata(Response<zoneResponse1> response, Context context) {
        zoneResponse1 responseinfo = response.body();
        if (responseinfo != null) {
            String message = responseinfo.getMessage();
            int responseCode = responseinfo.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                Log.e("zonesnames&colors", "" + responseCode + "  " + message);
                zonesData1[] zones = responseinfo.getData();

                Log.e("zonesnames&colors", "" + zones);
                if (zones != null)//data
                {
                    List<List<zonesVehicles1>> asignment=new ArrayList<>();

                    //List<zonesData> data=zones.getData();
                    Log.e("zonesnames&colors", "" + zones[0].getCveLayer());
                    int cves;
                    cvesZone.clear();
                    colorsZones.clear();
                    zonesAndColors.clear();
                    dayAvalable.clear();
                    String colors;
                    String daysWeek;
                    String mixedInfo;

                    for (int i = 0; i < zones.length; i++) {
                        cves = zones[i].getCveLayer();
                        cvesZone.add(cves);

                        colors = zones[i].getTabLayerColor();
                        colorsZones.add(colors);

                        mixedInfo = String.valueOf(zones[i].getCveLayer()) + "|" + zones[i].getTabLayerColor();
                        zonesAndColors.add(mixedInfo);

                        daysWeek= zones[i].getDaysAvailable()+"#"+zones[i].getCveLayer();
                        dayAvalable.add(daysWeek);
                        asignment.add( zones[i].getVehicles());

                    }
                    if(asignment!=null)
                    {
                        List<List<zonesVehicles1>> valuesCves= new ArrayList<>();
                        List<String> cvesf=new ArrayList<>();
                        List<List<String>> cvesf2=new ArrayList<>();
                        cvesf2.clear();
                        cvesf.clear();
                        valuesCves.clear();
                        Log.e("volante", ""+ asignment.size());
                        for(int i=0;i<asignment.size();i++)
                        {
                            valuesCves.add(asignment.get(i));
                            int sizeD =asignment.get(i).size();

//
                            for (int k=0;k<sizeD;k++)
                            {   if(asignment.get(i).size()==1&&k==0&&i==0){
                                cvesf.add(""+" Zone "+zones[i].getCveLayer()+" :"+asignment.get(i).get(k).getCveVehicle()+"]");
                                }else if(asignment.get(i).size()==1&&k==0&&i>0&&i!=asignment.size()-1)
                                {
                                cvesf.add("["+" Zone "+zones[i].getCveLayer()+" :"+asignment.get(i).get(k).getCveVehicle()+"]");
                                }else if(asignment.get(i).size()==1&&k==0&&i>0&&i==asignment.size()-1)
                                 {
                                cvesf.add("["+" Zone "+zones[i].getCveLayer()+" :"+asignment.get(i).get(k).getCveVehicle()+"],");
                                }else if(asignment.get(i).size()>1&&k==0&&i==0)
                                {
                                cvesf.add("["+" Zone "+zones[i].getCveLayer()+" :"+asignment.get(i).get(k).getCveVehicle());
                                 }
                                else if(asignment.get(i).size()>1&&k==0){
                                cvesf.add("["+" Zone "+zones[i].getCveLayer()+" :"+asignment.get(i).get(k).getCveVehicle());}
                                else if(asignment.get(i).size()>1&&k==sizeD-1&&i<asignment.size()-1)
                                {
                                cvesf.add(asignment.get(i).get(k).getCveVehicle()+"]");
                                }
                                else if(asignment.get(i).size()>1&&k==sizeD-1&&i==asignment.size()-1)
                                {
                                cvesf.add(asignment.get(i).get(k).getCveVehicle());
                                }
                                else
                                {
                                    cvesf.add(asignment.get(i).get(k).getCveVehicle());
                                }
                            }
                            asignment.get(i);
                        }
                        cvesf2.add(cvesf);
                        String data=String.valueOf( cvesf2.get(0));
                        //Log.e("volante", " ITEMS: "+asignment.size()+"  "+data);
                        //Log.e("volante", ""+cvesf.size()+"  "+cvesf);
                        if(asignment.size()!=0)
                        {

                            adapterCheck.clear();
                            for (int i=0;i<asignment.size();i++)
                            {
                                String[] sections=data.split("],");

                                adapterCheck.add( sections[i]+"]");
                            }/**este Log trae las zonas por cve y los cve de los vehiculos asignados, en caso de contener uno con 0 el volante debe estar en gris*/
                            Log.e("volante", '\n'+" laslist: "+adapterCheck.size()+"   "+adapterCheck);
                        }
//                        String part=data.get(position);
//
                    }

                    Log.e("zonesnames&colors", "" + cvesZone);
                    presenter.setDataofZones(zones);
                    presenter.cveZones(cvesZone);
                    presenter.colorZones(colorsZones);
                    presenter.colorAndCvesZones(zonesAndColors);
                    presenter.setListays(dayAvalable);
                    presenter.setVolante(adapterCheck);
                    // presenter.hideProgressDialog();
                }
            }else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {

                UnitDB.deleteDB();
                GroupDB.deleteDB();
                RealmUserData.deleteDB();

                Bundle bndl = new Bundle();
                bndl.putBoolean("HelpStatus", true);

                Intent intent = new Intent(context, LoginContainerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtras(bndl);
                context.startActivity(intent);



                //   presenter.failureResponse(context.getString(R.string.textSessionExpired));
            }
        }


    }

    private void getPointsperZone(List<Integer> cveZonesfullList, String token) {
        pointsRequest1 request = new pointsRequest1(cveZonesfullList, token);
        //aqui deberia ir un loader/
        presenter.showProgressDialog();
        Call<pointsResponse1> call = service.getZonepoints(request);
        call.enqueue(new Callback<pointsResponse1>() {
            @Override
            public void onResponse(Call<pointsResponse1> call, Response<pointsResponse1> response) {
                validateCodezonePoints(response, context);
            }

            @Override
            public void onFailure(Call<pointsResponse1> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateCodezonePoints(Response<pointsResponse1> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getPointsZoneData(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getPointsZoneData(Response<pointsResponse1> response, Context context) {
        pointsResponse1 responseP=response.body();

        if(responseP!=null)
        {
            int code=responseP.getResponseCode();
            String message=responseP.getMessage();
            if(code==GeneralConstantsV2.RESPONSE_CODE_OK)
            {
                Log.e("pointperZone",code+"  "+ message);
                List<pointsData1> data=responseP.getData();
                if(data!=null)
                {
                    Log.e("zonesnames&colors", "static zones value " + zonesFragment.pointInZone);

                        List<pointsInterest1> points= new ArrayList<>();
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



                    presenter.setPointperZones(data);
                    //

                }
            }
        }
    }
    private void getvisitedPoints(List<Integer> cveZonesfullList, String token) {
        visitedRequest1 request = new visitedRequest1(cveZonesfullList,token);
        Call<visitedResponse1> call= service.getPointsVisited(request);
        call.enqueue(new Callback<visitedResponse1>() {
            @Override
            public void onResponse(Call<visitedResponse1> call, Response<visitedResponse1> response) {
                validateCodeVisitedPoints(response,context);
            }

            @Override
            public void onFailure(Call<visitedResponse1> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void validateCodeVisitedPoints(Response<visitedResponse1> response,Context context) {
        if (response != null) {
            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                getPointsVisited(response,context);

            }else
            {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getPointsVisited(Response<visitedResponse1> response, Context context) {
        visitedResponse1 responseVisited=response.body();
        if (responseVisited!=null)
        {
            int code=responseVisited.getResponseCode();
            String message=responseVisited.getMessage();
            if(code==GeneralConstantsV2.RESPONSE_CODE_OK)
            {
                List<visitedData1> dataVisited=responseVisited.getData();
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

