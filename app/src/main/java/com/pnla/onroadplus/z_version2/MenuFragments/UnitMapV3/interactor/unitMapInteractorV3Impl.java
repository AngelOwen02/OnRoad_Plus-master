package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.interactor;

import android.content.Context;
<<<<<<< HEAD
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
=======
import android.content.SharedPreferences;
>>>>>>> 2881a57b5c1602b01222a7427cac33a6b81b399f
import android.widget.Toast;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.Containers.LoginContainer.view.LoginContainerActivity;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.tripsbyTimeRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.tripsbyTimeResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.utils.ExternalApiSerice;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.dataVehicleDescV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.requestVehicleDescV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.responseVehicleDescV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.presenter.unitViewpresenterV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.util.serviceVehicleDescription;
<<<<<<< HEAD
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Group.GroupDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.UnitDB;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripsV2Data;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripsV2Request;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripsV2Response;
import com.pnla.onroadplus.z_version2.fragments.mapV2.utils.MapV2Utils;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmUserData;
=======
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
>>>>>>> 2881a57b5c1602b01222a7427cac33a6b81b399f
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitExternalApi;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> 2881a57b5c1602b01222a7427cac33a6b81b399f
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

    @Override
    public void getVehicleDescription(int vehicleCve) {
        //todo LUIS en esta clase hereda el token del user y el cve comprobando antes el token
        //todo llegar hasta el consumo del endpoint y colocar el metodo setDataDescriptionVehicle en la View
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if (token!=null) {
            //Metodo
            requestVehicleDescription(vehicleCve, token);
        }
    }

    private void requestVehicleDescription(int vehicleCve,String token) {
        final requestVehicleDescV3 request = new requestVehicleDescV3(vehicleCve, token);
        services.getVehicleDescription(request).enqueue(new Callback<responseVehicleDescV3>() {
            @Override
            public void onResponse(Call<responseVehicleDescV3> call, Response<responseVehicleDescV3> response) {
                //Validar
                validateCodeVehicleDescription(response, context);
            }

            @Override
            public void onFailure(Call<responseVehicleDescV3> call, Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateCodeVehicleDescription(Response<responseVehicleDescV3> response, Context context) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getVehicleData(response, context);
        } else {
            Toast.makeText(context, "sesion expirada", Toast.LENGTH_SHORT).show();
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
                } else {
                    //Error
                }
            } else {
                //Error
            }
        } else {
            //Error
            Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

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

    @Override
    public void AsyncTaskOne(int vehicleCve, String datealternative, String timeStart, Context context) {
        presenter.getTripsByTime(vehicleCve,datealternative,timeStart,context);
    }

    @Override
    public void getTripsbyTime(int vehicleCve, String startTimer, String endtime, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if (token != null){
            String validation = MapV2Utils.validategettripbytinetoRequest(vehicleCve,endtime,startTimer,token);
            if (validation.equals(GeneralConstantsV2.SUCCESS)) {
                startvehicleTripbytime(vehicleCve,startTimer,endtime,token,context);
            }
            else{
                Toast.makeText(context, ""+validation, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startvehicleTripbytime(int vehicleCve, String startTimer, String endtime, String token, Context context) {
        tripsbyTimeRequest request=new tripsbyTimeRequest(vehicleCve,startTimer,endtime,token);
        services.gettripsbytime(request).enqueue(new Callback<tripsbyTimeResponse>() {    // esto es tripsbyHour


            @Override
            public void onResponse(Call<tripsbyTimeResponse> call, Response<tripsbyTimeResponse> response) {
                validateTripsbyTimeCode(response,context);
            }

            @Override
            public void onFailure(Call<tripsbyTimeResponse> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
              //  presenter.hideDialog();
            }

        });
    }

    private void validateTripsbyTimeCode(Response<tripsbyTimeResponse> response, Context context) {
        if(RetrofitValidationsV2.checkSuccessCode(response.code())){
            getTipsByTimeData(response,context);
        }
        else{
            Toast.makeText(context, ""+response.message(), Toast.LENGTH_SHORT).show();
            //  presenter.hideDialog();
        }
    }

    private void getTipsByTimeData(Response<tripsbyTimeResponse> response, Context context) {//TODO en este metodo deberia llegar al set de tripsByday
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
                        Log.e("tripsroutes",""+trips);
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
}
