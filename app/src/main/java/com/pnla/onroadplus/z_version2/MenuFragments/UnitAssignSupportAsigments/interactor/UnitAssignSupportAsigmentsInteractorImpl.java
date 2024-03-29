package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.Models.DeleteUnitAssignRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.Models.DeleteUnitAssignResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.Models.UnitAssignRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.Models.UnitAssignResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.Utils.UnitAssignService;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.presenter.UnitAssignSupportAsigmentsPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SingleSupportUnitData;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SingleSupportUnitRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SingleSupportUnitResponse;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UnitAssignSupportAsigmentsInteractorImpl implements UnitAssignSupportAsigmentsInteractor {

    private Context context;
    private UnitAssignSupportAsigmentsPresenter presenter;
    //private UnitService unitService;
    private UnitAssignService unitAssignService;

    public UnitAssignSupportAsigmentsInteractorImpl(UnitAssignSupportAsigmentsPresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
        initRetrofit();
    }

    //region initRetrofit
    private void initRetrofit() {
        Retrofit retrofitClient = RetrofitClientV2.getRetrofitInstance();
        //unitService = retrofitClient.create(UnitService.class);
        unitAssignService = retrofitClient.create(UnitAssignService.class);
        //Toast.makeText(context, "paso por aqui retrofit", Toast.LENGTH_LONG).show();
    }
    //endregion initRetrofit

    //region requestSoportes
    @Override
    public void requestSoportes(int cveLayer) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null) {
            //requestSoportes(token);
            requestUnidades(token, cveLayer);
        }
    }
    //endregion requestSoportes

    //region setAssignSupport
    @Override
    public void setAssignSupport(Integer layer, int cveLayer, String cve_vehicle) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null) {
            Log.e("TokenAssigmentSupport", "" + token);
            requesAssignSupport(layer, cveLayer, cve_vehicle, token);
            //Log.e("assistencesupport" , "" + cveLayer + "   " +  cveLayer + "  "+ cve_vehicle + "   "+ token);
        }
    }
    //endregion setAssignSupport

    //region deleteUnitAssign
    @Override
    public void deleteUnitAssign(int cveLayer, String cve_vehicle, int cve_layer_support) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null) {

            deleteSupport(cve_layer_support, cve_vehicle, token);
            //Log.e("assistencesupport" , "" + cveLayer + "   " +  cveLayer + "  "+ cve_vehicle + "   "+ token);
        }
    }
    //endregion deleteUnitAssign

    //region deleteSupport
    private void deleteSupport(int cveLayer, String cve_vehicle, String token) {
        DeleteUnitAssignRequest request = new DeleteUnitAssignRequest(cveLayer, Integer.valueOf(cve_vehicle), token);
        //unitService.deleteAssignSupport(request).enqueue(new Callback<DeleteUnitAssignResponse>() {
        unitAssignService.deleteAssignSupport(request).enqueue(new Callback<DeleteUnitAssignResponse>() {
            @Override
            public void onResponse(Call<DeleteUnitAssignResponse> call, Response<DeleteUnitAssignResponse> response) {
                validateCodeDelete(response, context);
            }

            @Override
            public void onFailure(Call<DeleteUnitAssignResponse> call, Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
    //endregion deleteSupport

    //region validateCodeDelete
    private void validateCodeDelete(Response<DeleteUnitAssignResponse> response, Context context) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            deleteSupportAssign(response, context);
        } else {
            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    //endregion validateCodeDelete

    //region deleteSupportAssign
    private void deleteSupportAssign(Response<DeleteUnitAssignResponse> response, Context context) {
        DeleteUnitAssignResponse response2 = response.body();
        if(response2 !=null){
            int responseCode = response2.getResponseCode();
            String message = response2.getMessage();
            String data = response2.getData();
            if(responseCode == GeneralConstantsV2.RESPONSE_CODE_OK){
                presenter.deleteSuccess(data);
            } else {
                //Toast.makeText(context, response.code(), Toast.LENGTH_LONG).show();
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        } else {
            presenter.hideProgressDialog();
            Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    //endregion deleteSupportAssign

    //region requesAssignSupport
    private void requesAssignSupport(int cveLayer, int cveLayer1, String cve_vehicle, String token) {
         final UnitAssignRequest request = new UnitAssignRequest(cveLayer1, cveLayer, Integer.valueOf(cve_vehicle), token);
         //unitService.setUnitAssignSupport(request).enqueue(new Callback<UnitAssignResponse>() {
        unitAssignService.setUnitAssignSupport(request).enqueue(new Callback<UnitAssignResponse>() {
             @Override
             public void onResponse(Call<UnitAssignResponse> call, Response<UnitAssignResponse> response) {
                 validateCodeAssignent(response, context);
             }

             @Override
             public void onFailure(Call<UnitAssignResponse> call, Throwable t) {
                 Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
             }
         });
    }
    //endregion requesAssignSupport

    //region validateCodeAssignent
    private void validateCodeAssignent(Response<UnitAssignResponse> response, Context context) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            setAssignSupportAssigment(response, context);
        } else {
            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    //endregion validateCodeAssignent

    //region setAssignSupportAssigment
    private void setAssignSupportAssigment(Response<UnitAssignResponse> response, Context context) {
        UnitAssignResponse response1 = response.body();
        if(response1 != null){
            int responseCode = response1.getResponseCode();
            String message = response1.getMessage();
            String data = response1.getData();
            if(responseCode == GeneralConstantsV2.RESPONSE_CODE_OK){
                presenter.setUnitAssignSupport(data);
            } else {
                presenter.hideProgressDialog();
                Toast.makeText(context, ""+response.code(), Toast.LENGTH_LONG).show();
                //Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        } else {
            presenter.hideProgressDialog();
            Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    //endregion setAssignSupportAssigment

    //region requestUnidades
    private void requestUnidades(String token, int cveLayer) {
        SingleSupportUnitRequest request = new SingleSupportUnitRequest(cveLayer, token);
        //unitService.getFullSingleVehiclesSupport(request).enqueue(new Callback<SingleSupportUnitResponse>() {
        unitAssignService.getFullSingleVehiclesSupport(request).enqueue(new Callback<SingleSupportUnitResponse>() {
            @Override
            public void onResponse(Call<SingleSupportUnitResponse> call, Response<SingleSupportUnitResponse> response) {
                validateCode(response, context);
            }

            @Override
            public void onFailure(Call<SingleSupportUnitResponse> call, Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
    //endregion requestUnidades

    //region validateCode
    private void validateCode(Response<SingleSupportUnitResponse> response, Context context) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            //getVehiclesData(response, context);
            getSupportData(response, context);
            //Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    //endregion validateCode

    //region getSupportData
    private void getSupportData(Response<SingleSupportUnitResponse> response, Context context) {
        SingleSupportUnitResponse singleSupportUnitResponse = response.body();
        if(singleSupportUnitResponse != null) {
            int responseCode = singleSupportUnitResponse.getResponseCode();
            if(responseCode == GeneralConstantsV2.RESPONSE_CODE_OK){
                List<SingleSupportUnitData> data = singleSupportUnitResponse.getData();
                presenter.setSoportes(data);
            } else {
                presenter.hideProgressDialog();
                Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    //endregion getSupportData
}
