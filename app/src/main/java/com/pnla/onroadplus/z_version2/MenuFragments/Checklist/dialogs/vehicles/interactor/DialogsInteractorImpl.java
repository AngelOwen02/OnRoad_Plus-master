package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.presenter.*;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.model.*;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.util.*;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DialogsInteractorImpl implements DialogsInteractor {

    private Context context;
    private DialogsPresenter presenter;
    private ServiceDialogs service;

    private Retrofit retrofitClient;

    public DialogsInteractorImpl(DialogsPresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        service = retrofitClient.create(ServiceDialogs.class);
    }

    @Override
    public void requestVehicles() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        Boolean flagParent = true;
        if(token!=null) {
            requestfullVehicles(token, flagParent);
        }
    }

    private void requestfullVehicles(String token, Boolean flagParent) {
        DialogsRequest request = new DialogsRequest(flagParent, token);
        //presenter.showDialog();
        Call<DialogsResponse> call = service.getVehiclesDialog(request);
        call.enqueue(new Callback<DialogsResponse>() {
            @Override
            public void onResponse(Call<DialogsResponse> call, Response<DialogsResponse> response) {
                validateCodeVehicles(response, context);
            }

            @Override
            public void onFailure(Call<DialogsResponse> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateCodeVehicles(Response<DialogsResponse> response, Context context) {
        if(response!=null){
            if(RetrofitValidationsV2.checkSuccessCode(response.code())) {
                vehicleList(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context),Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void vehicleList(Response<DialogsResponse> response, Context context) {
        DialogsResponse responseD = response.body();
        if(responseD!=null) {
            int code = responseD.getResponseCode();
            String message = responseD.getMessage();
            List<DialogsData> data = responseD.getData();
            if(code == GeneralConstantsV2.RESPONSE_CODE_OK) {
                if(data!=null) {
                    presenter.setVehicles(data);
                    presenter.hideDialog();
                } else {
                    presenter.hideDialog();
                    //Toast.makeText("" + responseD.getMessage() + " " + responseD.getResponseCode(), Toast.LENGTH_SHORT).show();
                }
            } else {
                presenter.hideDialog();
                //Toast.makeText("" + responseD.getMessage() + " " + responseD.getResponseCode(), Toast.LENGTH_SHORT).show();
            }
        } else {
            presenter.hideDialog();
            //Toast.makeText("" + responseD.getMessage() + " " + responseD.getResponseCode(), Toast.LENGTH_SHORT).show();
        }
    }
}
