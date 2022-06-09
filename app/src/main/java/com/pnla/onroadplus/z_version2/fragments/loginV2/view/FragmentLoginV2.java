package com.pnla.onroadplus.z_version2.fragments.loginV2.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import android.util.Log;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputLayout;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.Containers.MainMenuContainerActivity;
import com.pnla.onroadplus.z_version2.fragments.changePasswordV2.view.FragmentChangePasswordV2;
import com.pnla.onroadplus.z_version2.fragments.loginV2.interfaces.FragmentLoginV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.loginV2.interfaces.FragmentLoginV2View;
import com.pnla.onroadplus.z_version2.fragments.loginV2.presenters.FragmentLoginV2PresenterImpl;
import com.pnla.onroadplus.z_version2.fragments.restorePasswordV2.view.FragmentRestorePasswordV2;
import com.phoenix2.top_driver_ui.tracking_app.DialogTrackingLoader;

public class FragmentLoginV2 extends Fragment implements FragmentLoginV2View, View.OnClickListener {
    public static final String TAG = FragmentLoginV2.class.getSimpleName();

    private DialogTrackingLoader loader;

    private CardView imgvLogin;
    private TextView txvTitleLogin, txvForgetPassword;
    private EditText edtUserOrEmail, edtPassword;
    private TextView txtUserTitle;
    private TextView txtPassTitle;
    private TextInputLayout textInputLayoutUser;
    private TextInputLayout textInputLayoutPass;

    private FragmentLoginV2Presenter presenter;
    public static boolean showedLoginView = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initFragmentLoginV2(view);
        setFonts();
        disabledTextInputAnimation();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txvForgetPassword:
                showFragmentRestorePassword();
                break;
            case R.id.imgvLogin:
                presenter.validateDataToStartLoginRequest(edtUserOrEmail.getText().toString(), edtPassword.getText().toString());
                break;
        }
    }

    @Override
    public void showLoader() {
        loader = new DialogTrackingLoader();
        loader.show(getActivity().getSupportFragmentManager(), DialogTrackingLoader.TAG);
    }

    @Override
    public void hideLoader() {
        if (loader != null)
            loader.dismiss();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successLogin() {
        Intent intent = new Intent(getContext(), MainMenuContainerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void showFragmentChangePasswordV2(Bundle bundle) {
        edtPassword.setText(null);
        edtUserOrEmail.setText(null);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentChangePasswordV2.TAG);
        FragmentChangePasswordV2 fragmentChangePasswordV2 = new FragmentChangePasswordV2();
        fragmentChangePasswordV2.setArguments(bundle);
        transaction.replace(R.id.containerFragmentsLoginV2, fragmentChangePasswordV2, FragmentChangePasswordV2.TAG).commit();
    }

    @Override
    public void setUserData(String user, String password) {
        edtUserOrEmail.setText(user);
        edtPassword.setText(password);
    }

    private void initFragmentLoginV2(View view) {
        textInputLayoutUser = view.findViewById(R.id.til_user);
        textInputLayoutPass = view.findViewById(R.id.til_password);
        txtUserTitle = view.findViewById(R.id.txt_login_user);
        txtPassTitle = view.findViewById(R.id.txt_login_pass);
        edtUserOrEmail = view.findViewById(R.id.edtUserOrEmail);
        edtPassword = view.findViewById(R.id.edtPassword);
        txvTitleLogin = view.findViewById(R.id.txt_login_title);
        txvForgetPassword = view.findViewById(R.id.txvForgetPassword);
        imgvLogin = view.findViewById(R.id.imgvLogin);
        imgvLogin.setOnClickListener(this);
        txvForgetPassword.setOnClickListener(this);
        presenter = new FragmentLoginV2PresenterImpl(getContext());
        presenter.setView(this);
        setFonts();

        if (!showedLoginView) {
            presenter.getUserDataPreferences();
        } else {
            Log.e("TAG", "way 2");
        }
    }

    private void setFonts() {

        Typeface robotoRegular = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface robotoBold = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Bold.ttf");

        txvTitleLogin.setTypeface(robotoBold);
        txtUserTitle.setTypeface(robotoRegular);
        txtPassTitle.setTypeface(robotoRegular);
        txvForgetPassword.setTypeface(robotoRegular);
    }

    private void showFragmentRestorePassword() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentRestorePasswordV2.TAG);
        transaction.replace(R.id.containerFragmentsLoginV2, new FragmentRestorePasswordV2(), FragmentRestorePasswordV2.TAG).commit();
    }

    private void disabledTextInputAnimation(){
        textInputLayoutUser.setHintEnabled(false);
        textInputLayoutPass.setHintEnabled(false);
    }

}