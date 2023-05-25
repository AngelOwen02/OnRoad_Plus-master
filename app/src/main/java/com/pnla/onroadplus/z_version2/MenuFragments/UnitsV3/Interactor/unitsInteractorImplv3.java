package com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.Interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.dataRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.dataresponseUnitsV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.requestUnitsmodelV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.responseUnitsV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitsLegacy.Unitlegacy;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitsLegacy.dataUnitsLegacy;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitsLegacy.requestUnitsLegacy;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitsLegacy.responseUnitsLegacy;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.presenter.presenterUnitV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.presenter.presenterUnitV3Impl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.util.serviceUnitsLegacy;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.util.serviceUnitsV3;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV3;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class unitsInteractorImplv3 implements unitsInteractorV3{
    private Context context;
    private presenterUnitV3 presenter;
    private serviceUnitsV3 service;
    private serviceUnitsLegacy service2;
    private Retrofit retrofitClient,retrofitClient2;
    public unitsInteractorImplv3(presenterUnitV3Impl presenter, Context context) {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientV3.getRetrofitInstance();
        service=retrofitClient.create(serviceUnitsV3.class);

        retrofitClient2 = RetrofitClientV2.getRetrofitInstance();
        service2 =retrofitClient2.create(serviceUnitsLegacy.class);

    }

    @Override
    public void requestVehicles(List<dataRequest> origin) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null){
            requestAllVehicles(origin);
        }
    }

    @Override
    public void requestAllvehicles() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null){
            requestListVehicles(token);
        }
    }

    @Override
    public void asyncUpdate(List<dataRequest> orderedListAlphanumeric, String origin) {
       GetGeoreferenceAsync getVehicleMarkerAsync = new GetGeoreferenceAsync(orderedListAlphanumeric,origin);
        getVehicleMarkerAsync.execute();
    }
    private class GetGeoreferenceAsync extends AsyncTask<Void, Void, Void> {
        private List<dataRequest> orderedListAlphanumeric;
        private String origin;
        private GetGeoreferenceAsync(List<dataRequest> orderedListAlphanumeric, String origin) {
            this.orderedListAlphanumeric=orderedListAlphanumeric;
            this.origin=origin;
        }
        @Override
        protected Void doInBackground(Void... voids) {
           presenter.getAllVehiclesList();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }

    private void requestAllVehicles(List<dataRequest> origin) {
        requestUnitsmodelV3 request= new requestUnitsmodelV3(origin);
        Call<responseUnitsV3> call= service.getUnitsV3(request);
        call.enqueue(new Callback<responseUnitsV3>() {
            @Override
            public void onResponse(Call<responseUnitsV3> call, Response<responseUnitsV3> response) {
                getUnitsV3(response, context);//todo no usamo nuestra validacion comun ya que no esta estructurado igual este endpoint REVISAR MODEL de response
            }

            @Override
            public void onFailure(Call<responseUnitsV3> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void getUnitsV3(Response<responseUnitsV3> response, Context context) {
        responseUnitsV3 resp=response.body();
        if(resp!=null){
            if(resp.getFound()!=null){
                List<dataresponseUnitsV3> data=resp.getFound();
                if(data!=null){
                    presenter.setVehicles(data);
                }else {
                    Toast.makeText(context, "respuesta sin datos", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(context, "respuesta vacia", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(context, ""+response.message(), Toast.LENGTH_SHORT).show();
        }
    }

    private void requestListVehicles(String token) {
        int tipodeRequest=1;//esto es para traer a todas ya que valor 0 trae solo 30
        List<Integer> noCves=new ArrayList<>();
        requestUnitsLegacy request= new requestUnitsLegacy(token,tipodeRequest,noCves);
        Call<responseUnitsLegacy> call= service2.getFullVehicles(request);
        call.enqueue(new Callback<responseUnitsLegacy>() {
            @Override
            public void onResponse(Call<responseUnitsLegacy> call, Response<responseUnitsLegacy> response) {
                validateCode(response,context);
            }

            @Override
            public void onFailure(Call<responseUnitsLegacy> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void validateCode(Response<responseUnitsLegacy> response, Context context)  {
        //  Log.e("LAPRINCESS", String.valueOf(response.body().getResponseCode()));
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getVehiclesData(response, context);
        } else {
            // presenter.failureResponse(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
            Toast.makeText(context,  "sesion expirada", Toast.LENGTH_LONG).show();
        }
    }

    private void getVehiclesData(Response<responseUnitsLegacy> response, Context context) {
        responseUnitsLegacy resp= response.body();
        if (resp != null) {
            String message = resp.getMessage();
            int responseCode = resp.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                dataUnitsLegacy data =resp.getData();
                if(data!=null){
                List<Unitlegacy > dataUnits=data.getUnitList();
                if(dataUnits!=null){
                    if(dataUnits.isEmpty()){
                        Toast.makeText(context, "No cuentas con Unidades disponibles", Toast.LENGTH_SHORT).show();
                    }else {
                        presenter.setUnitsList(dataUnits);
                    }
                }else {
                    Toast.makeText(context, "No cuentas con Unidades", Toast.LENGTH_SHORT).show();
                }
                }else {
                    Toast.makeText(context,  "Sin datos", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(context,  responseCode, Toast.LENGTH_LONG).show();
            }
        }
    }
}
