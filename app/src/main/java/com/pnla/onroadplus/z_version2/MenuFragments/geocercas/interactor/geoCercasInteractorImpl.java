package com.pnla.onroadplus.z_version2.MenuFragments.geocercas.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.pnla.onroadplus.z_version2.MenuFragments.geocercas.model.Data;
import com.pnla.onroadplus.z_version2.MenuFragments.geocercas.model.geoCercasModel;
import com.pnla.onroadplus.z_version2.MenuFragments.geocercas.model.geoCercasRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.geocercas.presenter.geoCercasPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.geocercas.util.geCercasService;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class geoCercasInteractorImpl implements geCercasInteractor {

    private Context context;
    private geoCercasPresenter presenter;
    private geCercasService service;
    private Retrofit retrofitClient;
    //private List<Data> data=new ArrayList<>();
 //   List<Geofences> geoCercas=new ArrayList<>();
    public geoCercasInteractorImpl(geoCercasPresenter presenter, Context context)
    {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        service = retrofitClient.create(geCercasService.class);
    }


    @Override
    public void geoCercasRequest() {

        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            geoCercasRequestData(token);
        }


    }

    private void geoCercasRequestData(String token)
    {
        geoCercasRequest request= new geoCercasRequest(token);
        presenter.showProgressDialog();
        Call<geoCercasModel> call=service.geoCercas(request);
        call.enqueue(new Callback<geoCercasModel>() {
            @Override
            public void onResponse(Call<geoCercasModel> call, Response<geoCercasModel> response) {
                validateCode(response, context);
                Log.e("geofencesdata",""+response.code());// Toast.makeText(context, "data null", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<geoCercasModel> call, Throwable t) {
                Log.e("geofencesdata",""+t.getMessage());// Toast.makeText(context, "data null", Toast.LENGTH_SHORT).show();

            }
        });
        /**
         Call<cancelOrderResponse> call=service.cancelOrder(request);*/

    }

    private void validateCode(Response<geoCercasModel> response, Context context) {
        if(response!=null){
            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                getGeoeferences(response, context);
            } else {
              //  presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
               // Toast.makeText(context,  "sesion expirada", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void getGeoeferences(Response<geoCercasModel> response, Context context)
    {
        geoCercasModel geoCercasModelresponse=response.body();
        if(geoCercasModelresponse!=null)
        {
            int responseCode=geoCercasModelresponse.getResponseCode();
            if(responseCode== GeneralConstantsV2.RESPONSE_CODE_OK)
            {
                Log.e("geofencesdataCode",""+responseCode);// Toast.makeText(context, "data null", Toast.LENGTH_SHORT).show();

                List<Data>  data= geoCercasModelresponse.getData();
                if(data!=null)//data
                {

                    if(data.size()>0)
                    {

                       presenter.setDataofGeozones(data);
                       presenter.hideProgressDialog();
                    }

                }else{Log.e("geofencesdatanull",""+data.size());// Toast.makeText(context, "data null", Toast.LENGTH_SHORT).show();
                }
            }

        }

    }
}
