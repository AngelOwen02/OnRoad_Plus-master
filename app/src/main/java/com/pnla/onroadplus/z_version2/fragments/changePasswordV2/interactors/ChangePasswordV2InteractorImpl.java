package com.pnla.onroadplus.z_version2.fragments.changePasswordV2.interactors;

import android.content.Context;
import android.os.Bundle;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.fragments.changePasswordV2.interfaces.ChangePasswordV2Interactor;
import com.pnla.onroadplus.z_version2.fragments.changePasswordV2.interfaces.ChangePasswordV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.changePasswordV2.interfaces.ChangePasswordV2Services;
import com.pnla.onroadplus.z_version2.fragments.changePasswordV2.models.ChangePasswordV2Request;
import com.pnla.onroadplus.z_version2.fragments.changePasswordV2.models.ChangePasswordV2Response;
import com.pnla.onroadplus.z_version2.fragments.changePasswordV2.utils.ChangePasswordV2Validations;
import com.pnla.onroadplus.z_version2.fragments.loginV2.utils.UtilsLoginV2;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChangePasswordV2InteractorImpl implements ChangePasswordV2Interactor {

    private ChangePasswordV2Presenter presenter;
    private Context context;
    private ChangePasswordV2Services services;
    private Retrofit retrofitClient;

    public ChangePasswordV2InteractorImpl(ChangePasswordV2Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        services = retrofitClient.create(ChangePasswordV2Services.class);
    }

    //region getDataFromBundle
    @Override
    public void getDataFromBundle(Bundle bundle) {
        if (bundle != null) {
            String token = bundle.getString(GeneralConstantsV2.TOKEN);
            String email = bundle.getString(GeneralConstantsV2.EMAIL);
            presenter.setUserDataToView(token, email);
        } else {
            presenter.setMessageToView(context.getString(R.string.textGetEmptyUserData));
        }
    }
    //endregion getDataFromBundle

    //region validateChangePasswordData
    @Override
    public void validateChangePasswordData(String token, String password1, String password2) {
        String resultValidationToken = ChangePasswordV2Validations.validateToken(token, context);
        if (resultValidationToken.equals(GeneralConstantsV2.SUCCESS)) {
            String resultValidationsPasswords = ChangePasswordV2Validations.validatePasswords(password1, password2, context);
            if (resultValidationsPasswords.equals(GeneralConstantsV2.SUCCESS)) {
                startChangePasswordRequest(token, password1);
            } else {
                presenter.setMessageToView(resultValidationsPasswords);
            }
        } else {
            presenter.setMessageToView(resultValidationToken);
        }
    }
    //endregion validateChangePasswordData

    //region startChangePasswordRequest
    private void startChangePasswordRequest(String token, String newPassword) {
        ChangePasswordV2Request request = new ChangePasswordV2Request(newPassword, token);
        services.changePasswordService(request).enqueue(new Callback<ChangePasswordV2Response>() {
            @Override
            public void onResponse(Call<ChangePasswordV2Response> call, Response<ChangePasswordV2Response> response) {
                validateChangePasswordCode(response);
            }

            @Override
            public void onFailure(Call<ChangePasswordV2Response> call, Throwable t) {
                presenter.setMessageToView(RetrofitValidationsV2.getOnFailureResponse(t, context));
            }
        });
    }
    //endregion startChangePasswordRequest

    //region validateChangePasswordCode
    private void validateChangePasswordCode(Response<ChangePasswordV2Response> response) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getChangePasswordResponse(response);
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }
    //endregion validateChangePasswordCode

    //region getChangePasswordResponse
    private void getChangePasswordResponse(Response<ChangePasswordV2Response> response) {
        ChangePasswordV2Response changePasswordV2Response = response.body();
        if (changePasswordV2Response != null) {
            int responseCode = changePasswordV2Response.getResponseCode();
            if (responseCode == 105) {
                UtilsLoginV2.saveUserDataSharedPreferences(null, null, null, null, null, null, "1", context);
                presenter.successChangePassword();
            } else {
                presenter.setMessageToView(changePasswordV2Response.getMessage());
            }
        } else {
            presenter.setMessageToView("Error, no se puedo obtener respuesta del servidor.");
        }
    }
    //endregion getChangePasswordResponse
}
