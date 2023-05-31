package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.interactor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.Containers.LoginContainer.view.LoginContainerActivity;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.Path;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.RequestAPIMAP;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.ResponseAPIMAP;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.tripsBytimeData;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.tripsbyTimeRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.tripsbyTimeResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.utils.ExternalApiSerice;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.dataVehicleDescV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.requestVehicleDescV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.responseVehicleDescV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.presenter.unitViewpresenterV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.util.serviceVehicleDescription;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Group.GroupDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.UnitDB;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripsV2Data;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripsV2Request;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripsV2Response;
import com.pnla.onroadplus.z_version2.fragments.mapV2.utils.MapV2Utils;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmUserData;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitExternalApi;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class unitMapInteractorV3Impl implements unitMapInteractorV3{

    private Context context;
    private unitViewpresenterV3 presenter;
    private serviceVehicleDescription services;
    private ExternalApiSerice externalservice;
    private Retrofit retrofitClient,retrofitExternalapi;

    public unitMapInteractorV3Impl(unitViewpresenterV3 presenter, Context context) {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        services = retrofitClient.create(serviceVehicleDescription.class);

        retrofitExternalapi= RetrofitExternalApi.getApiMap2();
        externalservice=retrofitExternalapi.create(ExternalApiSerice.class);
    }
    //region descripcion header
    @Override
    public void getVehicleDescription(int vehicleCve) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if (token!=null) {
            //Metodo
            requestVehicleDescription(vehicleCve, token);
        }
    }

    private void requestVehicleDescription(int vehicleCve,String token) {
        final requestVehicleDescV3 request = new requestVehicleDescV3(vehicleCve, token);
        Log.e("updateFields"," request: "+vehicleCve+" "+token);
        presenter.showProgressBar();
        services.getVehicleDescription(request).enqueue(new Callback<responseVehicleDescV3>() {
            @Override
            public void onResponse(Call<responseVehicleDescV3> call, Response<responseVehicleDescV3> response) {
                //Validar
                validateCodeVehicleDescription(response, context);
            }

            @Override
            public void onFailure(Call<responseVehicleDescV3> call, Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
                presenter.hideProgressBar();
            }
        });
    }

    private void validateCodeVehicleDescription(Response<responseVehicleDescV3> response, Context context) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getVehicleData(response, context);
        } else {
            Toast.makeText(context, "sesion expirada", Toast.LENGTH_SHORT).show();
            presenter.hideProgressBar();
        }
    }

    private void getVehicleData(Response<responseVehicleDescV3> response, Context context) {
        responseVehicleDescV3 vehicleDescResponse = response.body();
        if (vehicleDescResponse != null) {
            int responseCode = vehicleDescResponse.getResponseCode();
            if(responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                dataVehicleDescV3 data = vehicleDescResponse.getData();
                if(data!=null) {
                    presenter.setDataVehicleDescripcion(data);
                    presenter.hideProgressBar();
                } else {
                    //Error
                    presenter.hideProgressBar();
                }
            } else {
                //Error
                presenter.hideProgressBar();
            }
        } else {
            //Error
            Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
            presenter.hideProgressBar();
        }
    }
    //endregion
    //region minimapas
    @Override
    public void reqEvents(int cveVehicle, String date, Context context) {
        String startUtcDate = MapV2Utils.getUtcStartDate(date);
        String endUtcDate = MapV2Utils.getUtcEndDate(date);
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);

        String validation = MapV2Utils.validateTripsData(token, cveVehicle, startUtcDate, endUtcDate);
        if (validation.equals(GeneralConstantsV2.SUCCESS)) {
            startTripsRequest(token, cveVehicle, startUtcDate, endUtcDate, context);
        } else {
        }
    }



    private void startTripsRequest(String token, int cveVehicle, String startDate, String endDate, Context context) {
        TripsV2Request request = new TripsV2Request(cveVehicle, startDate, endDate, token);
        presenter.showDialog();
        services.getTrips(request).enqueue(new Callback<TripsV2Response>() {
            @Override
            public void onResponse(Call<TripsV2Response> call, Response<TripsV2Response> response) {
                validateTripsCode(response, context);
            }

            @Override
            public void onFailure(Call<TripsV2Response> call, Throwable t) {
                Toast.makeText(context, ""+(RetrofitValidationsV2.getOnFailureResponse(t, context)), Toast.LENGTH_SHORT).show();
                presenter.hideDialog();
            }
        });
    }

    private void validateTripsCode(Response<TripsV2Response> response, Context context) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getTripsData(response, context);
        } else {
            Toast.makeText(context, ""+response.message(), Toast.LENGTH_SHORT).show();
            presenter.hideDialog();
        }
    }

    private void getTripsData(Response<TripsV2Response> response, Context context) {
        TripsV2Response tripsV2Response = response.body();
        if (tripsV2Response != null) {
            int responseCode = tripsV2Response.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                TripsV2Data data = tripsV2Response.getData();
                if (data != null) {
                    List<TripV2> trips = data.getTrips();
                    if (trips != null && trips.size() > 0) {
                        trips = MapV2Utils.orderPositions(trips);
                        trips = MapV2Utils.buildGoogleUrlImage(trips, context);
                        Log.e("tripsroutesF",""+trips.size()+" "+trips);
                        presenter.setEvents(trips);
                        presenter.hideDialog();
                    } else {
                        Toast.makeText(context, "Sin viajes: "+context.getString(R.string.textEmptyTrips), Toast.LENGTH_SHORT).show();
                        presenter.hideDialog();
                    }
                } else {
                    Toast.makeText(context, "Sin datos: "+context.getString(R.string.textEmptyTrips), Toast.LENGTH_SHORT).show();
                    presenter.hideDialog();

                }
            } else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {
                Toast.makeText(context, ""+context.getString(R.string.textSessionExpired), Toast.LENGTH_SHORT).show();
                presenter.hideDialog();
                UnitDB.deleteDB();
                GroupDB.deleteDB();
                RealmUserData.deleteDB();

                Bundle bndl = new Bundle();
                bndl.putBoolean("HelpStatus", true);

                Intent intent = new Intent(context, LoginContainerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtras(bndl);
                context.startActivity(intent);


            } else {
                Toast.makeText(context, ""+ String.valueOf( tripsV2Response.getMessage()), Toast.LENGTH_SHORT).show();
                presenter.hideDialog();

            }
        } else {
            Toast.makeText(context, ""+ context.getString(R.string.textErrorDataEmptyMap), Toast.LENGTH_SHORT).show();
            presenter.hideDialog();

        }
    }

    //endregion
    //region peticion asynk

    @Override
    public void AsyncTaskOne(int vehicleCve, String timeStart, String timeEnd, Context context) {
        presenter.getTripsByTime(vehicleCve,timeStart,timeEnd,context);
    }
    //endregion

    @Override//TODO viajes de todo el dia
    public void getTripsbyTime(int vehicleCve, String startTimer, String timeEnd, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if (token != null){
            String validation = MapV2Utils.validategettripbytinetoRequest(vehicleCve,startTimer,timeEnd,token);
            if (validation.equals(GeneralConstantsV2.SUCCESS)) {
                startvehicleTripbytime(vehicleCve,startTimer,timeEnd,token,context);
            }
            else{
                Toast.makeText(context, ""+validation, Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void startvehicleTripbytime(int vehicleCve, String startTimer, String endtime, String token, Context context) {
        tripsbyTimeRequest request=new tripsbyTimeRequest(vehicleCve,endtime,startTimer,token);//el endpoint esta alrevez
        presenter.showDialog();
        services.gettripsbytime(request).enqueue(new Callback<tripsbyTimeResponse>() {    // esto es tripsbyHour
            @Override
            public void onResponse(Call<tripsbyTimeResponse> call, Response<tripsbyTimeResponse> response) {
                validateTripsbyTimeCode(response,context);
            }
            @Override
            public void onFailure(Call<tripsbyTimeResponse> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            presenter.hideDialog();
            }

        });
    }

    private void validateTripsbyTimeCode(Response<tripsbyTimeResponse> response, Context context) {
        if(RetrofitValidationsV2.checkSuccessCode(response.code())){
            getTipsByTimeData(response,context);
        }
        else{
            Toast.makeText(context, ""+response.message(), Toast.LENGTH_SHORT).show();
            presenter.hideDialog();
        }
    }

    private void getTipsByTimeData(Response<tripsbyTimeResponse> response, Context context) {//TODO en este metodo deberia llegar al set de tripsByday

            tripsbyTimeResponse tripbytimeResponse=response.body();

            if(tripbytimeResponse!=null) {
                int responseCode=tripbytimeResponse.getResponseCode();

                if(responseCode==GeneralConstantsV2.RESPONSE_CODE_OK){
                    tripsBytimeData data=tripbytimeResponse.getData();

                    if(data!=null)
                    {
                        if (!data.getLatitude().isEmpty()){

                            Log.e("dataclocks5",""+data.getGeoreference());
                            String lislats= data.getLatitude();
                            String listLongs=data.getLongitude();
                            String calles=data.getGeoreference();
                            String sendstimes=data.getSendtime();
                            List<String> latitudeList=new ArrayList<>();
                            List<String> longitudeList=new ArrayList<>();
                            List<String> calles1=new ArrayList<>();
                            ////falta agregar punto inicial y punto final
                            if(lislats.split(",").length>50&&lislats.split(",").length==listLongs.split(",").length) {
                                int idslat= lislats.split(",").length/lislats.split(",").length;
                                int idslong= lislats.split(",").length/50;
                                // Log.e("mydaytrips",""+ids);

                                String[] parts1=lislats.split(",");
                                String[] parts2=listLongs.split(",");
                                String[] parts3=calles.split("::");
                                int sizeRepaetlats=lislats.split(",").length-1;
                                int sizeRepaetlongs=listLongs.split(",").length-1;
                                int sizeRepaetCalles=calles.split("::").length-1;
                                for(int i=0;i<lislats.split(",").length; i=i+idslat)
                                {
                                    String partlat=parts1[i];
                                    String parlong=parts2[i];

                                    latitudeList.add(parlong);
                                    longitudeList.add(partlat);
                                }
                                for(int i=0;i<calles.split("::").length; i++){
                                    String calless=parts3[i];
                                    calles1.add(calless);
                                }
                                presenter.setcalles(calles1);
                                presenter.setdatafromlistDayLats(latitudeList);
                                presenter.setdatafromlistDayLongs(longitudeList);
                                presenter.hideDialog();
                            }else
                            {
                                int idslat= lislats.split(",").length/lislats.split(",").length;
                                int idslong= lislats.split(",").length/50;

                                String[] parts1=lislats.split(",");
                                String[] parts2=listLongs.split(",");
                                String[] parts3=calles.split("::");
                                for(int i=0;i<lislats.split(",").length; i=i+idslat)
                                {
                                    String partlat=parts1[i];
                                    String parlong=parts2[i];
                                    latitudeList.add(parlong);
                                    longitudeList.add(partlat);
                                }
                                for(int i=0;i<calles.split("::").length; i++){
                                    String calless=parts3[i];
                                    calles1.add(calless);
                                }
                                presenter.setcalles(calles1);
                                presenter.setdatafromlistDayLats(latitudeList);
                                presenter.setdatafromlistDayLongs(longitudeList);
                                presenter.hideDialog();
                            }
                        }else {
                            presenter.hideDialog();
                        }
                    }else{
                        Toast.makeText(context, ""+context.getString(R.string.textEmptyTrips), Toast.LENGTH_SHORT).show();
                        presenter.hideDialog();
                    }
                }else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {
                    Toast.makeText(context, ""+context.getString(R.string.textSessionExpired), Toast.LENGTH_SHORT).show();

                    UnitDB.deleteDB();
                    GroupDB.deleteDB();
                    RealmUserData.deleteDB();

                    Bundle bndl = new Bundle();
                    bndl.putBoolean("HelpStatus", true);
                    presenter.hideDialog();
                } else {
                    Toast.makeText(context, ""+tripbytimeResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    presenter.hideDialog();
                }
            }else {
                Toast.makeText(context, ""+context.getString(R.string.textErrorDataEmptyMap), Toast.LENGTH_SHORT).show();
                presenter.hideDialog();
            }

    }

    //region externalAPI
    @Override
    public void dataExteralAPI(List<List<Double>> correctedDots) {
        List<List<Double>> points=new ArrayList<>();
        List<Double> dots=new ArrayList<>();
        dots.add(-103.348356);//,20.629786]");
        dots.add(20.629786);
        List<Double> dots2=new ArrayList<>();
        dots2.add(-103.349047);//,20.629786]");
        dots2.add(20.629536);
        // dots.add("[-103.349047,20.629536]");
        points.add(0,dots);
        points.add(1,dots2);
        // Log.e("externalApimaps",""+points);
        RequestAPIMAP requestAPIMAP =new RequestAPIMAP(correctedDots,false,"car",false,false);
        presenter.showDialog();
        externalservice.closerPointsAPI(requestAPIMAP).enqueue(new Callback<ResponseAPIMAP>() {
            @Override
            public void onResponse(Call<ResponseAPIMAP> call, Response<ResponseAPIMAP> response) {
                Log.e("externalApimaps","respuetaA : "+ response.code());
                externalApivoid(response,context);
            }

            @Override
            public void onFailure(Call<ResponseAPIMAP> call, Throwable t) {
                Toast.makeText(context,  ""+t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("externalApimaps","respueta: "+t.getMessage());
                presenter.hideDialog();
            }
        });
    }

    private void externalApivoid(Response<ResponseAPIMAP> response, Context context)
    {
        if(RetrofitValidationsV2.checkSuccessCode(response.code())){
            getExternalApiDataData(response,context);
        }
        else{
            Toast.makeText(context, ""+response.message(), Toast.LENGTH_SHORT).show();
            presenter.hideDialog();
            //Log.e("externalApimaps","respueta2 : "+RetrofitValidationsV2.getErrorByStatus(response.code(),context));
        }
    }

    private void getExternalApiDataData( Response<ResponseAPIMAP> response, Context context)
    {
        ResponseAPIMAP myresponse=response.body();
        List<Path> paths = new ArrayList<>();
        paths=myresponse.getPaths();
        if(paths.size()!=0)
        {
            List<List<Float>> resumeDots=new ArrayList<>();
            resumeDots.clear();

            List<List<Float>> snapedDots=new ArrayList<>();
            snapedDots.clear();
            List<String> xdots=new ArrayList<>();
            List<String> ydots=new ArrayList<>();

            xdots.clear();
            ydots.clear();
            if(paths.get(0).getPoints().getCoordinates().size()!=0)
            {

                for(int i=0; i<paths.get(0).getPoints().getCoordinates().size();i++)
                {
                    resumeDots.add(paths.get(0).getPoints().getCoordinates().get(i));
                }
                for(int j=0; j<paths.get(0).getSnappedWaypoints().getCoordinates().size();j++)
                {
                    snapedDots.add(paths.get(0).getSnappedWaypoints().getCoordinates().get(j));

                    String[] parts=String .valueOf(paths.get(0).getSnappedWaypoints().getCoordinates().get(j)).split(",");
                    String[] partlongitude=parts[0].split("\\[");
                    String[] partlatitude=parts[1].split("\\]");

                    xdots.add(partlatitude[0]);
                    ydots.add(partlongitude[1]);

                }

                Log.e("1externalApimaps"," these are the new dots"+resumeDots);
                Log.e("1externalApimaps"," snapped x dots "+xdots.size()+"   "+ xdots.get(0));
                presenter.tripsBDx2tripsBDy2(xdots,ydots);
                presenter.drawHDdots(resumeDots);
                presenter.hideDialog();
            }else{
                presenter.hideDialog();
            }
        }else{
            presenter.hideDialog();
        }

    }
    //endregion getExternalApiDataData
}
