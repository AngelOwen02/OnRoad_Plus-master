package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.pnla.onroadplus.z_version2.MenuFragments.Login.model.AuditTrail;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.model.Zone;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.model.requestDriversBypos;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.model.responseSetZones;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.model.setZone;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.presenter.supervisorPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.utils.service;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.drivers.driversNames;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.drivers.requestDrivers;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.drivers.responsDrivers;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;
import com.pnla.onroadplus.z_version2.MenuFragments.Login.model.*;

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

    public  supervisorInteractorImpl(supervisorPresenter presenter , Context context) {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        service = retrofitClient.create(service.class);
    }

    //region requestEployes
    @Override
    public void requestEployes() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if (token != null) {
            setDriversVehicles(token);
        }
    }
    //endregion requestEployes

    //region setDriversVehicles
    private void setDriversVehicles(String token) {
        requestDriversBypos request = new requestDriversBypos(true, token,1);
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
    //endregion setDriversVehicles

    //region validateCodeDrivers
    private void validateCodeDrivers(Response<responsDrivers> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                catalogDrivers(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }
    //endregion validateCodeDrivers

    //region catalogDrivers
    private void catalogDrivers(Response<responsDrivers> response, Context context) {
        responsDrivers responsD = response.body();
        if (responsD != null) {
            int code = responsD.getResponseCode();
            String message = responsD.getMessage();
            List<String> tripulantesdata = new ArrayList<>();
            tripulantesdata.clear();
            if (code == GeneralConstantsV2.RESPONSE_CODE_OK) {
                List<driversNames> data = responsD.getData();
                namesDrivers.clear();
                for (int i = 0; i < data.size(); i++) {
                    namesDrivers.add(data.get(i).getEmployeeName() + "/" + data.get(i).getCveEmployee());
                    Log.e("catalogD", "" + data.get(i).getEmployeeName() + "/" + data.get(i).getCveEmployee());
                    tripulantesdata.add(data.get(i).getEmployeeName() + "/" + data.get(i).getCveEmployee());
                }
                namesDrivers.add(0, "Elige una opción...");
                //  presenter.hideDialog();

                presenter.getDriversCatalog(namesDrivers);//tripulantesdata //puedes intercambiar si es que quieres que te salga un valor default o no
                //Log.e("tripulantes","  data names  "+namesDrivers);
            }

        }
    }
    //endregion catalogDrivers

    //region setZones
    @Override
    public void setZones(int zonecveLayer, int newCveEmploye) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if (token != null) {
            requestSetZones(zonecveLayer, token, newCveEmploye);
            Log.e("zonesnames&colors15", "token " + token);
        }
    }
    //endregion setZones

    //region requestSetZones
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
    //endregion requestSetZones

    //region validateCodeSetZones
    private void validateCodeSetZones(Response<responseSetZones> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                setZonesResp(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }
    //endregion validateCodeSetZones

    //region setZonesResp
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
    //endregion setZonesResp

    //region newsetAuditTrail
    @Override
    public void newsetAuditTrail(String log) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null) {
            myauditTrail(log, token);
        }
    }
    //endregion newsetAuditTrail

    public void newsetAuditTrail2(String log) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null) {
            myauditTrail2(log, token);
        }
    }

    public void newsetAuditTrail3(String log) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null) {
            myauditTrail3(log, token);
        }
    }

    //region myauditTrail
    private void myauditTrail(String log, String token) {
        AuditTrail mynewAuditTrail = new AuditTrail("Onroad_Asignaciones", "Agregar supervisor", ""+log);
        setAuditTrail request = new setAuditTrail(mynewAuditTrail, token);
        service.auditTrail(request).enqueue(new Callback<responseAuditTrail>() {
            @Override
            public void onResponse(Call<responseAuditTrail> call, Response<responseAuditTrail> response) {
                validateCodeauditTrail(response,context);
            }

            @Override
            public void onFailure(Call<responseAuditTrail> call, Throwable t) {
                Toast.makeText(context, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    //endregion myauditTrail

    private void myauditTrail2(String log, String token) {
        AuditTrail mynewAuditTrail = new AuditTrail("Onroad_Asignaciones", "Eliminar supervisor", ""+log);
        setAuditTrail request = new setAuditTrail(mynewAuditTrail, token);
        service.auditTrail(request).enqueue(new Callback<responseAuditTrail>() {
            @Override
            public void onResponse(Call<responseAuditTrail> call, Response<responseAuditTrail> response) {
                validateCodeauditTrail(response,context);
            }

            @Override
            public void onFailure(Call<responseAuditTrail> call, Throwable t) {
                Toast.makeText(context, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void myauditTrail3(String log, String token) {
        AuditTrail mynewAuditTrail = new AuditTrail("Onroad_Asignaciones", "Editar supervisor", ""+log);
        setAuditTrail request = new setAuditTrail(mynewAuditTrail, token);
        service.auditTrail(request).enqueue(new Callback<responseAuditTrail>() {
            @Override
            public void onResponse(Call<responseAuditTrail> call, Response<responseAuditTrail> response) {
                validateCodeauditTrail(response,context);
            }

            @Override
            public void onFailure(Call<responseAuditTrail> call, Throwable t) {
                Toast.makeText(context, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //region validateCodeauditTrail
    private void validateCodeauditTrail(Response<responseAuditTrail> response, Context context) {
        if(RetrofitValidationsV2.checkSuccessCode(response.code())){
            responseSetAuditTrail(response,context);
        } else {
            Toast.makeText(context,response.code(),Toast.LENGTH_SHORT).show();
        }
    }
    //endregion validateCodeauditTrail

    //region validateCodeauditTrail
    private void responseSetAuditTrail(Response<responseAuditTrail> response, Context context) {
        responseAuditTrail auditResponse = response.body();
        if(auditResponse!=null){
            int responseCode = auditResponse.getResponseCode();
            String message = auditResponse.getMessage();
            if(responseCode==GeneralConstantsV2.RESPONSE_CODE_OK){
                //NADAAAAAAAAAA
            }
        }
    }
    //endregion validateCodeauditTrail
}
