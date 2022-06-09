package com.pnla.onroadplus.z_version2.MenuFragments.RecoveryPassword.interactor;

import android.content.Context;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.RecoveryPassword.presenter.RecoveryPasswordPresenter;
import com.pnla.onroadplus.z_version2.fragments.restorePasswordV2.interfaces.RestorePasswordV2Services;
import com.pnla.onroadplus.z_version2.fragments.restorePasswordV2.models.RestorePasswordV2Request;
import com.pnla.onroadplus.z_version2.fragments.restorePasswordV2.models.RestorePasswordV2Response;
import com.pnla.onroadplus.z_version2.fragments.restorePasswordV2.utils.RestorePasswordV2Validations;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecoveryPasswordInteractorImpl implements  RecoveryPasswordInteractor {

    private Context context;
    private RecoveryPasswordPresenter presenter;
    private RestorePasswordV2Services services;


    public RecoveryPasswordInteractorImpl(RecoveryPasswordPresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
        Retrofit retrofitClient = RetrofitClientV2.getRetrofitInstance();
        services = retrofitClient.create(RestorePasswordV2Services.class);
    }

    @Override
    public void validateRestorePasswordData(String email) {
        String resultValidationSize = RestorePasswordV2Validations.validateEmailSize(context, email);
        if (resultValidationSize.equals(GeneralConstantsV2.SUCCESS)) {
            String resultValidationFormat = RestorePasswordV2Validations.validateEmailFormat(context, email);
            if (resultValidationFormat.equals(GeneralConstantsV2.SUCCESS)) {
                startRestorePasswordRequest(email);
            } else {
                presenter.setMessageToView(resultValidationFormat);
            }
        } else {
            presenter.setMessageToView(resultValidationSize);
        }
    }

    private void startRestorePasswordRequest(String email) {
        RestorePasswordV2Request request = new RestorePasswordV2Request(email);
        services.restorePasswordService(request).enqueue(new Callback<RestorePasswordV2Response>() {
            @Override
            public void onResponse(Call<RestorePasswordV2Response> call, Response<RestorePasswordV2Response> response) {
                validateRestorePasswordCode(response);
            }

            @Override
            public void onFailure(Call<RestorePasswordV2Response> call, Throwable t) {
                presenter.setMessageToView(RetrofitValidationsV2.getOnFailureResponse(t, context));
            }
        });
    }

    private void validateRestorePasswordCode(Response<RestorePasswordV2Response> response) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            successRestorePassword(response);
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }

    private void successRestorePassword(Response<RestorePasswordV2Response> response) {
        RestorePasswordV2Response restorePasswordV2Response = response.body();
        if (restorePasswordV2Response != null) {
            int responseCode = restorePasswordV2Response.getResponseCode();
            if (responseCode == 105) {
                presenter.successRestorePassword();
            } else if (responseCode == 107) {
                presenter.setMessageToView(context.getString(R.string.textNotFoundEmail));
            } else {
                presenter.setMessageToView(restorePasswordV2Response.getMessage());
            }
        } else {
            presenter.setMessageToView("Ocurrió un error al intentar restaurar la contraseña.");
        }
    }
}
