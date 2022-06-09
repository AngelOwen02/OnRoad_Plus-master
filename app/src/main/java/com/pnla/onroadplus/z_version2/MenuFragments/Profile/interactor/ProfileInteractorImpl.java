package com.pnla.onroadplus.z_version2.MenuFragments.Profile.interactor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Profile.presenter.ProfilePresenter;
import com.pnla.onroadplus.z_version2.activities.HelpContainerActivity;
import com.pnla.onroadplus.z_version2.activities.online.interfaces.ActivityOnLineServices;
import com.pnla.onroadplus.z_version2.activities.online.models.CloseSessionV2Request;
import com.pnla.onroadplus.z_version2.activities.online.models.CloseSessionV2Response;
import com.pnla.onroadplus.z_version2.activities.online.utils.ActivityOnLineUtils;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmUserNotifications;
import com.pnla.onroadplus.z_version2.realmOnRoad.UserNotificationRealmSave;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileInteractorImpl implements ProfileInteractor {

    private Context context;
    private ProfilePresenter presenter;
    private ActivityOnLineServices services;

    public ProfileInteractorImpl(Context context, ProfilePresenter presenter) {
        this.context = context;
        this.presenter = presenter;

        Retrofit retrofitClient = RetrofitClientV2.getRetrofitInstance();
        services = retrofitClient.create(ActivityOnLineServices.class);
    }
// region:notificationconfig
    @Override
    public void getNotificationsConfiguration(Context context) {

        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String userPreferences = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);

        UserNotificationRealmSave userNotificationRealmSave = RealmUserNotifications.existUserNotification(userPreferences);

        if (userNotificationRealmSave != null) {
            boolean isActiveNotification = userNotificationRealmSave.isActiveNotifications();
            if (isActiveNotification) {
                presenter.activeNotifications();
            } else {
                presenter.disabledNotifications();
            }
        } else {
            presenter.disabledNotifications();
        }
    }

    @Override
    public void saveUserNotificationState(boolean isActive, Context context) {

        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String user = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);

        if (RealmUserNotifications.existUserNotification(user) == null) {
            RealmUserNotifications.saveUserNotifications(user, isActive);
        } else {
            RealmUserNotifications.updateUserNotification(user, isActive);
        }
    }


    @Override
    public void getUserDataPreferences(Context context) {

        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String username = preferences.getString(GeneralConstantsV2.EMPLOYEE_NAME_PREFERENCES, null);
        String email = preferences.getString(GeneralConstantsV2.EMAIL_PREFERENCES, null);

        presenter.setUserProfileData(username, email);


    }
    // endregion:notificationconfig
    @Override
    public void getUserImageProfile(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String image = preferences.getString(GeneralConstantsV2.URL_USER_IMAGE_PREFERENCES, null);

        if (image != null && !image.equals(GeneralConstantsV2.NO_IMAGE)) {
            presenter.setUserImageProfile(image);
        } else {
            presenter.setUserImageProfile("");
        }
    }

    @Override
    public void getFonts() {
        Typeface robotoRegular = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface robotoBold = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");

        presenter.setFonts(robotoRegular, robotoBold);
    }


    @Override
    public void validateDataToCloseSession(Context context) {

        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        String resultValidation = ActivityOnLineUtils.validateTokenCloseSession(token);

        if (resultValidation.equals(GeneralConstantsV2.SUCCESS)) {
            startCloseSessionRequest(token);
        } else {
            presenter.setMessageToView("Ocurrió un error, no se pudo cerrar sesión");
        }
    }


    @Override
    public void getLogoutConfirmationDialog() {

        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        final String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);

        AlertDialog.Builder logoutDialog = new AlertDialog.Builder(context, R.style.AlertDialogCustom);
        logoutDialog.setMessage(R.string.logout_dialog_message);
        logoutDialog.setCancelable(false);
        logoutDialog.setPositiveButton(R.string.logout_dialog_btn_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                startCloseSessionRequest(token);
            }
        });
        logoutDialog.setNegativeButton(R.string.logout_dialog_btn_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
            }
        });
        logoutDialog.show();
    }

    @Override
    public void intentToHelpScreen(Context context) {
        Intent intent = new Intent(context, HelpContainerActivity.class);
        context.startActivity(intent);
    }


    private void startCloseSessionRequest(String token) {
        CloseSessionV2Request request = new CloseSessionV2Request(token);
        services.closeSessionService(request).enqueue(new Callback<CloseSessionV2Response>() {
            @Override
            public void onResponse(Call<CloseSessionV2Response> call, Response<CloseSessionV2Response> response) {
                getCloseSessionResponse(response);
            }

            @Override
            public void onFailure(Call<CloseSessionV2Response> call, Throwable t) {
                presenter.setMessageToView(RetrofitValidationsV2.getOnFailureResponse(t, context));
            }
        });
    }

    private void getCloseSessionResponse(Response<CloseSessionV2Response> response) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            closeSessionSuccess(response);
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }

    private void closeSessionSuccess(Response<CloseSessionV2Response> response) {
        CloseSessionV2Response closeSessionV2Response = response.body();
        if (closeSessionV2Response != null) {
            int responseCode = closeSessionV2Response.getResponseCode();
            if (responseCode == 102 || responseCode == 104) {
                SharedPreferences prefs = context.getSharedPreferences("Login:FirstTime", Context.MODE_PRIVATE);
                prefs.edit().remove("isFirst").apply();
                SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(GeneralConstantsV2.CLOSE_SESSION_PREFERENCES, "YES");
                editor.commit();
                //UnitDB.deleteDB();
                //UserDataDB.deleteDB();
                //GroupDB.deleteDB();
                //RealmUserData.deleteDB();
                presenter.showLoginScreen();
            } else {
                presenter.setMessageToView("Ocurrió un error, no se pudo cerrar sesión");

            }
        } else {
            presenter.setMessageToView("Ocurrió un error, no se pudo cerrar sesión");
        }
    }


}
