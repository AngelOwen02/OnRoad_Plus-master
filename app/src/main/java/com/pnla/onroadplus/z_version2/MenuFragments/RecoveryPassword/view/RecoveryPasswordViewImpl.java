package com.pnla.onroadplus.z_version2.MenuFragments.RecoveryPassword.view;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.Containers.LoginContainer.view.LoginContainerActivity;
import com.pnla.onroadplus.z_version2.MenuFragments.RecoveryPassword.presenter.RecoveryPasswordPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.RecoveryPassword.presenter.RecoveryPasswordPresenterImpl;
import com.pnla.onroadplus.z_version2.fragments.loginV2.view.FragmentLoginV2;
import com.pnla.onroadplus.z_version2.fragments.restorePasswordV2.view.FragmentRestorePasswordV2;
import com.phoenix2.top_driver_ui.tracking_app.DialogOkMessageTracking;
import com.phoenix2.top_driver_ui.tracking_app.DialogTrackingLoader;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecoveryPasswordViewImpl extends Fragment implements RecoveryPasswordView, CardView.OnClickListener {

    public static final String TAG = FragmentRestorePasswordV2.class.getSimpleName();
    private DialogTrackingLoader loader;
    private RecoveryPasswordPresenter presenter;
    private TextView txvRestorePasswordTitle, txvBackToLogin;
    private EditText edtEmail;
    private CardView btnRestorePassword;
    private DialogOkMessageTracking dialogOkMessageTracking;
    private TextInputLayout textInputLayoutEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recovery_password_view_impl, container, false);

        initRecoveryPasswordView(view);

        return view;
    }

    public void initRecoveryPasswordView(View view){
        textInputLayoutEmail = view.findViewById(R.id.til_user);
        txvRestorePasswordTitle = view.findViewById(R.id.txt_restore_password_title);
        edtEmail = view.findViewById(R.id.edtEmail);
        btnRestorePassword = view.findViewById(R.id.btnRestorePassword);
        btnRestorePassword.setOnClickListener(this);
        presenter = new RecoveryPasswordPresenterImpl(getContext());
        presenter.setView(this);
        FragmentLoginV2.showedLoginView = true;
    }

    @Override
    public void successRestorePassword() {
      /*  dialogOkMessageTracking = new DialogOkMessageTracking();
       // dialogOkMessageTracking.setOnTrackingAppOkMessageDialogListener(RecoveryPasswordViewImpl.this);
        dialogOkMessageTracking.setInfoNormalDialog(GeneralConstantsV2.TYPE_OK_RESTORE_PASSWORD);
        dialogOkMessageTracking.show(getActivity().getSupportFragmentManager(), DialogOkMessageTracking.TAG); */

        AlertDialog.Builder logoutDialog = new AlertDialog.Builder(getContext(), R.style.AlertDialogCustom);
        logoutDialog.setMessage("Te hemos enviado un enlace a tu correo electrónico para que puedas restablecer tu contraseña.");
        logoutDialog.setCancelable(false);
        logoutDialog.setPositiveButton(R.string.logout_dialog_btn_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showLogin();
            }
        });
        logoutDialog.show();
    }

    private void showLogin (){
        Bundle bndl = new Bundle();
        bndl.putBoolean("HelpStatus", true);
        Intent intent = new Intent(getContext(), LoginContainerActivity.class);
        intent.putExtras(bndl);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void showErrorMessage(String message) {
        AlertDialog.Builder logoutDialog = new AlertDialog.Builder(getContext(), R.style.AlertDialogCustom);
        logoutDialog.setMessage(message);
        //logoutDialog.setMessage("Te hemos enviado un enlace a tu correo electrónico para que puedas restablecer tu contraseña.");
        logoutDialog.setCancelable(false);
        logoutDialog.setPositiveButton(R.string.logout_dialog_btn_ok, null);
        logoutDialog.show();
    }

    @Override
    public void showLoader() {
        loader = new DialogTrackingLoader();
        loader.show(getActivity().getSupportFragmentManager(), DialogTrackingLoader.TAG);
    }

    @Override
    public void hideLoader() {
        if (loader != null) {
            loader.dismiss();
            loader = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRestorePassword:
                presenter.validateRestorePasswordData(edtEmail.getText().toString());
                break;
        }

    }
}
