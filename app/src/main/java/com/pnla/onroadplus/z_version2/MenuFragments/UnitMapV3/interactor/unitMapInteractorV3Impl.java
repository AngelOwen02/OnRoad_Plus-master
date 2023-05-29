package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.utils.ExternalApiSerice;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.dataVehicleDescV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.requestVehicleDescV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.responseVehicleDescV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.presenter.unitViewpresenterV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.util.serviceVehicleDescription;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitExternalApi;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

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
}
