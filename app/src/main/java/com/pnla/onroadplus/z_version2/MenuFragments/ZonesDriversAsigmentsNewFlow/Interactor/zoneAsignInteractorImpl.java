package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Interactor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.pnla.onroadplus.z_version2.Containers.LoginContainer.view.LoginContainerActivity;
import com.pnla.onroadplus.z_version2.MenuFragments.Login.model.AuditTrail;
import com.pnla.onroadplus.z_version2.MenuFragments.Login.model.responseAuditTrail;
import com.pnla.onroadplus.z_version2.MenuFragments.Login.model.setAuditTrail;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Group.GroupDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.UnitDB;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.drivers.driversNames;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.drivers.requestDrivers;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.drivers.responsDrivers;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.vehicles.UnitsCves;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.vehicles.requestUnits;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.vehicles.responseUnits;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model.Datum;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model.VehicleDriver;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model.VehicleDriver2;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model.asigmentRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model.asignmentResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model.requestUpdate;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model.responseUpdate;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Presenter.zonePresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Presenter.zonePresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.utils.asignmentService;
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

public class zoneAsignInteractorImpl implements  zonesAsignInteractor{

    private Context context;
    private Retrofit retrofitClient;
    private zonePresenter presenter;
    private asignmentService service;
    public List<String> Vehicles=new ArrayList<>();
    public List<String> namesDrivers=new ArrayList<>();
    public static List<String> mynamesDrivers=new ArrayList<>();
    public static List<String> mynamesVehicles=new ArrayList<>();
    private  int Mcvelayer;

