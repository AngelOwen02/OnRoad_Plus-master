package com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.mapTracking.trackingInteractor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.mapTracking.models.lasPositionItemRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.mapTracking.models.laspositionmapservice;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.mapTracking.models.lastData;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.mapTracking.models.lastPositionItemResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.mapTracking.models.lastlatlong;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.mapTracking.trackingPresenter.trackingPresenter;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class trackingInteractorImpl implements trackingInteractor {

    private trackingPresenter presenter;
    private Context context;
    private laspositionmapservice service;

    public  trackingInteractorImpl(trackingPresenter presenter,Context context)
    {
        this.context=context;
        this.presenter=presenter;
        initRetrofit();

    }
    private void initRetrofit() {
        Retrofit retrofitClient = RetrofitClientV2.getRetrofitInstance();
        service = retrofitClient.create(laspositionmapservice.class);
    }

    @Override
    public void getVehiclesFromAPIS() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        startVehiclesRequest(token, context);
    }
    private void startVehiclesRequest(String mytoken,final Context context)
    {
        lasPositionItemRequest request= new lasPositionItemRequest(mytoken);
        service.getvehicles(request).enqueue(new Callback<lastPositionItemResponse>() {
            @Override
            public void onResponse(Call<lastPositionItemResponse> call, Response<lastPositionItemResponse> response) {
                validateCode(response, context);
                Log.e("datosdelmapafinal",""+response);
            }

            @Override
            public void onFailure(Call<lastPositionItemResponse> call, Throwable t) {


            }
        });

    }
    private void validateCode(Response<lastPositionItemResponse> response,Context context)
    {
        if(RetrofitValidationsV2.checkSuccessCode(response.code()))
        {
            getlaspositionItems(response,context);
        }
        else
        {
            presenter.failureResponse(RetrofitValidationsV2.getErrorByStatus(response.code(),context));
            Toast.makeText(context,  "sesion expirada", Toast.LENGTH_LONG).show();
        }
    }
    private void  getlaspositionItems(Response<lastPositionItemResponse> response , Context context)
    {
        lastPositionItemResponse vehiclesResponse= response.body();

        if(vehiclesResponse!=null)
        {
            int responceCode=vehiclesResponse.getResponseCode();
            if(responceCode== GeneralConstantsV2.RESPONSE_CODE_OK)
            {
                lastData data= vehiclesResponse.getData();
                if(data!=null)
                {
                    List<lastlatlong> unilist= data.getUnitList();


                }
            }
        }
    }
}
