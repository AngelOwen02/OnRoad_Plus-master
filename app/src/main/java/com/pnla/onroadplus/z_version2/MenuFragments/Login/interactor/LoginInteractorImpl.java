package com.pnla.onroadplus.z_version2.MenuFragments.Login.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.dynatrace.android.agent.Dynatrace;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Login.model.AuditTrail;
import com.pnla.onroadplus.z_version2.MenuFragments.Login.model.responseAuditTrail;
import com.pnla.onroadplus.z_version2.MenuFragments.Login.model.setAuditTrail;
import com.pnla.onroadplus.z_version2.MenuFragments.Login.presenter.LoginPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.adapter.GroupTrackingAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.adapter.UnitTrackingAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Group.GroupDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.UnitDB;
import com.pnla.onroadplus.z_version2.UserDataDB;
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

public class LoginInteractorImpl implements LoginInteractor {

    private LoginPresenter presenter;
    private Context context;
    private LoginServicesV2 services;

    public LoginInteractorImpl(LoginPresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
        Retrofit retrofitClient = RetrofitClientV2.getRetrofitInstance();
        services = retrofitClient.create(LoginServicesV2.class);
    }

    @Override
    public void getUserDataPreferences() {

        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String userName = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);

        String password = preferences.getString(GeneralConstantsV2.PASSWORD_PREFERENCES, null);
        String userClosedSessionSuccess = preferences.getString(GeneralConstantsV2.CLOSE_SESSION_PREFERENCES, null);

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

        if(UserDataDB.getUserData()!=null) {
            Log.e("checkinguser", "info conparativa   " + UserDataDB.getUserData().getUser_cve() );
        }
        if (resultValidation.equals(GeneralConstantsV2.SUCCESS)) {
            startLoginRequest(user, password);
            Dynatrace.identifyUser( String.valueOf(user));
            Log.e("dynatracelog", "info conparativa   " + user);

        } else {
            presenter.setMessageToView(context.getString(R.string.textEmptyDataLogin));
        }
    }

    @Override
    public void newsetAuditTrail(String name) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            myauditTrail(name,token);
        }
    }
    private void myauditTrail(String name ,String token)
    {
        AuditTrail mynewAuditTrail=new AuditTrail("Onroad_Login","Login","login con "+name);
        setAuditTrail request=new setAuditTrail(mynewAuditTrail,token);
        services.auditTrail(request).enqueue(new Callback<responseAuditTrail>() {
            @Override
            public void onResponse(Call<responseAuditTrail> call, Response<responseAuditTrail> response) {
                validateCodeauditTrail(response,context);
            }

            @Override
            public void onFailure(Call<responseAuditTrail> call, Throwable t) {
                presenter.setMessageToView(RetrofitValidationsV2.getOnFailureResponse(t, context));
            }
        });
    }

    private  void  validateCodeauditTrail(Response<responseAuditTrail> response,Context context)
    {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            responseSetAuditTrial(response,context);
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }
    private void responseSetAuditTrial(Response<responseAuditTrail> response,Context context)
    {
        responseAuditTrail auditResponse=response.body();
        if(auditResponse!=null)
        {
            int responseCode=auditResponse.getResponseCode();
            String message=auditResponse.getMessage();
            if(responseCode==GeneralConstantsV2.RESPONSE_CODE_OK)
            {

            }
        }

    }
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

    private void validateCode(Response<LoginResponseV2> response, String user, String password) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getUserData(response, user, password);
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }

    private void getUserData(Response<LoginResponseV2> response, String user, String password) {
        LoginResponseV2 loginResponse = response.body();
        if (loginResponse != null) {
            int responseCode = loginResponse.getResponseCode();
            if (responseCode == 100) {
                UserDataV2 userData = loginResponse.getData();
                if (userData != null) {

                    String nameuser=userData.getUser_cve();
                    Log.e("dynatracelog", "info conparativa   " + nameuser);
                    Dynatrace.identifyUser( nameuser);
                    Log.e("checkinguser", "info conparativa   " + user+":"+nameuser );
                  if(UserDataDB.getUserData()!=null) {


                      if (UserDataDB.getUserData().getUser_cve().equals(nameuser)) {
                          Log.e("checkinguser", "es lo mismo");
                      } else {
                          Log.e("checkinguser", "si es diferente :" + user + ":   :" + userData.getUser_cve() + ":");
                          UnitDB.deleteDB();
                          UserDataDB.deleteDB();
                          GroupDB.deleteDB();
                          RealmUserData.deleteDB();
                          UnitTrackingAdapter.integerList.clear();
                          GroupTrackingAdapter.integerList1.clear();
                          Log.e("checkinguser", "DAta: " + userData.getUser_cve());
                      }
                  }
                  //  RealmList unitRealmList = new RealmList();
                    userData.getEmployeeName();
                    if (UserDataDB.isEmpty()){
                        UserDataDB.createUserData(userData.getEmployeeName(), userData.getFirstLogin(), userData.getUserImage(), userData.getToken(), userData.getEmail(),userData.getUser_cve());
                    }



                    boolean isFirstLogin = userData.getFirstLogin();
                    String email = userData.getEmail();
                    String token = userData.getToken();

                    String name = userData.getEmployeeName();

                    SharedPreferences prefs = context.getSharedPreferences("Haha",Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = prefs.edit();
                    Log.e("FIRsTTT", String.valueOf(userData.getFirstLogin()));
                    editor.putBoolean("isFirst", true);
                    editor.commit();


                    if (isFirstLogin) {
                        Bundle bndl = new Bundle();
                        bndl.putString("newEmail", email);
                        bndl.putString("newToken", token);
                        presenter.showFragmentChangePasswordV2(bndl);
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
                        presenter.successLogin(user);
                    }
                } else {
                    presenter.setMessageToView(context.getString(R.string.textEmptyDataResponse));
                }
            } else if (responseCode == 101) {
                presenter.setMessageToView(context.getString(R.string.textWrongUserDataLogin));
            } else {
                presenter.setMessageToView(loginResponse.getMessage());
            }
        } else {
            presenter.setMessageToView("Error, no se pudo obtener respuesta del servidor.");
        }
    }
}
