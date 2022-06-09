package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.arrayasignments.Data;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.arrayasignments.VhicleDriver;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.arrayasignments.asigmentResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.arrayasignments.asignmentRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.asignment.asignment;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.asignment.asignmentResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.asignment.vehicleZones;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.newUpdate.VehicleDriver;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.newUpdate.newUpdateAsigments;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.newUpdate.newUpdateResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model.asigmentRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.adapter.zonesAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.checkAsigment.asignVehicles;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.checkAsigment.dataAsignment;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.checkAsigment.requestAsigments;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.checkAsigment.responseAsigment;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.drivers.driversNames;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.drivers.requestDrivers;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.drivers.responsDrivers;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.vehicles.UnitsCves;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.vehicles.requestUnits;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.vehicles.responseUnits;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.presenter.driverAsignmentPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.util.asignmentService;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class driverAsignmentInteractorImplements implements  checkAsignement {

    private Context context;
    private driverAsignmentPresenter presenter;

    private Retrofit retrofitClient;
    private asignmentService service;

    public static List<String> namesDrivers=new ArrayList<>();
    public static List<String> namesVehicles=new ArrayList<>();

    public static List<String> cvesDricers=new ArrayList<>();
    public static List<String> cveVehicles=new ArrayList<>();

    private  String cveLayer;

    public driverAsignmentInteractorImplements(driverAsignmentPresenter presenter,Context context)
    {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        service = retrofitClient.create(asignmentService.class);
    }

    @Override
    public void checkList(String Asigment) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        cveLayer=Asigment;
        if (token != null) {
            requestdata(token);
        }
    }

    @Override
    public void requestDrivers() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if (token != null) {
            setDriversVehicles(token);
        }
    }

    private void setDriversVehicles(String token) {
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
                namesDrivers.add(0,"Selecciona un conductor");
               presenter.hideDialog();

                presenter.getDriversCatalog(tripulantesdata);
                //Log.e("tripulantes","  data names  "+namesDrivers);
            }

        }
    }

    @Override
    public void requestUnits() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if (token != null) {
            catolgVehicles(token);
        }
    }

    @Override
    public void asigmentDrivers( List<vehicleZones> zones) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if (token != null) {
            newAsignment(token,zones);
        }
    }



    @Override
    public void savesignments() {
        //zonesAdapter.cveLayerZone;
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if (token != null) {
        updateAsignment(token);
        }

    }


    private void newAsignment(String token,List<vehicleZones> zones) {

        List<vehicleZones> vehicleZonesList = new ArrayList<>();
        //zones.add(new vehicleZones(10150,22265));
        //zones.add(new vehicleZones(11047,19263));
        List<Integer> cveVehicle = new ArrayList<>();
        List<Integer> cveDriver = new ArrayList<>();
        if(zones!=null){
        for (int i=0; i<zones.size(); i++){
            cveVehicle.add(zones.get(i).getCveVehicle());
            cveDriver.add(zones.get(i).getCveDriver());
            vehicleZonesList.add(new vehicleZones(cveVehicle.get(i),cveDriver.get(i)));
        }}
        else {
            Toast.makeText(context, "nulsito papa", Toast.LENGTH_SHORT).show();
        }
        asignment request=new asignment( zonesAdapter.cveLayerZone,token, vehicleZonesList);//"{10150,22265},{11047,19263}"
        if(zones.size()!=0)
        {
         //   Log.e("viewtoReuquestA","zonasI  "+ zones.size()+ "   "+ vehicleZonesList.toString()+" ");
        }
        if(zones!=null)
        {
            for(int i=0;i<zones.size();i++)
            {
                //Log.e("viewtoReuquestA","zonasI  "+zones.get(i).getCveVehicle()+"   "+zones.get(i).getCveDriver());
            }
        }
        presenter.showDialog();
        Call<asignmentResponse> call= service.postAsignments(request);
        call.enqueue(new Callback<asignmentResponse>() {
            @Override
            public void onResponse(Call<asignmentResponse> call, Response<asignmentResponse> response) {

                validateCodenewAisgnment(response,context);
            }

            @Override
            public void onFailure(Call<asignmentResponse> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                presenter.hideDialog();
            }
        });

    }
    private void validateCodenewAisgnment(Response<asignmentResponse> response,Context context)
    {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                asignmentsCode(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private  void   asignmentsCode(Response<asignmentResponse> response,Context context)
    {
        asignmentResponse reponseCode=response.body();
        if(reponseCode!=null)
        {
            String message=reponseCode.getMessage();
            String code=reponseCode.getResponseCode();
         //   Toast.makeText(context, "" + code+ "   "+message, Toast.LENGTH_SHORT).show();
                presenter.hideDialog();
            Log.e("viewtoReuquestA", "zonas  " + code+ "   "+message);

        }


    }
    private void updateAsignment(String token) {
     //asignment request= new asignment(zonesAdapter.cveLayerZone,token,)///
    }

    private void catolgVehicles(String token) {
        requestUnits request =new requestUnits(true,token);
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

    private  void validateCodeUnits(Response<responseUnits> response,Context context)
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
                namesVehicles.clear();
                for(int i=0;i<data.size();i++)
                {
                    namesVehicles.add(data.get(i).getVehicleName()+"/"+data.get(i).getCveVehicle());
                    Log.e("catalogD",""+data.get(i).getVehicleName()+"/"+data.get(i).getCveVehicle());
                }
                namesVehicles.add(0,"Selecciona un vehículo");
                presenter.hideDialog();

            }
        }
    }

    private void requestdata(String token) {
        requestAsigments request=new  requestAsigments(token);
        presenter.showDialog();
        Call<responseAsigment> call =service.getZonesAsignment(request);
        call.enqueue(new Callback<responseAsigment>() {
            @Override
            public void onResponse(Call<responseAsigment> call, Response<responseAsigment> response) {
                validateCode(response,context);
            }

            @Override
            public void onFailure(Call<responseAsigment> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void validateCode(Response<responseAsigment> response, Context context) {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                getzonesalldata(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getzonesalldata(Response<responseAsigment> response, Context context) {
        responseAsigment responseInfo=response.body();
        if(responseInfo!=null)
        {
            String message=responseInfo.getMessage();
            int responseCode=responseInfo.getResponseCode();


            if(responseCode==GeneralConstantsV2.RESPONSE_CODE_OK)
            {
                dataAsignment[] data=responseInfo.getData();//
                if(data!=null)
                {
/***/
                    if(data!=null)
                    {
                        Log.e("newFlowasignment","tamaño de datos :"+data.length);
                        List<asignVehicles> dataInfo=new ArrayList<>();
                        List<String> dataInfoAsigments = new ArrayList<>();
                        dataInfo.clear();

                        String employe1="";
                        String vehicleName1="";
                        String cveVehicle1="";
                        String cveEmploye1="";
                        dataInfoAsigments.clear();
                        for(int i=0;i<data.length;i++)
                        {

                            /** aqui lo que devemos de obtener es la comparacion del cveLayer en tanto   data[i].getCveLayer() seaigual a cveLayer toda la data de getvehicles debe insertarce en la lista de asignaciones para el adapter*/
                            // //  dataInfo.addAll( data[i].getVehicles());
                            //                           Log.e("newFlowasignment",""+data[i].getCveLayer());

                            if(data[i].getCveLayer()== Integer.valueOf(cveLayer))
                            {
                                Log.e("newFlowasignment","COMPARITION"+data[i].getCveLayer()+"  cvelayer "+cveLayer);
                               dataInfo.addAll( data[i].getVehicles());
                                Log.e("newFlowasignment","FULL DATA OF LIST"+"   "+dataInfo);
                            }

                        }
                        if(dataInfo!=null)
                        {
                            for(int m=0;m<dataInfo.size();m++)
                            {

                                employe1=dataInfo.get(m).getEmployeeName();
                                vehicleName1=dataInfo.get(m).getVehicleName();
                                cveVehicle1=String.valueOf(dataInfo.get(m).getCveVehicle());
                                cveEmploye1=String.valueOf( dataInfo.get(m).getCveEmploye());
                                if(dataInfo.get(m).getCveVehicle()!=0)
                                {
                                    dataInfoAsigments.add(employe1+","+vehicleName1+","+cveVehicle1+","+cveEmploye1);
                                }else
                                {

                                }
                                Log.e("newFlowasignment","FULL DATA OF LIST iteration"+"   "+dataInfo.get(m).getCveVehicle());
                            }
                            Log.e("newFlowasignment","FIANL DATA"+"   "+dataInfoAsigments);/**lista final a comparar con asigment2*/
                        }
                        List<asignVehicles> asingment=new ArrayList<>();
                        //for (int i=0;i<data.length;i++)
                        //{
                        Log.e("asignment","posicion estatica de la lista : "+zonesAdapter.zonePosition);
                        asingment.addAll( data[zonesAdapter.zonePosition].getVehicles());/** se requiere eliminar informacion de modelo errada del adaptador  cve employe y hacer independiente el adapter
                     de zonasRecyclerView*/
                        Log.e("asignment","position : "+asingment.get(0).getEmployeeName());
                        // }
                        List<List<String>> asignments = new ArrayList<List<String>>();
                        List<String> asignments2=new ArrayList<>();
                        // asignments.clear();
                        asignments2.clear();
                        String employe="";
                        String vehicleName="";
                        String cveVehicle="";
                        String cveEmploye="";
                        if(asingment!=null)
                        {
                            for (int i =0;i<asingment.size();i++)
                            {
                                employe=asingment.get(i).getEmployeeName();
                                vehicleName=asingment.get(i).getVehicleName();
                                cveVehicle=String.valueOf( asingment.get(i).getCveVehicle());
                                cveEmploye=String.valueOf( asingment.get(i).getCveEmploye());
                                if(asingment.get(i).getCveVehicle()!=0)
                                {
                                    asignments2.add(employe+","+vehicleName+","+cveVehicle+","+cveEmploye);
                                }else
                                {

                                }
                                //Log.e("asignment","size:"+asingment.size()+"   employeName :  "+employe+"  vehicleName: "+vehicleName+"  cveVehicle:  "+cveVehicle);
                            }
                            // asignments.add(asignments2);
                        }
                        if(dataInfoAsigments!=asignments2)
                        {
                            presenter.CheckDataAsignment(dataInfoAsigments);
                        }else
                        {
                            Log.e("asignment","data deasigment2 !!!:  "+asignments2 );
                            presenter.CheckDataAsignment(asignments2);
                        }

                    }
//200
/***/

                   // presenter.hideDialog();
                }

            }
        }
    }

    @Override
    public void requestTripulantes() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        String CveLayer = String.valueOf(zonesAdapter.cveLayerZone);

        if(token!=null)
        {
            Log.e("tripulantesnewFLOW","responseCode :  "+token);
            getAsignmentsenpoint(CveLayer,token);
        }
    }

    private void getAsignmentsenpoint(String cveLayer,String token)
    {
        int cvelay=Integer.valueOf(cveLayer);
        Log.e("tripulantesnewFLOW","cvelayer :       "+cvelay);
        asignmentRequest request =new asignmentRequest( cvelay,token);
        presenter.showDialog();
        Call<asigmentResponse> call=service.getAsignments(request);
        call.enqueue(new Callback<asigmentResponse>() {
            @Override
            public void onResponse(Call<asigmentResponse> call, Response<asigmentResponse> response) {
                validateCodeResponse(response,context);
            }

            @Override
            public void onFailure(Call<asigmentResponse> call, Throwable t) {

            }
        });
    }
    private void validateCodeResponse(Response<asigmentResponse> response,Context context)
    {
        if (response != null) {

            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                showAsigments(response,context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();

            }
        }
    }
    private void showAsigments(Response<asigmentResponse> response,Context context)
    {
        asigmentResponse res=response.body();
        if(res!=null)
        {
            int code=res.getResponseCode();
            String message=res.getMessage();
            List<Data> data =res.getData();
            if(code==GeneralConstantsV2.RESPONSE_CODE_OK)
            {

                if(data!=null)
                {
                    List<VhicleDriver>  myAsignments=new ArrayList<>();
                    myAsignments.clear();
                    for(int i=0;i<data.size();i++)
                    {
                        data.get(i).getCveLayer();/**este es el cve layer no interesa mucho*/
                        myAsignments.addAll( data.get(i).getVehicleDrivers());/**al parecer este ya agrego toda la data*/
                        Log.e("tripulantescheckSize","responseCode :  "+data.get(i).getVehicleDrivers());
                    }

                    presenter.setTripulantes(myAsignments);
                }
            }else if(code==107)
            {
                List<VhicleDriver>  myAsignments=new ArrayList<>();
                presenter.setTripulantes(myAsignments);
            }
        }
    }
    @Override
    public void newUpdate(List<VehicleDriver> vehicleDrivers) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        int CveLayer = zonesAdapter.cveLayerZone;

        if(token!=null)
        {
            NEWUPDATE(CveLayer,token,vehicleDrivers);
        }
    }

    private void NEWUPDATE(int cveLayer, String token, List<VehicleDriver> vehicleDrivers) {
        newUpdateAsigments request = new newUpdateAsigments(cveLayer, token, vehicleDrivers);
        presenter.showDialog();
        Call<newUpdateResponse> call = service.postAsignmentsNew(request);
        call.enqueue(new Callback<newUpdateResponse>() {
            @Override
            public void onResponse(Call<newUpdateResponse> call, Response<newUpdateResponse> response) {
                validateCodeResponsenewUpdate(response, context);
            }

            @Override
            public void onFailure(Call<newUpdateResponse> call, Throwable t) {

            }
        });
    }
    private void validateCodeResponsenewUpdate(Response<newUpdateResponse> response,Context context)
    {
        if (response != null) {

                    if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                       newUpdate(response,context);
                    } else {
                        Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();

                    }
                }
    }
    private void newUpdate(Response<newUpdateResponse> response,Context context)
    {
        newUpdateResponse resp=response.body();
        if(resp!=null)
        {
            int code=resp.getResponseCode();
            String message=resp.getMessage();
            if(code==GeneralConstantsV2.RESPONSE_CODE_OK) {
            Log.e("UpdateNewEndpoint","todo salio perfecto");
            }

        }
    }
}