    public zoneAsignInteractorImpl(zonePresenterImpl zonePresenter, Context context) {
        this.presenter=zonePresenter;
        this.context=context;
        retrofitClient= RetrofitClientV2.getRetrofitInstance();
        service= retrofitClient.create(asignmentService.class);
    }
     //region getAllAsignments
    @Override
    public void getAsignments(String CveLayer) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            Log.e("tripuFlow","responseCode :  "+token);
            getAsignmentsenpoint(CveLayer,token);
        }
    }


    private void getAsignmentsenpoint(String cveLayer,String token)
    {   int cvelay=Integer.valueOf(cveLayer);
        Mcvelayer=cvelay;
        asigmentRequest request=new asigmentRequest(cvelay,token);
        presenter.showDialog();
        Call<asignmentResponse> call=service.getAsignments(request);
        call.enqueue(new Callback<asignmentResponse>() {
            @Override
            public void onResponse(Call<asignmentResponse> call, Response<asignmentResponse> response) {
                validateCodeResponse(response,context);
            }

            @Override
            public void onFailure(Call<asignmentResponse> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void validateCodeResponse(Response<asignmentResponse> response,Context context)
    {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                    showAsigments(response,context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();

            }
        }
    }
    private void showAsigments(Response<asignmentResponse> response,Context context)
    {
        asignmentResponse respn=response.body();
        if(response!=null)
        {
            int code= respn.getResponseCode();
            String message=respn.getMessage();
            List<Datum> data=respn.getData();
            if(code==GeneralConstantsV2.RESPONSE_CODE_OK)
            {
                Log.e("tripulantesnewFLOW","responseCode :  "+message);
                if(data!=null)
                {
                    List<VehicleDriver> myAsignments=new ArrayList<>();
                    myAsignments.clear();
                    for(int i=0;i<data.size();i++)
                    {
                        data.get(i).getCveLayer();/**este es el cve layer no interesa mucho*/
                        myAsignments.addAll( data.get(i).getVehicleDrivers());/**al parecer este ya agrego toda la data*/
                    }
                   presenter.setAsignments(myAsignments);
                   //presenter.hideDialog();
                }
            } else if (code == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {

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
//endregion

     //region getFull units
    @Override
    public void getFUnits() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            getallUnits(token);
        }
    }
    private void getallUnits(String token)
    {
        requestUnits request= new requestUnits(true,token);
        presenter.showDialog();
        Call<responseUnits> call=service.getUnisCatalog(request);
        call.enqueue(new Callback<responseUnits>() {
            @Override
            public void onResponse(Call<responseUnits> call, Response<responseUnits> response) {
                validateCodeUnits(response,context);
            }

            @Override
            public void onFailure(Call<responseUnits> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void validateCodeUnits( Response<responseUnits> response,Context context)
    {
        if (response != null) {

         if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
         catalogUnits(response, context);
         } else {
         Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
         }
         }
    }
    private void catalogUnits(Response<responseUnits> response, Context context) {
        responseUnits responsU=response.body();
        if(responsU!=null)
        {
            int code=responsU.getResponseCode();
            String message=responsU.getMessage();
            if(code==GeneralConstantsV2.RESPONSE_CODE_OK)
            {
                List<UnitsCves> data=responsU.getData();
                List<String> v=new ArrayList<>();
                v.clear();
                Vehicles.clear();
                for(int i=0;i<data.size();i++)
                {
                    Vehicles.add(data.get(i).getVehicleName());
                    Log.e("catalogD",""+data.get(i).getVehicleName());
                    v.add(data.get(i).getVehicleName()+"/"+data.get(i).getCveVehicle());
                }
                Vehicles.add(0,"Selecciona un vehículo");
                mynamesVehicles=Vehicles;
                presenter.setV(v);
                //presenter.hideDialog();
                presenter.setVehiclesList(Vehicles);
               // presenter.hideDialog();
            }
        }
        }


    //endregion

     //region getFull Drivers
    @Override
    public void getFDrivers() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            catolgDrivers(token);
        }
    }


    private void catolgDrivers(String token) {
        requestDrivers request= new requestDrivers(true,token);
        presenter.showDialog();
        Call<responsDrivers> call=service.getDriversCatalog(request);
        call.enqueue(new Callback<responsDrivers>() {
            @Override
            public void onResponse(Call<responsDrivers> call, Response<responsDrivers> response) {
                validateCodeDrivers(response,context);
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
                List<String> d=new ArrayList<>();
                d.clear();
                namesDrivers.clear();
                for (int i=0;i<data.size();i++)
                {
                    namesDrivers.add(data.get(i).getEmployeeName());
                    Log.e("catalogD",""+data.get(i).getEmployeeName()+"/"+data.get(i).getCveEmployee());
                    tripulantesdata.add(data.get(i).getEmployeeName());
                    d.add(data.get(i).getEmployeeName()+"/"+data.get(i).getCveEmployee());
                }
                namesDrivers.add(0,"Selecciona un conductor");
                mynamesDrivers=namesDrivers;
               // presenter.hideDialog();
                presenter.setD(d);
                presenter.setDrivers(namesDrivers);
                presenter.setDriversNodefaulValue(tripulantesdata);
                //Log.e("tripulantes","  data names  "+namesDrivers);
                //presenter.hideDialog();
            }

        }
    }
    //endregion

    //region updateAsigments
    @Override
    public void updateFData(List<VehicleDriver> asigments) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        Log.e("tripulantesnewFLOW"," requestUpdateAsigments :  "+Mcvelayer);
        Log.e("tripulantesnewFLOW"," requestUpdateAsigments :  "+token);
        for(int i=0;i<asigments.size();i++)
        {
            Log.e("tripulantesnewFLOW"," data request :   cve "+asigments.get(i).getCveVehicle()+" ve  "+
                    asigments.get(i).getCveDriver()+"    T: "+asigments.get(i).getTripulantes());
        }
        if(token!=null)
        {
            requestUpdateAsignments(Mcvelayer,token,asigments);
        }
    }

    private void requestUpdateAsignments(int mcvelayer, String token, List<VehicleDriver> asigments) {
        Log.e("tripuFlow23","cvelayer: "+mcvelayer+" token: "+token+"    "+asigments);
        List<VehicleDriver2> newdata=new ArrayList<>();

        if(asigments!=null)
        {
            for(int i=0;i<asigments.size();i++)
            {
                //Log.e("tripuFlow4",""+asigments.get(i).)      esto contienecampos que no existen maldicion xD
                VehicleDriver2 vnewdata=new VehicleDriver2(asigments.get(i).getCveVehicle(),asigments.get(i).getCveDriver(),asigments.get(i).getTripulantes());
                Log.e("tripuFlow23","Last I: "+i+"   V " +asigments.get(i).getCveVehicle()+"  D: "+asigments.get(i).getCveDriver()+" T: "+asigments.get(i).getTripulantes().size());
                newdata.add(vnewdata);
            }
        }

       requestUpdate request= new requestUpdate(mcvelayer,token,newdata);
        presenter.showDialog();
        Call<responseUpdate> call =service.postAsignments(request);
        call.enqueue(new Callback<responseUpdate>() {
            @Override
            public void onResponse(Call<responseUpdate> call, Response<responseUpdate> response) {
                validateCodeUpdate(response,context);
                Log.e("tripuFlow23","rf "+response.message()+"  "+response.body().getMessage()+"   "+response.errorBody());
            }

            @Override
            public void onFailure(Call<responseUpdate> call, Throwable throwable) {
                Log.e("tripuFlow23","E"+ throwable.getMessage());
                Toast.makeText(context, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void validateCodeUpdate(Response<responseUpdate> response, Context context) {
         if (response != null) {
             Log.e("tripulantesnewFLOW"," requestUpdateAsigments :  "+response.code());
                    if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                        responsedataUpdate(response, context);

                    } else {

                        Toast.makeText(context, "error aqui" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
                    }
                }
    }

    private void responsedataUpdate(Response<responseUpdate> response, Context context) {
        responseUpdate resp=response.body();
        if(resp!=null)
        {
            int responseCode=resp.getResponseCode();
            String message=resp.getMessage();
            if(responseCode==GeneralConstantsV2.RESPONSE_CODE_OK)
            {
                Log.e("tripulantesnewFLOW23","updatedata succesfull "+ responseCode+" "+message );
                presenter.hideDialog();
                presenter.restartAfterUpdate();
            }

            else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {

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
        }else if(responseCode == 106)
            {
                presenter.hideDialog();
                presenter.restartAfterUpdate();
              //  Toast.makeText(context, "" + resp.getMessage(), Toast.LENGTH_SHORT).show();
            }
            else if(responseCode == 900)
            {
                presenter.hideDialog();
                Toast.makeText(context, "" + resp.getMessage(), Toast.LENGTH_SHORT).show();
            }
            else if(responseCode == 500)
            {
                presenter.hideDialog();
                Toast.makeText(context, "" + resp.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void newsetAuditTrail(String name) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            myauditTrail(name,token);
        }
    }
    private void myauditTrail(String name ,String token)
    {
        AuditTrail mynewAuditTrail=new AuditTrail("Onroad_Asignaciones","Editar asignaciones",""+name);
        setAuditTrail request=new setAuditTrail(mynewAuditTrail,token);
        service.auditTrail(request).enqueue(new Callback<responseAuditTrail>() {
            @Override
            public void onResponse(Call<responseAuditTrail> call, Response<responseAuditTrail> response) {
                validateCodeauditTrail(response,context);
            }

            @Override
            public void onFailure(Call<responseAuditTrail> call, Throwable t) {
                presenter.setMessageToView(RetrofitValidationsV2.getOnFailureResponse(t, context));
            }
        });
    }

    private  void  validateCodeauditTrail(Response<responseAuditTrail> response,Context context)
    {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            responseSetAuditTrial(response,context);
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }
    private void responseSetAuditTrial(Response<responseAuditTrail> response,Context context)
    {
        responseAuditTrail auditResponse=response.body();
        if(auditResponse!=null)
        {
            int responseCode=auditResponse.getResponseCode();
            String message=auditResponse.getMessage();
            if(responseCode==GeneralConstantsV2.RESPONSE_CODE_OK)
            {

            }
        }

    }

}
