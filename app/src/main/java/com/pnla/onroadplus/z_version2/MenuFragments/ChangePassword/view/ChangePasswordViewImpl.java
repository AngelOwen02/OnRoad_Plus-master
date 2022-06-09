package com.pnla.onroadplus.z_version2.MenuFragments.ChangePassword.view;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.Containers.LoginContainer.view.LoginContainerActivity;
import com.pnla.onroadplus.z_version2.MenuFragments.ChangePassword.presenter.ChangePasswordPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.ChangePassword.presenter.ChangePasswordPresenterImpl;
import com.phoenix2.top_driver_ui.tracking_app.DialogOkMessageTracking;
import com.phoenix2.top_driver_ui.tracking_app.DialogTrackingLoader;

/**
 * A simple {@link Fragment} subclass.
 */
public class    ChangePasswordViewImpl extends Fragment implements ChangePasswordView, CardView.OnClickListener{


    private DialogTrackingLoader loader;
    private DialogOkMessageTracking dialogOkMessageTracking;
    private ChangePasswordPresenter presenter;

    private Bundle bundle;
    private String email, token;
    private TextInputEditText edtxtPass;
    private TextInputEditText edtxtRepeatPass;
    private CardView btnChangePassword;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_password_view_impl, container, false);
        initChangePasswordView(view);
        return view;
    }

    private void initChangePasswordView(View view) {
        edtxtPass = view.findViewById(R.id.edtxt_change_pass__pass);
        edtxtRepeatPass = view.findViewById(R.id.edtxt_change_pass_confirm_pass);
        btnChangePassword = view.findViewById(R.id.btn_change_pass);
        btnChangePassword.setOnClickListener(this);
        presenter = new ChangePasswordPresenterImpl(getContext());
        presenter.setView(this);
        presenter.getDataFromBundle(bundle);
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
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successChangePassword() {

        AlertDialog.Builder logoutDialog = new AlertDialog.Builder(getContext(), R.style.AlertDialogCustom);
        logoutDialog.setMessage("Se ha restablecido con éxito la contraseña. Para continuar, inicia sesión con tu nueva contraseña.");
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
    public void setUserData(String token, String email) {
        this.token = token;
        this.email = email;
    }

  /*  @Override
    public void onOkMessage() {
        dialogOkMessageTracking.dismiss();
        getActivity().getSupportFragmentManager().popBackStack();
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_change_pass:
                presenter.validateChangePasswordData(token, edtxtPass.getText().toString(), edtxtRepeatPass.getText().toString());
                break;
        }
    }


}
