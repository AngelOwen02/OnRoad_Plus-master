package com.pnla.onroadplus.z_version2.MenuFragments.Contact.interactor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Contact.presenter.ContactPresenter;
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

public class ContactInteractorImpl implements ContactInteractor {

    Context context;

    private ContactPresenter presenter;
    private ContactV2Services services;


    public ContactInteractorImpl(ContactPresenter presenter, Context context){
        this.context = context;
        this.presenter = presenter;
        Retrofit retrofitClient = RetrofitClientV2.getRetrofitInstance();
        services = retrofitClient.create(ContactV2Services.class);
    }

    @Override
    public void getUserEmail(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String email = preferences.getString(GeneralConstantsV2.EMAIL_PREFERENCES, null);
        presenter.setUserEmail(email);
    }

    @Override
    public void getSupportEmail() {
        String supportEmail = "support@gpsphoenix.com";
        presenter.setSupportEmail(supportEmail);
    }

    @Override
    public void validateUserDataToSend(String to, String subject, String message, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        String resultValidation = ContactV2Utils.validateContactData(to, subject, message, token, context);
        if (subject == null || subject.length() == 0 || message == null || message.length() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogCustom);
            builder.setMessage("Es necesario ingresar asunto y mensaje");
            builder.setCancelable(false);
            builder.setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        }
        else if (resultValidation.equals(GeneralConstantsV2.SUCCESS)){
            startContactRequest(to, subject, message, token, context);
        }
        presenter.setMessageToView(resultValidation);
    }

    @Override
    public void getFonts() {
        Typeface robotoRegular = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface robotoMedium = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto_Medium.ttf");
        Typeface robotoLight = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto_Light.ttf");

        presenter.setFonts(robotoLight, robotoRegular, robotoMedium);

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

    public void getSendConfirmationDialog(){

        AlertDialog.Builder sendDialog = new AlertDialog.Builder(context, R.style.AlertDialogCustom);
        sendDialog.setMessage("Su mensaje ha sido enviado correctamente");
        sendDialog.setCancelable(false);
        sendDialog.setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        sendDialog.show();
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
