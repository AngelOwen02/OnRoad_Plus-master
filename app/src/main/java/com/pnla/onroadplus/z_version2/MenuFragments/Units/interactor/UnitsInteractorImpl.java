package com.pnla.onroadplus.z_version2.MenuFragments.Units.interactor;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

/**import com.dynatrace.android.agent.DTXAction;
import com.dynatrace.android.agent.Dynatrace;*/
import com.pnla.onroadplus.BuildConfig;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.Containers.LoginContainer.view.LoginContainerActivity;
import com.pnla.onroadplus.z_version2.Containers.ModelVersion.Version;
import com.pnla.onroadplus.z_version2.Containers.ModelVersion.VersionRequest;
import com.pnla.onroadplus.z_version2.Containers.ModelVersion.VersionResponse;
import com.pnla.onroadplus.z_version2.Containers.ModelVersion.VersionService;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.adapter.UnitTrackingAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Group.GroupDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.FinalUnitDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.UnitDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.GeoreferenceRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.GeoreferenceResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.Group;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.GroupData;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.GroupRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.GroupResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.GroupvehicleInsideData;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.GroupvehicleInsideRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.GroupvehicleInsideResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.UnitData;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.UnitRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.UnitResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.UnitService;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.georeference;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.presenter.UnitsPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.dataRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.dataresponseUnitsV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.requestUnitsmodelV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.responseUnitsV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.util.serviceUnitsV3;
import com.pnla.onroadplus.z_version2.fragments.mapV2.interfaces.MapV2Services;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmUserData;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV3;
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

public class UnitsInteractorImpl implements UnitsInteractor {

    private UnitsPresenter presenter;
    private MapV2Services services;
    private serviceUnitsV3 service;
    private UnitService unitService;
    private Context context;
    public static List<String> dataofvehicles=new ArrayList<>();
    public static List<String> dataofvehiclesgroups=new ArrayList<>();
    public static List<String> dataofvehiclesgroupscve=new ArrayList<>();/***/
    public static  List<String> dataofvehiclesgroupsnames=new ArrayList<>();
    private List<String> gruposdata = new ArrayList<>();
    private List<GroupvehicleInsideData> groupvehicleInsideData;
    private Retrofit retrofitClient,retrofitClient2;

    public static List<String> dataphotoofvehicles=new ArrayList<>();
    public static int cveofgroup;

    public Version version;
    private String name = "OnRoad";
    private List<String> versiones;
    //   public static boolean firstime;
    private int sizeoflistgeoreference;
    List<String> adress=new ArrayList<>();

    public UnitsInteractorImpl(UnitsPresenter presenter, Context context) {
        this.context = context;
        this.presenter = presenter;
        initRetrofit();
    }

    //region initRetrofit
    private void initRetrofit() {
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        retrofitClient2 = RetrofitClientV3.getRetrofitInstance();
        unitService = retrofitClient.create(UnitService.class);
        service=retrofitClient2.create(serviceUnitsV3.class);
    }
    //endregion initRetrofit

