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

/**import com.dynatrace.android.agent.DTXAction;
import com.dynatrace.android.agent.Dynatrace;*/
import com.pnla.onroadplus.BuildConfig;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.Containers.LoginContainer.view.LoginContainerActivity;
import com.pnla.onroadplus.z_version2.Containers.ModelVersion.Version;
import com.pnla.onroadplus.z_version2.Containers.ModelVersion.VersionRequest;
import com.pnla.onroadplus.z_version2.Containers.ModelVersion.VersionResponse;
import com.pnla.onroadplus.z_version2.Containers.ModelVersion.VersionService;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.presenter.UnitAssignSupportPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.Utils.UnitAssignService;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.FinalUnitDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.UnitDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.GeoreferenceRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.GeoreferenceResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SupportUnitData;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SupportUnitRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SupportUnitResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.UnitService;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
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

    private UnitAssignSupportPresenter presenter;
    private UnitAssignService unitAssignService;
    private Context context;
    private Retrofit retrofitClient;

    public Version version;
    private String name = "OnRoad";
    private List<String> versiones;


    public UnitAssignSupportInteractorImpl(UnitAssignSupportPresenter presenter, Context context) {
        this.context = context;
        this.presenter = presenter;
        initRetrofit();
        requestSoportes();
    }
    private void initRetrofit() {
        Retrofit retrofitClient = RetrofitClientV2.getRetrofitInstance();
        unitAssignService = retrofitClient.create(UnitAssignService.class);
    }
    @Override
    public void requestSoportes(){

        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null) {
            requestSoportes(token);
        }
    }
    private void requestSoportes(String token) {
        SupportUnitRequest request = new SupportUnitRequest(token);
        presenter.showProgressDialog();
        if(token!=null) {
            unitAssignService.getFullVehiclesSupport(request).enqueue(new Callback<SupportUnitResponse>() {
                @Override
                public void onResponse(Call<SupportUnitResponse> call, Response<SupportUnitResponse> response) {
                    try {
                        validateCode(response, context);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<SupportUnitResponse> call, Throwable t) {
                    Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void validateCode(Response<SupportUnitResponse> response, Context context) throws IOException {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getSupportData(response, context);
        } else {
            presenter.failureResponse(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
            Toast.makeText(context,  "sesion expirada3", Toast.LENGTH_LONG).show();
            presenter.hideProgressDialog();
        }
    }
    private void getSupportData(Response<SupportUnitResponse> response, Context context) {
        SupportUnitResponse supportUnitResponse = response.body();
        if (supportUnitResponse != null) {
            int responseCode = supportUnitResponse.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                List<SupportUnitData> data = supportUnitResponse.getData();
                if(data!=null) {
                    presenter.setSoportes(data);
                    presenter.hideProgressDialog();
                }else{
                    presenter.hideProgressDialog();
                }

            }else
            {
                presenter.hideProgressDialog();
                presenter.goBackMap();
                Toast.makeText(context, "Sin zonas asignadas" , Toast.LENGTH_SHORT).show();
            }
        } else {
            presenter.hideProgressDialog();
            Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
}
