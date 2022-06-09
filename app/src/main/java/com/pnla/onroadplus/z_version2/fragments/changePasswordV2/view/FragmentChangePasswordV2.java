package com.pnla.onroadplus.z_version2.fragments.changePasswordV2.view;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.fragments.changePasswordV2.interfaces.ChangePasswordV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.changePasswordV2.interfaces.ChangePasswordV2View;
import com.pnla.onroadplus.z_version2.fragments.changePasswordV2.presenters.ChangePasswordV2PresenterImpl;
import com.pnla.onroadplus.z_version2.fragments.loginV2.view.FragmentLoginV2;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.phoenix2.top_driver_ui.tracking_app.DialogOkMessageTracking;
import com.phoenix2.top_driver_ui.tracking_app.DialogTrackingLoader;

public class FragmentChangePasswordV2 extends Fragment implements ChangePasswordV2View,
        DialogOkMessageTracking.OnTrackingAppOkMessageDialogListener, View.OnClickListener {
    public static final String TAG = FragmentChangePasswordV2.class.getSimpleName();

    private DialogTrackingLoader loader;
    private DialogOkMessageTracking dialogOkMessageTracking;
    private ChangePasswordV2Presenter presenter;

    private Bundle bundle;
    private String email, token;
    private TextView txvTitleChangePasword;
    private EditText edtNewPasswordChangePasswordLogin, edtRepeatNewPasswordChangePasswordLogin;
    private FloatingActionButton btnChangePassword;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_password_login, container, false);
        initChangePasswordView(view);
        setFonts();
        return view;
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
        dialogOkMessageTracking = new DialogOkMessageTracking();
        dialogOkMessageTracking.setOnTrackingAppOkMessageDialogListener(FragmentChangePasswordV2.this);
        dialogOkMessageTracking.setInfoNormalDialog(GeneralConstantsV2.TYPE_OK_UPDATE_PASSWORD);
        dialogOkMessageTracking.show(getActivity().getSupportFragmentManager(), DialogOkMessageTracking.TAG);
    }

    @Override
    public void setUserData(String token, String email) {
        this.token = token;
        this.email = email;
    }

    @Override
    public void onOkMessage() {
        dialogOkMessageTracking.dismiss();
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnChangePassword:
                presenter.validateChangePasswordData(token, edtNewPasswordChangePasswordLogin.getText().toString(), edtRepeatNewPasswordChangePasswordLogin.getText().toString());
                break;
        }
    }

    private void initChangePasswordView(View view) {
        txvTitleChangePasword = view.findViewById(R.id.txvTitleChangePasword);
        edtNewPasswordChangePasswordLogin = view.findViewById(R.id.edtNewPasswordChangePasswordLogin);
        edtRepeatNewPasswordChangePasswordLogin = view.findViewById(R.id.edtRepeatNewPasswordChangePasswordLogin);
        btnChangePassword = view.findViewById(R.id.btnChangePassword);
        btnChangePassword.setOnClickListener(this);
        FragmentLoginV2.showedLoginView = true;
        presenter = new ChangePasswordV2PresenterImpl(getContext());
        presenter.setView(this);
        presenter.getDataFromBundle(bundle);
    }

    private void setFonts() {
        Typeface robotoRegular = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface robotoBold = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Bold.ttf");
        txvTitleChangePasword.setTypeface(robotoBold);
        edtNewPasswordChangePasswordLogin.setTypeface(robotoRegular);
        edtRepeatNewPasswordChangePasswordLogin.setTypeface(robotoRegular);
    }

}
