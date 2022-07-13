package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.pnla.onroadplus.z_version2.Containers.ModelVersion.Version;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.data.Guardar;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.presenter.UnitAssignSupportAsigmentsPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.presenter.UnitAssignSupportAsigmentsPresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SingleSupportUnitData;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SingleSupportUnitRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SingleSupportUnitResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SupportUnitRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SupportUnitResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.UnitService;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UnitAssignSupportAsigmentsInteractorImpl implements UnitAssignSupportAsigmentsInteractor {

    private Context context;
    private UnitAssignSupportAsigmentsPresenter presenter;
    private UnitService unitService;

    public UnitAssignSupportAsigmentsInteractorImpl(UnitAssignSupportAsigmentsPresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
        initRetrofit();
    }

    private void initRetrofit() {
        Retrofit retrofitClient = RetrofitClientV2.getRetrofitInstance();
        unitService = retrofitClient.create(UnitService.class);
        //Toast.makeText(context, "paso por aqui retrofit", Toast.LENGTH_LONG).show();
    }

    @Override
    public void requestSoportes(int cveLayer) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null) {
            //requestSoportes(token);
            requestUnidades(token, cveLayer);
        }
    }

    private void requestUnidades(String token, int cveLayer) {
        SingleSupportUnitRequest request = new SingleSupportUnitRequest(cveLayer, token);
        unitService.getFullSingleVehiclesSupport(request).enqueue(new Callback<SingleSupportUnitResponse>() {
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

    private void validateCode(Response<SingleSupportUnitResponse> response, Context context) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            //getVehiclesData(response, context);
            getSupportData(response, context);
            //Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void getSupportData(Response<SingleSupportUnitResponse> response, Context context) {
        SingleSupportUnitResponse singleSupportUnitResponse = response.body();
        if(singleSupportUnitResponse != null){
            int responseCode = singleSupportUnitResponse.getResponseCode();
            if(responseCode == GeneralConstantsV2.RESPONSE_CODE_OK){
                List<SingleSupportUnitData> data = singleSupportUnitResponse.getData();
                presenter.setSoportes(data);
            }
        }
    }
}
