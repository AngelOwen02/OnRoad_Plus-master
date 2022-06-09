package com.pnla.onroadplus.z_version2.fragments.settingsV2.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.activities.HelpContainerActivity;
import com.pnla.onroadplus.z_version2.activities.online.interfaces.ActivityOnLineServices;
import com.pnla.onroadplus.z_version2.activities.online.models.CloseSessionV2Request;
import com.pnla.onroadplus.z_version2.activities.online.models.CloseSessionV2Response;
import com.pnla.onroadplus.z_version2.activities.online.view.ActivityOnlineV2;
import com.pnla.onroadplus.z_version2.fragments.helpV2.FragmentHelpV2;
import com.pnla.onroadplus.z_version2.fragments.settingsV2.interfaces.SettingsV2Data;
import com.pnla.onroadplus.z_version2.fragments.settingsV2.interfaces.SettingsV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.settingsV2.interfaces.SettingsV2View;
import com.pnla.onroadplus.z_version2.fragments.settingsV2.presenters.SettingsV2PresenterImpl;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FragmentSettingsV2 extends Fragment implements SettingsV2View {
    public static final String TAG = FragmentSettingsV2.class.getSimpleName();

    private SharedPreferences.Editor editor;
    private TextView txvNotificationTitle, txvEnableNotifications;
    private Switch switchNotifications;
    private SettingsV2Data settingsV2Data;
    private TextView txtUsername;
    private TextView txtEmail;
    private TextView txtNotificationsButton;
    private TextView txtHelpButton;
    private TextView txtLogoutButton;
    private ImageView imgUser;
    private LinearLayout llLogout;
    private LinearLayout llHelp;

    private Toolbar btnToolbar;

    private ActivityOnLineServices services;
    private Retrofit retrofitClient;



    private SettingsV2Presenter presenter;
    Fragment fragment = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityOnlineV2.isVisibleVehiclesList = true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        initSettingsView(view);
        setFonts();
        listenerSwitch();

        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        services = retrofitClient.create(ActivityOnLineServices.class);

        SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String employeeName = preferences.getString(GeneralConstantsV2.EMPLOYEE_NAME_PREFERENCES, null);
        String employeeImage = preferences.getString(GeneralConstantsV2.URL_USER_IMAGE_PREFERENCES, null);
        String employeeEmail = preferences.getString(GeneralConstantsV2.EMAIL_PREFERENCES, null);
        final String employeeToken = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        /*Glide.with(getContext())
                .load(employeeImage)
                .apply(RequestOptions.circleCropTransform())
                .into(imgUser);*/
        txtUsername.setText(employeeName);
        txtEmail.setText(employeeEmail);
        //presenter.setUserEmployeeName(employeeName);
        Log.e("ImageUser", employeeImage);
        if (employeeImage != null && !employeeImage.equals(GeneralConstantsV2.NO_IMAGE)) {
            //presenter.setEmployeeImage(employeeImage);
        } else {
            //presenter.setDefaultEmployeeImage();
        }

        llHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "Iniciar Fragmnt Help", Toast.LENGTH_SHORT).show();

                goToContainerHelp();
            }
        });


        llLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog(employeeToken);
            }
        });

        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //settingsV2Data = (SettingsV2Data) context;
    }

    @Override
    public void activeNotifications() {
        txvEnableNotifications.setText(getString(R.string.textDisableNotifications));
        switchNotifications.setChecked(true);
    }

    @Override
    public void disabledNotifications() {
        txvEnableNotifications.setText(getString(R.string.textEnableNotifications));
        switchNotifications.setChecked(false);
    }

    private void initSettingsView(View view) {
        btnToolbar = view.findViewById(R.id.bnc_toolbar);

        llLogout = view.findViewById(R.id.ll_logout_button_container);
        llHelp = view.findViewById(R.id.ll_help_button_container);

        imgUser = view.findViewById(R.id.img_pi);
        txtUsername = view.findViewById(R.id.txt_pi_name);
        txtEmail = view.findViewById(R.id.txt_pi_email);
        txtNotificationsButton = view.findViewById(R.id.txvEnableNotifications);
        txtHelpButton = view.findViewById(R.id.txt_hb);
        txtLogoutButton = view.findViewById(R.id.txt_lb);

        txvEnableNotifications = view.findViewById(R.id.txvEnableNotifications);
        switchNotifications = view.findViewById(R.id.switchNotifications);
//        settingsV2Data.setSettingsTitleToolbar(getString(R.string.textItemSettings));
        presenter = new SettingsV2PresenterImpl();
        presenter.setView(this);
        presenter.getNotificationsConfiguration(getContext());
    }

    private void setFonts() {
        Typeface robotoRegular = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface robotoBold = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Bold.ttf");

        txtUsername.setTypeface(robotoBold);
        txtEmail.setTypeface(robotoRegular);
        txtNotificationsButton.setTypeface(robotoRegular);
        txtHelpButton.setTypeface(robotoRegular);
        txtLogoutButton.setTypeface(robotoRegular);

    }

    private void listenerSwitch() {
        SharedPreferences preferences = getActivity().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        editor = preferences.edit();

        switchNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txvEnableNotifications.setText(R.string.textDisableNotifications);
                    //editor.putString(GeneralConstantsV2.NOTIFICATIONS_PREFERENCES, GeneralConstantsV2.ACTIVE_NOTIFICATIONS);
                    //editor.commit();
                    presenter.saveUserNotificationState(true, getContext());
                } else {
                    txvEnableNotifications.setText(getString(R.string.textEnableNotifications));
                    //editor.putString(GeneralConstantsV2.NOTIFICATIONS_PREFERENCES, GeneralConstantsV2.DISABLED_NOTIFICATIONS);
                    //editor.commit();
                    presenter.saveUserNotificationState(false, getContext());
                }
            }
        });
    }

    public void showLogoutDialog(final String token){
        AlertDialog.Builder logoutDialog = new AlertDialog.Builder(getContext(), R.style.AlertDialogCustom);
        logoutDialog.setMessage(R.string.logout_dialog_message);
        logoutDialog.setCancelable(false);
        logoutDialog.setPositiveButton(R.string.logout_dialog_btn_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                aceptar(token);
            }
        });
        logoutDialog.setNegativeButton(R.string.logout_dialog_btn_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                cancelar();
            }
        });
        logoutDialog.show();
    }

    public void aceptar(String token) {
        startCloseSessionRequest(token);
    }

    public void cancelar() {

    }

    private void goToContainerHelp(){
        Intent intent = new Intent(getContext(), HelpContainerActivity.class);
        startActivity(intent);
    }

    private void showFragmentHelp(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment, FragmentHelpV2.TAG).commit();

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
                //presenter.setMessageToView(RetrofitValidationsV2.getOnFailureResponse(t, context));
            }
        });
    }

    private void getCloseSessionResponse(Response<CloseSessionV2Response> response) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            closeSessionSuccess(response);
        } else {
            //presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }

    private void closeSessionSuccess(Response<CloseSessionV2Response> response) {
        CloseSessionV2Response closeSessionV2Response = response.body();
        if (closeSessionV2Response != null) {
            int responseCode = closeSessionV2Response.getResponseCode();
            if (responseCode == 102 || responseCode == 104) {
                SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(GeneralConstantsV2.CLOSE_SESSION_PREFERENCES, "YES");
                editor.commit();


               /* Intent intent = new Intent(getContext(), ActivityLoginV2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                +7
                */


              //  presenter.successCloseSession();
            } else {
              //  presenter.setMessageToView("Ocurrió un error, no se pudo cerrar sesión");
            }
        } else {
           // presenter.setMessageToView("Ocurrió un error, no se pudo cerrar sesión");
        }
    }




}
