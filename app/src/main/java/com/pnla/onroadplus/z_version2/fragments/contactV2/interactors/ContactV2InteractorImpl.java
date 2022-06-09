package com.pnla.onroadplus.z_version2.fragments.contactV2.interactors;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.fragments.contactV2.interfaces.ContactV2Interactor;
import com.pnla.onroadplus.z_version2.fragments.contactV2.interfaces.ContactV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.contactV2.interfaces.ContactV2Services;
import com.pnla.onroadplus.z_version2.fragments.contactV2.models.ContactV2Request;
import com.pnla.onroadplus.z_version2.fragments.contactV2.models.ContactV2Response;
import com.pnla.onroadplus.z_version2.fragments.contactV2.utils.ContactV2Utils;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ContactV2InteractorImpl implements ContactV2Interactor {

    private ContactV2Presenter presenter;
    private ContactV2Services services;
    private Retrofit retrofitClient;

    public ContactV2InteractorImpl(ContactV2Presenter presenter) {
        this.presenter = presenter;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        services = retrofitClient.create(ContactV2Services.class);
    }

    @Override
    public void getUserEmail(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String email = preferences.getString(GeneralConstantsV2.EMAIL_PREFERENCES, null);
        Log.e("EEEE", email);
        presenter.setUserEmail(email);
    }

    @Override
    public void validateUserDataToSend(String to, String subject, String message, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        String resultValidation = ContactV2Utils.validateContactData(to, subject, message, token, context);
        if (resultValidation.equals(GeneralConstantsV2.SUCCESS)) {
            startContactRequest(to, subject, message, token, context);
        } else {
            presenter.setMessageToView(resultValidation);
        }
    }

    private void startContactRequest(String to, String subject, String message, String token, final Context context) {
        ContactV2Request request = new ContactV2Request(message, subject, to, token);
        services.sendEmail(request).enqueue(new Callback<ContactV2Response>() {
            @Override
            public void onResponse(Call<ContactV2Response> call, Response<ContactV2Response> response) {
                validateCode(response, context);
            }

            @Override
            public void onFailure(Call<ContactV2Response> call, Throwable t) {
                presenter.setMessageToView(RetrofitValidationsV2.getOnFailureResponse(t, context));
            }
        });
    }

    private void validateCode(Response<ContactV2Response> response, Context context) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getContactData(response, context);
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }

    private void getContactData(Response<ContactV2Response> response, Context context) {
        ContactV2Response contactV2Response = response.body();
        if (contactV2Response != null) {
            int responseCode = contactV2Response.getResponseCode();
            if (responseCode == 105) {
                presenter.successContactService();
            } else if (responseCode == 104) {
                presenter.sessionExpired(context.getString(R.string.textSessionExpired));
            } else {
                presenter.setMessageToView(contactV2Response.getMessage());
            }
        } else {
            presenter.setMessageToView(context.getString(R.string.textEmptyResponse));
        }
    }

}
