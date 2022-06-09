package com.pnla.onroadplus.z_version2.fragments.restorePasswordV2.view;

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
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.fragments.loginV2.view.FragmentLoginV2;
import com.pnla.onroadplus.z_version2.fragments.restorePasswordV2.interfaces.FragmentRestorePasswordV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.restorePasswordV2.interfaces.FragmentRestorePasswordV2View;
import com.pnla.onroadplus.z_version2.fragments.restorePasswordV2.presenters.RestoresPasswordV2PresenterImpl;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.phoenix2.top_driver_ui.tracking_app.DialogOkMessageTracking;
import com.phoenix2.top_driver_ui.tracking_app.DialogTrackingLoader;

public class FragmentRestorePasswordV2 extends Fragment implements FragmentRestorePasswordV2View, CardView.OnClickListener, DialogOkMessageTracking.OnTrackingAppOkMessageDialogListener {
    public static final String TAG = FragmentRestorePasswordV2.class.getSimpleName();
    private DialogTrackingLoader loader;
    private FragmentRestorePasswordV2Presenter presenter;
    private TextView txvRestorePasswordTitle, txvBackToLogin;
    private EditText edtEmail;
    private CardView btnRestorePassword;
    private DialogOkMessageTracking dialogOkMessageTracking;
    private TextInputLayout textInputLayoutEmail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restore_password, container, false);
        initRestorePasswordV2(view);
        setFonts();
        disabledTextInputAnimation();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRestorePassword:
                presenter.validateRestorePasswordData(edtEmail.getText().toString());
                break;
            /*ase R.id.txvBackToLogin:
                returnToLogin();
                break;*/
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
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
    public void successRestorePassword() {
        dialogOkMessageTracking = new DialogOkMessageTracking();
        dialogOkMessageTracking.setOnTrackingAppOkMessageDialogListener(FragmentRestorePasswordV2.this);
        dialogOkMessageTracking.setInfoNormalDialog(GeneralConstantsV2.TYPE_OK_RESTORE_PASSWORD);
        dialogOkMessageTracking.show(getActivity().getSupportFragmentManager(), DialogOkMessageTracking.TAG);
    }

    @Override
    public void onOkMessage() {
        dialogOkMessageTracking.dismiss();
        getActivity().getSupportFragmentManager().popBackStack();
    }

    private void initRestorePasswordV2(View view) {
        textInputLayoutEmail = view.findViewById(R.id.til_user);
        txvRestorePasswordTitle = view.findViewById(R.id.txt_restore_password_title);
        //txvBackToLogin = view.findViewById(R.id.txvBackToLogin);
        edtEmail = view.findViewById(R.id.edtEmail);
        btnRestorePassword = view.findViewById(R.id.btnRestorePassword);
        btnRestorePassword.setOnClickListener(this);
        //txvBackToLogin.setOnClickListener(this);
        presenter = new RestoresPasswordV2PresenterImpl(getContext());
        presenter.setView(this);
        FragmentLoginV2.showedLoginView = true;
    }

    private void setFonts() {
        Typeface robotoRegular = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface robotoBold = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Bold.ttf");
        txvRestorePasswordTitle.setTypeface(robotoBold);
        edtEmail.setTypeface(robotoRegular);
        //txvBackToLogin.setTypeface(latoRegularTypeface);
    }

    private void disabledTextInputAnimation(){
        textInputLayoutEmail.setHintEnabled(false);

    }

    private void returnToLogin() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

}

