package com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.model.Vehicle;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.model.VehicleGroup;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.model.dataUnits;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.model.newUnits;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.model.unitsRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.model.unitsResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.model.vehicleGroupData;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.model.vehicleGroupRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.model.vehicleGroupResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.presenter.trackingPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.utils.mapService;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class trackingInteractorImpl implements trackingInteractor {

    private Context context;
    private trackingPresenter presenter;
    private mapService service;
    private Retrofit retrofitClient;

    public trackingInteractorImpl (trackingPresenter presenter,Context context)
    {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        service = retrofitClient.create(mapService.class);
    }


    //region noGeoRequest
    /** this region request all data that come from Units excepting de georeference wich gave us the addres ... what we require for this module is...
     *  the image of the vehicle, the name and the cve of vehicle and the switch which value range is 0 to 3*/
    @Override
    public void requestNoGeo() {

        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if (token != null) {
        noGeoUnits(token);
        }
    }

    private void noGeoUnits(String token) {
        List<Integer> nocves=new ArrayList<>();
        nocves.add(0);
        unitsRequest request=new unitsRequest( 1, token,nocves);
        Call<unitsResponse> call= service.getfullNogeoVehicles(request);
        call.enqueue(new Callback<unitsResponse>() {
            @Override
            public void onResponse(Call<unitsResponse> call, Response<unitsResponse> response) {
                validareCodeSuccesNogeo(response, context);
                Log.e("nogeoNewModule","succes");
            }

            @Override
            public void onFailure(Call<unitsResponse> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void validareCodeSuccesNogeo(Response<unitsResponse> response,Context context)
    {
        if(response!=null)
        {
            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                getnoGeodata(response, context);
            }
            else {
               Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void getnoGeodata(Response<unitsResponse> response,Context context)
    {
        /**request her fullno geo vehicles
         **/
        unitsResponse allunitsresponse=response.body();
        if(allunitsresponse!=null)
        {
         String message=allunitsresponse.getMessage();
         int responseCode=allunitsresponse.getResponseCode();
         if(responseCode==GeneralConstantsV2.RESPONSE_CODE_OK)
         {
             dataUnits units=allunitsresponse.getData();
             List<newUnits> allUnits=units.getUnitList();
             Log.e("nogeoNewModule",""+allUnits.size()+"  "+allUnits.get(0).getVehicleImage()+"  "+allUnits.get(0).getVehicleName()+"  "+allUnits.get(0).getVehicleSwitch()+" "+allUnits.get(0).getCveVehicle());
             //the presenter module here should be hide de progres dialog and send data to view
         }
        }
    }

    //endregion

    //region vehiclesInGroups
    /**  this region bring us information from the groups an units inside this groups in order to asign  in next module what we would saw in the map*/

    @Override
    public void requestGroups() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if (token != null) {
            checkGroups(token);
        }
    }

    private void checkGroups(String token) {
        vehicleGroupRequest request= new vehicleGroupRequest(token);
        Call<vehicleGroupResponse> call=service.getGroupsR(request);
        call.enqueue(new Callback<vehicleGroupResponse>() {
            @Override
            public void onResponse(Call<vehicleGroupResponse> call, Response<vehicleGroupResponse> response) {
                validateCodeGroupsSucces(response ,context);
            }

            @Override
            public void onFailure(Call<vehicleGroupResponse> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateCodeGroupsSucces(Response<vehicleGroupResponse> response,Context context)
    {
          if(response!=null)
                {
                    if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                        getGroupsData(response, context);
                    }
                    else {
                       Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
                    }
                }

    }

    private void getGroupsData(Response<vehicleGroupResponse> response, Context context) {
        vehicleGroupResponse grupResponse=response.body();
        if(grupResponse!=null)
        {
            String messagge=grupResponse.getMessage();
            int responseCode=grupResponse.getResponseCode();
            if(responseCode==GeneralConstantsV2.RESPONSE_CODE_OK)
            {
                vehicleGroupData data=grupResponse.getData();
                List<VehicleGroup> vehicleGroups=data.getVehicleGroups();
                List<Vehicle> vehiclesInGroups=new ArrayList<>();
                for (int i=0;i<vehicleGroups.size();i++)
                {
                    Log.e("vehicleGroupsData",""+vehicleGroups.get(i).getVehicleGroup());
                    vehiclesInGroups.addAll(vehicleGroups.get(i).getVehicles());
                }
                for (int k=0;k<=vehiclesInGroups.size()-1;k++)
                {
                   // Log.e("vehicleGroupsData",""+vehiclesInGroups.size()+"  "+vehiclesInGroups.get(k).getVehicleName());
                }

            }
        }

    }
    //endregion


}
