package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model.checkListResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model.checkListdata;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Util.serviceChecklist;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.model.DataGroups;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.model.responseGroups;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.presenter.presenterVehicleInGroups;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.presenter.presenterVehicleInGroupsImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.util.serviceVechileGroups;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.GroupRequest;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class interactorVehiclesinGroupsImpl implements  interactorVehicleinGroups {
    private presenterVehicleInGroupsImpl presenter;
    private Context context;
    private serviceVechileGroups service;
    private Retrofit retrofitClient;
    public interactorVehiclesinGroupsImpl(presenterVehicleInGroupsImpl presenter, Context context) {
        this.context=context;
        this.presenter=presenter;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        service = retrofitClient.create(serviceVechileGroups.class);
    }

    @Override
    public void getGroups() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            requestGroupsV2(token);
        }
    }

    private void requestGroupsV2(String token) {
        GroupRequest request= new GroupRequest(token);
        Call<responseGroups> call = service.getGroups(request);
        call.enqueue(new Callback<responseGroups>() {
            @Override
            public void onResponse(Call<responseGroups> call, Response<responseGroups> response) {
                validateCodeChecklist(response,context);
            }

            @Override
            public void onFailure(Call<responseGroups> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateCodeChecklist(Response<responseGroups> response, Context context) {
        if (response != null) {
            if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                groupsList(response, context);
            } else {
                Toast.makeText(context, "" + RetrofitValidationsV2.getErrorByStatus(response.code(), context), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void groupsList(Response<responseGroups> response, Context context) {
        responseGroups resp=response.body();
        if(resp!=null) {
            int code = resp.getResponseCode();
            String message = resp.getMessage();
            DataGroups data=resp.getData();
            if (code == GeneralConstantsV2.RESPONSE_CODE_OK) {//105
                if (data != null) {
                    presenter.setGroups(data);
                   // presenter.hideDialog();
                } else {
                   // presenter.hideDialog();
                    Toast.makeText(context, "" + resp.getMessage() + " " , Toast.LENGTH_SHORT).show();
                }
            } else {//106 107 104 101
               //presenter.hideDialog();
                Toast.makeText(context, "" + resp.getMessage() + " ", Toast.LENGTH_SHORT).show();
            }
        } else { //body null
            //presenter.hideDialog();
            Toast.makeText(context, ""+resp.getMessage()+" ", Toast.LENGTH_SHORT).show();
        }
    }
}