    //region getAllVehiclesFromAPI
    @Override
    public void getAllVehiclesFromAPI(boolean ismorethan20) {
        List<Integer> noCves = new ArrayList<>();
        //
        //  firstime=true;

      //  Log.e("doitonce", "" + UnitTrackingAdapter.integerList);
       /* if(!UnitDB.getUnitListActive().isEmpty())
        {
            List<Integer> cveList=new ArrayList<>();

            for(int i=0;i<UnitDB.getUnitListActive().size();i++)
            {
                cveList.add(UnitDB.getUnitListActive().get(i).getCveVehicle());
            }
            Log.e("vehiclecve","qwewqewq"+cveList);
            noCves.clear();
            noCves=cveList;
        }*/
        if(!UnitTrackingAdapter.integerList.isEmpty())
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
        }
        startVehiclesRequest(GeneralConstantsV2.REQUEST_ALL_VEHICLES, noCves, context,true);

//        Log.e("UnitDB", UnitDB.getUnitList().toString());
//        Log.e("FinalUnitDB", FinalUnitDB.getUnitList().toString());
//        Log.e("TemporalUnitDB", TemporalUnitDB.getUnitList().toString());


    }
    //endregion getAllVehiclesFromAPI

    //region getGeoreferencefromAPI
    @Override
    public void getGeoreferencefromAPI(List<Integer> noCves) throws IOException {

        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        int extracves=sizeoflistgeoreference-noCves.size();

        int tipodeRequest=1;
        if(token!=null) {
           // Log.e("bloquesdeunides11",""+noCves+ "   "+token+"   "+tipodeRequest);
            GeoreferenceRequest request = new GeoreferenceRequest(token, tipodeRequest, noCves);
            //presenter.showProgressDialog();

         /**final DTXAction processUnits= Dynatrace.enterAction("processUnits");//
         processUnits.reportValue("Token",token);//ListaCVEs
            processUnits.reportValue("ListaCVEs",String.valueOf(noCves));*/
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

                          /**processUnits.getRequestTag();
                          processUnits.getRequestTagHeader();
                          processUnits.tagRequest(connection);*/


                        adress.clear();
                        int extraItems=0;
                        int i=0;
                        GeoreferenceResponse myresponse=response.body();
                        //  Log.e("bloquesdeunides3",""+ myresponse.getData().getUnitList().get(0).getGeoreference());
                        // Log.e("bloquesdeunides8",""+ cves);

                        for (georeference georeference: myresponse.getData().getUnitList()){
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
    //processUnits.leaveAction();
        }
    }
    @Override
    public void askgeofences(List<dataRequest> askgeofences) {
        requestUnitsmodelV3 request= new requestUnitsmodelV3(askgeofences);
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
                    presenter.setVehiclesGeos(data);
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
    //endregion getGeoreferencefromAPI

    //region startVehiclesRequest
    private void startVehiclesRequest(int typeRequest, List<Integer> vehiclesCves, final Context context, boolean ismorethan20) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if (token != null) {
            UnitRequest request = new UnitRequest(token, 1, vehiclesCves);
            Log.e("token", "" + token);
            // Log.e("checkinguser",token+"  "+typeRequest+"  "+vehiclesCves);
            presenter.showProgressDialog();
          /*  if (ismorethan20) {
                unitService.getFullVehiclesGEO(request).enqueue(new Callback<UnitResponse>() {
                    @Override
                    public void onResponse(Call<UnitResponse> call, Response<UnitResponse> response) {
                        try {
                            validateCode(response, context);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<UnitResponse> call, Throwable t) {
                        // Log.e("onFailure",t.getLocalizedMessage());
                        Toast.makeText(context, "sesion expirada", Toast.LENGTH_LONG).show();

                    }
                });

            } else {*/
                unitService.getFullVehicles(request).enqueue(new Callback<UnitResponse>() {
                    @Override
                    public void onResponse(Call<UnitResponse> call, Response<UnitResponse> response) {
                        try {
                            validateCode(response, context);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<UnitResponse> call, Throwable t) {
                        // Log.e("onFailure",t.getLocalizedMessage());
                        Toast.makeText(context, "sesion expirada", Toast.LENGTH_LONG).show();

                    }
                });
            }
      //  }
    }



    //endregion startVehiclesRequest

    //region validateCode
    private void validateCode(Response<UnitResponse> response, Context context) throws IOException {
      //  Log.e("LAPRINCESS", String.valueOf(response.body().getResponseCode()));
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getVehiclesData(response, context);
        } else {
           // presenter.failureResponse(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
            Toast.makeText(context,  "sesion expirada", Toast.LENGTH_LONG).show();
        }
    }
    //endregion validateCode

    //region getVehiclesData
    private void getVehiclesData(Response<UnitResponse> response, Context context) throws IOException {
        UnitResponse unitResponse = response.body();
        if (unitResponse != null) {
            int responseCode = unitResponse.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                UnitData data = unitResponse.getData();
                if (data != null) {
                    List<Unit> unitList = data.getUnitList();
                 //   Log.e("checkinguser",String.valueOf(unitList.size()));
                    if (unitList.isEmpty()){
                        Toast.makeText(context, "No cuentas con unidades disponibles para visualizar", Toast.LENGTH_SHORT).show();
                    }
                    List<Unit> filteredList = new ArrayList<>();
                    String dataN;
                    String dataM;
                    dataofvehicles.clear();
                    dataphotoofvehicles.clear();
                    for(int i=0;i <unitList.size();i++) {

                        dataN= String.valueOf( unitList.get(i).getVehicleName());
                        dataM= String.valueOf( unitList.get(i).getVehicleImage());
                        //dataM.add(String.valueOf(unitList.get(i).getVehicleName()));
                        dataofvehicles.add(dataN);
                        dataphotoofvehicles.add(dataM);
                    }

                  //  Log.e("ArrayDataM",""+dataofvehicles);
                    RealmList unitRealmList = new RealmList();

                    for (Unit unit: unitList){
                        if (unit.getLatitude() == 0 && unit.getLongitude() == 0){

                        }else {

                            unitRealmList.add(unit);
                        }
                    }

//                    Log.e("HHHHH", unitRealmList.toString());
//                    Log.e("bloquesdeunides10", "unitlist"+String.valueOf(unitRealmList.size()));
                    sizeoflistgeoreference=unitRealmList.size();
                    presenter.setVehiclesListToView(unitRealmList);
                    // presenter.hideProgressDialog();




                    if (unitDBIsEmpty()) {
                        unitSelectedFalse();
                        groupSelectedFalse();
                        firstLoginTrue();
                        dialog();
                        changeUnitStatusToFalse(unitList);
                        createUnitListDB(unitRealmList);
                    }else {
                        //updateUnits(unitRealmList);

                        SharedPreferences unitPrefs = context.getSharedPreferences("TrackingUnit:Selected", Context.MODE_PRIVATE);
                        boolean isUnitSelected = unitPrefs.getBoolean("isSelected", true);
//                        Log.e("Unit Selected?", String.valueOf(isUnitSelected));

                        SharedPreferences groupPrefs = context.getSharedPreferences("TrackingGroup:Selected", Context.MODE_PRIVATE);
                        boolean isGroupSelected = groupPrefs.getBoolean("isSelected", true);
//                        Log.e("Group Selected?", String.valueOf(isGroupSelected));
//

                        if (isUnitSelected){
                            firstLoginFalse();

                        } else if (isGroupSelected){
                            firstLoginFalse();

                        }else {
                            firstLoginTrue();
                        }

                    }

                    getGroups(context);
                } else {
                    presenter.failureResponse(context.getString(R.string.textEmptyCarsResponse));
                }
            } else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {

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
            } else {
                presenter.failureResponse("Error");
            }
        } else {
            presenter.failureResponse(context.getString(R.string.textEmptyResponse));
        }
    }
    //endregion getVehiclesData

    //region dialog
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
                                        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.pnla.onroad"));
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
    //endregion dialog

    /**reques de grupos**/
    //region getGroupsVehicles
    //  @Override
    public void getGroupsVehicles(){
        if(!dataofvehiclesgroupscve.isEmpty()) {

            //Log.e("unitsthaticansaw", "" + dataofvehiclesgroupscve);// [x,x,x] grupos cve de vehiculos existentes
            SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
            String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
            //cveofgroup = 53;
            startGroupsRequestVehicles(cveofgroup, token);
               /* for(int i=0;i<dataofvehiclesgroupscve.size();i++)
                {
                    cveofgroup= Integer.parseInt( dataofvehiclesgroupscve.get(i));
                     startGroupsRequestVehicles(cveofgroup, token);

                }*/

        }
    }
    //endregion getGroupsVehicles

    /**reques de grupos**/
    //region startGroupsRequestVehicles
    private void startGroupsRequestVehicles(int cve,String token){
    /*    GroupvehicleInsideRequest request = new GroupvehicleInsideRequest(cve,token);
        Log.e("unitsthaticansaw", "response" +  cve+ " " + token);
          unitService.getVehiclesInGroups(request).enqueue(new Callback<GroupvehicleInsideResponse>() {
              @Override
              public void onResponse(Call<GroupvehicleInsideResponse> call, Response<GroupvehicleInsideResponse> response) {
                  validateGroupsVehiclesCode(response);
                  Log.e("unitsthaticansaw", "onresponse: " +   response);
                  Log.e("unitsthaticansaw", "onresponse: " +   call.request().toString());
              }

              @Override
              public void onFailure(Call<GroupvehicleInsideResponse> call, Throwable t) {
                      Log.e("unitsthaticansaw","validate : "+ call.request().toString());
                  Log.e("unitsthaticansaw","validate : "+ t.toString());
              }
          });*/

        GroupvehicleInsideRequest requestG=new GroupvehicleInsideRequest(cve,token);
        Call<GroupvehicleInsideResponse> call=unitService.getVehiclesInGroups(requestG);
        call.enqueue(new Callback<GroupvehicleInsideResponse>() {
            @Override
            public void onResponse(Call<GroupvehicleInsideResponse> call, Response<GroupvehicleInsideResponse> response) {
                //    Log.e("unitsthaticansaw", "onresponse: " +   response);
                validateGroupsVehiclesCode(response);
            }

            @Override
            public void onFailure(Call<GroupvehicleInsideResponse> call, Throwable t) {
                //Log.e("unitsthaticansaw","validate : "+ t.toString());
            }
        });

    }
    //endregion startGroupsRequestVehicles

    /**reques de grupos**/
    //region validateGroupsVehiclesCode
    private void validateGroupsVehiclesCode(Response<GroupvehicleInsideResponse> response){
       // Log.e("validategroups","validate : "+ String.valueOf(response.body().getResponseCode()));
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {

            getGrupsDataVhicles(response);
        }

    }
    //endregion validateGroupsVehiclesCode

    /**reques de grupos**/
    //region getGrupsDataVhicles
    private void getGrupsDataVhicles(Response<GroupvehicleInsideResponse> response )
    {

        GroupvehicleInsideResponse groupResponse = response.body();
        if (groupResponse != null) {
            int responseCode = groupResponse.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                //List<GroupvehicleInsideData> data = groupResponse.getData();
                groupvehicleInsideData = new ArrayList<>();
                groupvehicleInsideData = groupResponse.getData();
                //Log.e("infogrupos",""+groupvehicleInsideData.toString());
                if (groupvehicleInsideData != null){
                    //  List<String> gruposdata = new ArrayList<>();
                    gruposdata.clear();
                    // dataofvehiclesgroupsnames.clear();
                    /*for (GroupvehicleInsideData data : groupvehicleInsideData){
                        //gruposdata.add(String.valueOf(data.getCve_vehicle()));
                        gruposdata.add(String.valueOf(data.getVehicle_name()));

                    }*/
                    for (int i=0;i<groupvehicleInsideData.size();i++)
                    {
                        gruposdata.add(String.valueOf(groupvehicleInsideData.get(i).getCve_vehicle()));
                     //   Log.e("unitsthaticansaw2",""+i);

                    }

                    dataofvehiclesgroupsnames.add(String.valueOf( gruposdata));
//                    Log.e("unitsthaticansaw2",""+dataofvehiclesgroupsnames);
//
//                    Log.e("unitsthaticansaw",""+gruposdata.size());
                }
            }
        }


    }
    //endregion getGrupsDataVhicles

    //Grupos get
    //region getGroups
    private void getGroups(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        startGroupsRequest(token, context);
    }
    //endregion getGroups

    //Grupos request
    //region startGroupsRequest
    private void startGroupsRequest(String token, final Context context) {
        GroupRequest request = new GroupRequest(token);
        // presenter.showLoaderFromInteractor();
        unitService.getGroups(request).enqueue(new Callback<GroupResponse>()  {
            @Override
            public void onResponse(Call<GroupResponse> call, Response<GroupResponse> response) {
                validateGroupsCode(response, context);
            }

            @Override
            public void onFailure(Call<GroupResponse> call, Throwable t) {
             //   Log.e("jjjj", t.getLocalizedMessage());

                // presenter.setMessageToView(RetrofitValidationsV2.getOnFailureResponse(t, context));

            }
        });
    }
    //endregion startGroupsRequest

    //Grupos
    //region validateGroupsCode
    private void validateGroupsCode(Response<GroupResponse> response, Context context) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getGroupsData(response, context);
        } else {
//            Log.e("jjjj", "rrrr");

            // presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }
    //endregion validateGroupsCode

    //region getGroupsData
    private void getGroupsData(Response<GroupResponse> response, Context context) {
        GroupResponse groupResponse = response.body();
       // Log.e("jjjj", groupResponse.toString());

        if (groupResponse != null) {
            int responseCode = groupResponse.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                GroupData groupData = groupResponse.getData();
                if (groupData != null) {
                    List<Group> groups = groupData.getVehicleGroups();
                    String dataN;
                    String dataÑ;
                    dataofvehiclesgroups.clear();
                    dataofvehiclesgroupscve.clear();

                    for(int i=0;i <groups.size();i++) {

                        dataN= String.valueOf( groups.get(i).getVehicle_group());
                        dataÑ=String.valueOf( groups.get(i).getCve_vehicle_group());
                        //dataM.add(String.valueOf(unitList.get(i).getVehicleName()));
                        dataofvehiclesgroups.add(dataN);
                        dataofvehiclesgroupscve.add(dataÑ);
                    }


                //    Log.e("groupsizes",""+dataofvehiclesgroups);
                    /*if(!dataofvehiclesgroupscve.isEmpty())

                    {
                        //Log.e("unitsthaticansaw",""+dataofvehiclesgroupscve);

                       for(int i=0 ;i <dataofvehiclesgroupscve.size();i++)
                        {
                            cveofgroup=Integer.parseInt(dataofvehiclesgroupscve.get(i));

                        }
                        getGroupsVehicles();
                    }*/

                    if (groups != null && groups.size() > 0) {
                        RealmList groupRealmList = new RealmList();
                        groupRealmList.addAll(groups);
                        if (groupDBIsEmpty()) {
                            changeGroupStatusToFalse(groups);
                            createGroupListDB(groups);
                        }

                    } else {
                        //presenter.setMessageToView("No se encotraron grupos");
                    }
                } else {
                    // presenter.setMessageToView(context.getString(R.string.textEmptyCarsResponse));
                }
            } else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {
                UnitDB.deleteDB();
                GroupDB.deleteDB();
                RealmUserData.deleteDB();

                Bundle bndl = new Bundle();
                bndl.putBoolean("HelpStatus", true);

                Intent intent = new Intent(context, LoginContainerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtras(bndl);
                context.startActivity(intent);

                presenter.failureResponse(context.getString(R.string.textSessionExpired));
            } else {
                //presenter.setMessageToView(groupV2Response.getMessage());
            }
        } else {
            // presenter.setMessageToView(context.getString(R.string.textEmptyResponse));
        }
    }
    //endregion getGroupsData

    //region unitDBIsEmpty
    private boolean unitDBIsEmpty() {
        return UnitDB.getUnitList().isEmpty();
    }
    //endregion unitDBIsEmpty

    //region createUnitListDB
    private void createUnitListDB(List<Unit> unitList) {
        for (Unit unit : unitList) {
            UnitDB.createNewUnit(unit.isVehicleStatus(), unit.getCveVehicle(), unit.getVehicleSwitch(), unit.getVehicleName(), unit.getVehicleImage(), unit.getSendTime(),
                    unit.getDescBrand(), unit.getDescModel(), unit.getVehicleYear(), unit.getVehicleVin(), unit.getVehiclePlate(), unit.getGeoreference(), unit.getTimeTravel(),
                    unit.getTimeElapsed(), unit.getLatitude(), unit.getLongitude(), unit.getMileage(), unit.getKmTravel(), unit.getCurrentSpeed(), unit.getMaxSpeed(), unit.getDesc_layer());
        }
    }
    //endregion createUnitListDB

    //region createFinalUnitListDB
    private void createFinalUnitListDB(List<Unit> unitList) {
        for (Unit unit : unitList) {
            FinalUnitDB.createNewUnit(unit.isVehicleStatus(), unit.getCveVehicle(), unit.getVehicleSwitch(), unit.getVehicleName(), unit.getVehicleImage(), unit.getSendTime(),
                    unit.getDescBrand(), unit.getDescModel(), unit.getVehicleYear(), unit.getVehicleVin(), unit.getVehiclePlate(), unit.getGeoreference(), unit.getTimeTravel(),
                    unit.getTimeElapsed(), unit.getLatitude(), unit.getLongitude(), unit.getMileage(), unit.getKmTravel(), unit.getCurrentSpeed(), unit.getMaxSpeed(), unit.getDesc_layer());
        }
    }
    //endregion createFinalUnitListDB

    //region groupDBIsEmpty
    private boolean groupDBIsEmpty() {
        return GroupDB.getGroupList().isEmpty();
    }
    //endregion groupDBIsEmpty

    //region createGroupListDB
    private void createGroupListDB(List<Group> groupList) {
        for (Group group : groupList) {
            GroupDB.createNewGroup(group.getCve_vehicle_group(), group.getUserGroup(), group.getVehicle_group(), group.getDesc_vehicle_group(), group.isSelected(),
                    group.getPositionItem(), group.getVehicles());
        }
    }
    //endregion createGroupListDB

    //region changeUnitStatusToFalse
    private void changeUnitStatusToFalse(List<Unit> vehicleList) {
        for (Unit vehicles : vehicleList) {
            vehicles.setVehicleStatus(false);
        }
    }
    //endregion changeUnitStatusToFalse

    //region changeGroupStatusToFalse
    private void changeGroupStatusToFalse(List<Group> groupList) {
        for (Group group : groupList) {
            group.setSelected(false);
        }
    }
    //endregion changeGroupStatusToFalse

    //region firstLoginTrue
    private void firstLoginTrue() {
        SharedPreferences prefs = context.getSharedPreferences("Login:FirstTime", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isFirst", true);
        editor.commit();
    }
    //endregion firstLoginTrue

    //region firstLoginFalse
    private void firstLoginFalse() {
        SharedPreferences prefs = context.getSharedPreferences("Login:FirstTime", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isFirst", false);
        editor.commit();
    }
    //endregion firstLoginFalse

    //region unitSelectedFalse
    private void unitSelectedFalse() {
        SharedPreferences prefs = context.getSharedPreferences("TrackingUnit:Selected", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isSelected", false);
        editor.commit();
    }
    //endregion unitSelectedFalse

    //region groupSelectedFalse
    private void groupSelectedFalse() {
        SharedPreferences prefs = context.getSharedPreferences("TrackingGroup:Selected", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isSelected", false);
        editor.commit();
    }
    //endregion groupSelectedFalse

    //region updateUnits
    private void updateUnits(List<Unit> unitList){
        UnitDB.deleteDB();
        createUnitListDB(unitList);
    }
    //endregion updateUnits
}
