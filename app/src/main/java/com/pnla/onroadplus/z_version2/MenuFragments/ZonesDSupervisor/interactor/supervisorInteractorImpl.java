package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.model.Zone;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.model.responseSetZones;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.model.setZone;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.presenter.supervisorPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.utils.service;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.drivers.driversNames;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.drivers.requestDrivers;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.drivers.responsDrivers;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class supervisorInteractorImpl implements supervisorInteractor {
    private supervisorPresenter presenter;
    private Context context;

    private Retrofit retrofitClient;
    private service service;
    public static List<String> namesDrivers=new ArrayList<>();
    public  supervisorInteractorImpl(supervisorPresenter presenter , Context context)
    {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        service = retrofitClient.create(service.class);
    }

    @Override
    public void requestEployes() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if (token != null) {
            setDriversVehicles(token);
        }
    }
    private void setDriversVehicles(String token) {
        requestDrivers request = new requestDrivers(true, token);
        presenter.showDialog();
        Call<responsDrivers> call = service.getDriversCatalog(request);
        call.enqueue(new Callback<responsDrivers>() {
            @Override
            public void onResponse(Call<responsDrivers> call, Response<responsDrivers> response) {
                validateCodeDrivers(response, context);
            }

            @Override
            public void onFailure(Call<responsDrivers> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
        private void validateCodeDrivers(Response<responsDrivers> response, Context context)    {
            if (response != null) {

                if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                    catalogDrivers(response, context);
                } else {
                    Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
                }
            }
        }

        private void catalogDrivers(Response<responsDrivers> response, Context context) {
            responsDrivers responsD=response.body();
            if(responsD!=null)
            {
                int code=responsD.getResponseCode();
                String message=responsD.getMessage();
                List<String> tripulantesdata=new ArrayList<>();
                tripulantesdata.clear();
                if(code==GeneralConstantsV2.RESPONSE_CODE_OK)
                {
                    List<driversNames> data=responsD.getData();
                    namesDrivers.clear();
                    for (int i=0;i<data.size();i++)
                    {
                        namesDrivers.add(data.get(i).getEmployeeName()+"/"+data.get(i).getCveEmployee());
                        Log.e("catalogD",""+data.get(i).getEmployeeName()+"/"+data.get(i).getCveEmployee());
                        tripulantesdata.add(data.get(i).getEmployeeName()+"/"+data.get(i).getCveEmployee());
                    }
                    namesDrivers.add(0,"Elige una opción...");
                  //  presenter.hideDialog();

                    presenter.getDriversCatalog(namesDrivers);//tripulantesdata //puedes intercambiar si es que quieres que te salga un valor default o no
                    //Log.e("tripulantes","  data names  "+namesDrivers);
                }

            }
        }
                    
        @Override
    public void setZones(int zonecveLayer, int newCveEmploye) {
            SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
            String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
            if (token != null) {
                requestSetZones(zonecveLayer,token,newCveEmploye);
                Log.e("zonesnames&colors15","token "+token);
            }
    }

    private void requestSetZones(int zonecveLayer, String token, int newCveEmploye) {
        Zone asignacionNueva=new Zone(newCveEmploye);
        setZone request=new setZone(zonecveLayer,token,asignacionNueva);
        presenter.showDialog();
        Call<responseSetZones> call=service.setZonez(request);
        call.enqueue(new Callback<responseSetZones>() {
            @Override
            public void onResponse(Call<responseSetZones> call, Response<responseSetZones> response) {
                validateCodeSetZones(response,context);
            }

            @Override
            public void onFailure(Call<responseSetZones> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateCodeSetZones(Response<responseSetZones> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                setZonesResp(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setZonesResp(Response<responseSetZones> response, Context context) {
        responseSetZones resp=response.body();
        if(resp!=null)
        {
            int responsecode=resp.getResponseCode();
            String message=resp.getMessage();
            if(responsecode==GeneralConstantsV2.RESPONSE_CODE_OK)
            {
               // presenter.restartView();
                presenter.hideDialog();

            }
        }
    }
}
