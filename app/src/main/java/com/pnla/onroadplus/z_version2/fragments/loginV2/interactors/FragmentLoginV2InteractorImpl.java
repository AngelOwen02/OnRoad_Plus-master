package com.pnla.onroadplus.z_version2.fragments.loginV2.interactors;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.fragments.loginV2.interfaces.FragmentLoginV2Interactor;
import com.pnla.onroadplus.z_version2.fragments.loginV2.interfaces.FragmentLoginV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.loginV2.interfaces.LoginServicesV2;
import com.pnla.onroadplus.z_version2.fragments.loginV2.models.LoginRequestV2;
import com.pnla.onroadplus.z_version2.fragments.loginV2.models.LoginResponseV2;
import com.pnla.onroadplus.z_version2.fragments.loginV2.models.UserDataV2;
import com.pnla.onroadplus.z_version2.fragments.loginV2.utils.LoginV2Validations;
import com.pnla.onroadplus.z_version2.fragments.loginV2.utils.UtilsLoginV2;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmUserData;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FragmentLoginV2InteractorImpl implements FragmentLoginV2Interactor {

    private FragmentLoginV2Presenter presenter;
    private Context context;
    private LoginServicesV2 services;
    private Retrofit retrofitClient;

    public FragmentLoginV2InteractorImpl(FragmentLoginV2Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        services = retrofitClient.create(LoginServicesV2.class);
    }

    @Override
    public void getUserDataPreferences() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);

        String userName = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);
    //    Log.e("checkinguser","splash"+userName+ UserDataDB.getUserData().getEmployee_name());
        String password = preferences.getString(GeneralConstantsV2.PASSWORD_PREFERENCES, null);
        String userClosedSessionSuccess = preferences.getString(GeneralConstantsV2.CLOSE_SESSION_PREFERENCES, null);
        //SI EL USUARIO CERRO SESION CON EXITO, NO SE REALIZA LOGIN, CASO CONTRARIO SI
        if (userClosedSessionSuccess != null && userClosedSessionSuccess.equals("NO")) {
            presenter.setUserData(userName, password);
            presenter.showLoaderFromInteractor();
            validateData(userName, password);
        } else {
            presenter.setUserData(userName, password);
        }
    }

    @Override
    public void validateData(String user, String password) {
        String resultValidation = LoginV2Validations.validateUserAndPassword(user, password);
        if (resultValidation.equals(GeneralConstantsV2.SUCCESS)) {
            startLoginRequest(user, password);
        } else {
            presenter.setMessageToView(context.getString(R.string.textEmptyDataLogin));
        }
    }

    /**
     * Start loginRequest
     *
     * @param user
     * @param password
     */
    private void startLoginRequest(final String user, final String password) {
        LoginRequestV2 request = new LoginRequestV2(user, password);
        services.login(request).enqueue(new Callback<LoginResponseV2>() {
            @Override
            public void onResponse(Call<LoginResponseV2> call, Response<LoginResponseV2> response) {
                validateCode(response, user, password);
            }

            @Override
            public void onFailure(Call<LoginResponseV2> call, Throwable t) {
                presenter.setMessageToView(RetrofitValidationsV2.getOnFailureResponse(t, context));
            }
        });
    }

    /**
     * ValidateCode
     *
     * @param response
     */
    private void validateCode(Response<LoginResponseV2> response, String user, String password) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getUserData(response, user, password);
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }

    /**
     * getUserData
     *
     * @param response
     */
    private void getUserData(Response<LoginResponseV2> response, String user, String password) {
        LoginResponseV2 loginResponse = response.body();
        if (loginResponse != null) {
            int responseCode = loginResponse.getResponseCode();
            if (responseCode == 100) {
                UserDataV2 userData = loginResponse.getData();
                if (userData != null) {
                    boolean isFirstLogin = userData.getFirstLogin();
                    String email = userData.getEmail();
                    String token = userData.getToken();
                    if (isFirstLogin) {
                        Bundle bundle = new Bundle();
                        bundle.putString(GeneralConstantsV2.EMAIL, email);
                        bundle.putString(GeneralConstantsV2.TOKEN, token);
                        UtilsLoginV2.saveUserDataSharedPreferences(null, null, null, null, null, null, context);
                        presenter.showFragmentChangePasswordV2(bundle);
                    } else {
                        String urlUserImage = userData.getUserImage();
                        String employeeName = userData.getEmployeeName();
                        if (urlUserImage == null || urlUserImage.length() == 0) {
                            urlUserImage = GeneralConstantsV2.NO_IMAGE;
                        }
                        /**
                         *      Si el usuario no esta registrado en la db del telefono entonces lo guardamos en db
                         *      Y luego en SharePreferences, esto se realiza para la validación del fragment del mapa
                         *      donde se muestra solo las 30 primeras unidades
                         */
                        if (!RealmUserData.existUser(user)) {
                            RealmUserData.saveUser(user, email, password, GeneralConstantsV2.IS_FIRST_TIME);
                        }
                        UtilsLoginV2.saveUserDataSharedPreferences(urlUserImage, user, token, email, password, employeeName, context);
                        presenter.successLogin();
                    }
                } else {
                    presenter.setMessageToView(context.getString(R.string.textEmptyDataResponse));
                }
            } else if (responseCode == 101) {
                presenter.setMessageToView( context.getString(R.string.textWrongUserDataLogin));
            } else {
                presenter.setMessageToView(loginResponse.getMessage());
            }
        } else {
            presenter.setMessageToView("Error, no se pudo obtener respuesta del servidor.");
        }
    }

}
