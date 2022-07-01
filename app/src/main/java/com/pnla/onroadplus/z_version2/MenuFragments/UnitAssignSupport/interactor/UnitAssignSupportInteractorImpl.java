package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.interactor;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.dynatrace.android.agent.DTXAction;
import com.dynatrace.android.agent.Dynatrace;
import com.pnla.onroadplus.BuildConfig;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.Containers.LoginContainer.view.LoginContainerActivity;
import com.pnla.onroadplus.z_version2.Containers.ModelVersion.Version;
import com.pnla.onroadplus.z_version2.Containers.ModelVersion.VersionRequest;
import com.pnla.onroadplus.z_version2.Containers.ModelVersion.VersionResponse;
import com.pnla.onroadplus.z_version2.Containers.ModelVersion.VersionService;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.presenter.UnitAssignSupportPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.FinalUnitDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.UnitDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.GeoreferenceRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.GeoreferenceResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SupportUnitData;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SupportUnitRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SupportUnitResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.UnitService;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.UnitSupport;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmUserData;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UnitAssignSupportInteractorImpl implements UnitAssignSupportInteractor {

    //private UnitAssignSupportPresenter presenter;
    private UnitAssignSupportPresenter presenter;
    //private SupportService service;
    private UnitService unitService;
    private Context context;
    //public static List<String> dataofvehicles=new ArrayList<>();
    //public static List<String> dataofvehiclesgroups=new ArrayList<>();
    //public static List<String> dataofvehiclesgroupscve=new ArrayList<>();/***/
    //public static  List<String> dataofvehiclesgroupsnames=new ArrayList<>();
    //private List<String> gruposdata = new ArrayList<>();
    //private List<GroupvehicleInsideData> groupvehicleInsideData;
    private Retrofit retrofitClient;

    //public static List<String> dataphotoofvehicles=new ArrayList<>();
    //public static int cveofgroup;

    public Version version;
    private String name = "OnRoad";
    private List<String> versiones;
    //   public static boolean firstime;
    private int sizeoflistgeoreference;
    //List<String> adress=new ArrayList<>();

    public UnitAssignSupportInteractorImpl(UnitAssignSupportPresenter presenter, Context context) {
        this.context = context;
        this.presenter = presenter;
        initRetrofit();
        requestSoportes();
    }
    private void initRetrofit() {
        Retrofit retrofitClient = RetrofitClientV2.getRetrofitInstance();
        unitService = retrofitClient.create(UnitService.class);
        //Toast.makeText(context, "paso por aqui retrofit", Toast.LENGTH_LONG).show();
    }

    @Override
    public void requestSoportes(){
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        //Toast.makeText(context, "paso por aqui request1", Toast.LENGTH_LONG).show();
        //String token = "7042b63634e5aab50029d64d1c802e4b";
        if(token!=null) {
            //Toast.makeText(context, "paso por aqui y reviso el token, all bien", Toast.LENGTH_LONG).show();
            //startVehiclesRequest(token);
            requestSoportes(token);
        }

    }

    /**@Override
    public void getAllVehiclesFromAPI() {
        List<Integer> noCves = new ArrayList<>();
        /**if(!UnitTrackingAdapter.integerList.isEmpty())
        {
            List<Integer> cveList=new ArrayList<>();

            for(int i=0;i<UnitTrackingAdapter.integerList.size();i++)
            {
                cveList.add(UnitTrackingAdapter.integerList.get(i));
            }
            //  Log.e("vehiclecve","qwewqewq"+cveList);
            noCves.clear();
            noCves=cveList;
        }
        else if(!UnitDB.getUnitListActive().isEmpty())
        {
            List<Integer> cveList=new ArrayList<>();

            for(int i=0;i<UnitDB.getUnitListActive().size();i++)
            {
                cveList.add(UnitDB.getUnitListActive().get(i).getCveVehicle());
            }
            // Log.e("vehiclecve","qwewqewq"+cveList);
            noCves.clear();
            noCves=cveList;
        }
        else
        {
            noCves.add(0);
        }*/


        //startVehiclesRequest(GeneralConstantsV2.REQUEST_ALL_VEHICLES, noCves, context);
        //startVehiclesRequest(GeneralConstantsV2.REQUEST_ALL_VEHICLES, context);

        /**SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        //String token = "7042b63634e5aab50029d64d1c802e4b";
        startVehiclesRequest(token);*/
        //Toast.makeText(context, token, Toast.LENGTH_SHORT).show();

//        Log.e("UnitDB", UnitDB.getUnitList().toString());
//        Log.e("FinalUnitDB", FinalUnitDB.getUnitList().toString());
//        Log.e("TemporalUnitDB", TemporalUnitDB.getUnitList().toString());
    //}

    /**@Override
    public void getGeoreferencefromAPI(List<Integer> noCves) throws IOException {

        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        int extracves=sizeoflistgeoreference-noCves.size();

        int tipodeRequest=1;
        /**if(token!=null) {
            // Log.e("bloquesdeunides11",""+noCves+ "   "+token+"   "+tipodeRequest);
            GeoreferenceRequest request = new GeoreferenceRequest(token, tipodeRequest, noCves);
            //presenter.showProgressDialog();

            final DTXAction processUnits= Dynatrace.enterAction("processUnits");//
            processUnits.reportValue("Token",token);//ListaCVEs
            processUnits.reportValue("ListaCVEs",String.valueOf(noCves));
            Call<GeoreferenceResponse> call=   unitService.getFullGeoReference(request);
            String url1=call.request().url().toString();
            URL url = new URL(url1);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();


            call.enqueue(new Callback<GeoreferenceResponse>() {
                @Override
                public void onResponse(Call<GeoreferenceResponse> call, Response<GeoreferenceResponse> response) {
                    if(response.code()==200)
                    {
                        Log.e("urltag",call.request().url().toString());//perfect

                        processUnits.getRequestTag();
                        processUnits.getRequestTagHeader();
                        processUnits.tagRequest(connection);


                        adress.clear();
                        int extraItems=0;
                        int i=0;
                        GeoreferenceResponse myresponse=response.body();
                        //  Log.e("bloquesdeunides3",""+ myresponse.getData().getUnitList().get(0).getGeoreference());
                        // Log.e("bloquesdeunides8",""+ cves);

                        for (com.pnla.onroadplus.z_version2.MenuFragments.Units.data.georeference georeference: myresponse.getData().getUnitList()){
                            if (georeference.getGeoreference().equals("cero")){

                            }else {

                                adress.add(georeference.getVehicleName()+" :::"+georeference.getGeoreference());
                            }
                        }

                        for(i=0;i<myresponse.getData().getUnitList().size();i=i+1)
                        {
                            // adress.add(i,i+"  "+myresponse.getData().getUnitList().get(i).getVehicleName()+"|||"+myresponse.getData().getUnitList().get(i).getGeoreference());

                        }

                        extraItems=sizeoflistgeoreference-myresponse.getData().getUnitList().size();
                        for(int j=0;j<extraItems;j++)
                        {
                            adress.add("");
                        }
                        //  Log.e("bloquesdeunides11",""+ adress);
//                        Log.e("bloquesdeunides14",""+ adress);
//                        Log.e("bloquesdeunides4",""+  sizeoflistgeoreference);
                        presenter.setdirectionsToView(adress);

                    }
                }

                @Override
                public void onFailure(Call<GeoreferenceResponse> call, Throwable t) {
                    Log.e("bloquesdeunides3",""+t.getMessage());
                }
            });
            processUnits.leaveAction();
        }*/
    //}

    //private void startVehiclesRequest(final int typeRequest, List<Integer> vehiclesCves, final Context context) {
    private void requestSoportes(String token) {
    //private void startVehiclesRequest( int typeRequest, final Context context) {
        //SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        //String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        //Toast.makeText(context, mytoken, Toast.LENGTH_SHORT).show();
        SupportUnitRequest request = new SupportUnitRequest(token);
        if(token!=null) {
        //if(mytoken!=null) {
            //UnitRequest request = new UnitRequest(token, typeRequest, vehiclesCves);
            //SupportUnitRequest request = new SupportUnitRequest(token);
            //Log.e("token",""+mytoken);
            // Log.e("checkinguser",token+"  "+typeRequest+"  "+vehiclesCves);
            //presenter.showProgressDialog();
            //unitService.getFullVehicles(request).enqueue(new Callback<UnitResponse>() {
            unitService.getFullVehiclesSupport(request).enqueue(new Callback<SupportUnitResponse>() {
                @Override
                //public void onResponse(Call<UnitResponse> call, Response<UnitResponse> response) {
                public void onResponse(Call<SupportUnitResponse> call, Response<SupportUnitResponse> response) {
                    try {
                        //Toast.makeText(context, "Paso por aqui", Toast.LENGTH_SHORT).show();
                        validateCode(response, context);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                //public void onFailure(Call<UnitResponse> call, Throwable t) {
                public void onFailure(Call<SupportUnitResponse> call, Throwable t) {
                    // Log.e("onFailure",t.getLocalizedMessage());
                    //Toast.makeText(context,  "sesion expirada2", Toast.LENGTH_LONG).show();
                    Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    //private void validateCode(Response<UnitResponse> response, Context context) throws IOException {
    private void validateCode(Response<SupportUnitResponse> response, Context context) throws IOException {
        //  Log.e("LAPRINCESS", String.valueOf(response.body().getResponseCode()));
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            //getVehiclesData(response, context);
            getSupportData(response, context);
            //Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
        } else {
            presenter.failureResponse(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
            Toast.makeText(context,  "sesion expirada3", Toast.LENGTH_LONG).show();
        }
    }

    //private void getVehiclesData(Response<UnitResponse> response, Context context) throws IOException {
    private void getSupportData(Response<SupportUnitResponse> response, Context context) {
        //UnitResponse unitResponse = response.body();
        SupportUnitResponse supportUnitResponse = response.body();
        //if (unitResponse != null) {
        if (supportUnitResponse != null) {
            //int responseCode = unitResponse.getResponseCode();
            int responseCode = supportUnitResponse.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                //UnitData data = unitResponse.getData();
                //SupportUnitData data = supportUnitResponse.getData();
                //Toast.makeText(context, "paso por aqui", Toast.LENGTH_LONG).show();
                List<SupportUnitData> data = supportUnitResponse.getData();
                presenter.setSoportes(data);
                //Toast.makeText(context, data.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void dialog(){
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        final String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);

        final VersionRequest request = new VersionRequest(name,token);
        Retrofit retrofit = RetrofitClientV2.getRetrofitInstance();
        VersionService service = retrofit.create(VersionService.class);
        Call<VersionResponse> call = service.getVersion(request);
        call.enqueue(new Callback<VersionResponse>() {
            @Override
            public void onResponse(Call<VersionResponse> call, Response<VersionResponse> response) {
                if (response.body().getResponseCode()==105) {
                    versiones = new ArrayList<>();
                    version = response.body().getData();
                    if(version!=null){
                        String [] string1 = BuildConfig.VERSION_NAME.split("[.]");
                        String [] string2 = version.getDesc_version().split("[.]");
                        Integer[] number2 = new Integer[string2.length];

                        Integer[] numbers = new Integer[string2.length];


                        for (int i = 0; i < string2.length; i++) {
                            if (string1.length-1<i)
                                numbers[i] = 0;
                            else
                                numbers[i] = Integer.parseInt(string1[i]);
                        }


                        for (int i = 0; i < string2.length; i++) {
                            number2[i] = Integer.parseInt(string2[i]);
                        }

                        for (int i = 0; i < number2.length; i++) {
                            if (number2[i] > numbers[i]) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogCustom);
                                builder.setTitle("Hay una nueva versión disponible de OnRoad");
                                builder.setMessage("Tu versión: " + BuildConfig.VERSION_NAME + "\n\n" + "Nueva versión: " + version.getDesc_version());
                                builder.setCancelable(true);
                                builder.setPositiveButton("Descargar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        //Aqui le cambie la direccion de la app, cada vez que se lance una nueva version, los debera mandar a actualizarla
                                        //intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.pnla.onroad"));
                                        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.pnla.onroadplus"));
                                        //intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.newlandapps.onroad"));
                                        //intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.pnla.onroadplus2"));
                                        intent.setPackage("com.android.vending");
                                        context.startActivity(intent);
                                    }
                                });
                                builder.create();
                                builder.show();
                                break;
                            } else {
                            }
                        }
                    }
                }
            }


            @Override
            public void onFailure(Call<VersionResponse> call, Throwable t) {

            }
        });

    }

}
