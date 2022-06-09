package com.pnla.onroadplus.z_version2.MenuFragments.Login.view;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.ChangePassword.view.ChangePasswordViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Login.presenter.LoginPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Login.presenter.LoginPresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.RecoveryPassword.view.RecoveryPasswordViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.view.menuViewImpl;
import com.pnla.onroadplus.z_version2.fragments.loginV2.view.FragmentLoginV2;
import com.pnla.onroadplus.z_version2.fragments.restorePasswordV2.view.FragmentRestorePasswordV2;
import com.phoenix2.top_driver_ui.tracking_app.DialogTrackingLoader;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginViewImpl extends Fragment implements LoginView, View.OnClickListener {

    public static final String TAG = FragmentLoginV2.class.getSimpleName();

    private DialogTrackingLoader loader;

    private CardView btnLogin;
    private TextView txvTitleLogin, txvForgetPassword;
    private EditText edtUserOrEmail, edtPassword;
    private TextInputLayout textInputLayoutUser;
    private TextInputLayout textInputLayoutPass;

    private LoginPresenter presenter;
    public static boolean showedLoginView = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_impl, container, false);
        initLoginViewImpl(view);


        return view;
    }

    private void initLoginViewImpl(View view) {
        textInputLayoutUser = view.findViewById(R.id.til_user);
        textInputLayoutPass = view.findViewById(R.id.til_password);
        edtUserOrEmail = view.findViewById(R.id.edtUserOrEmail);
        edtPassword = view.findViewById(R.id.edtPassword);
        txvTitleLogin = view.findViewById(R.id.txt_login_title);
        txvForgetPassword = view.findViewById(R.id.txvForgetPassword);
        btnLogin = view.findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(this);
        txvForgetPassword.setOnClickListener(this);

        presenter = new LoginPresenterImpl(getContext());
        presenter.setView(this);

     /*   if (!showedLoginView) {
            presenter.getUserDataPreferences();
        } else {
            Log.e("TAG", "way 2");
        }*/

        disabledTextInputAnimation();
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
        AlertDialog.Builder logoutDialog = new AlertDialog.Builder(getContext(), R.style.AlertDialogCustom);
        logoutDialog.setMessage(message);
        logoutDialog.setCancelable(false);
        logoutDialog.setPositiveButton(R.string.logout_dialog_btn_ok, null);
        logoutDialog.show();
    }

    @Override
    public void successLogin(String name) {
        presenter.auditTrail(name);
        Bundle bndl = new Bundle();
        bndl.putString("nav", "MAIN");
        Intent intent = new Intent(getContext(),menuViewImpl.class);// menuViewImpl.class);//MainMenuContainerActivity
        intent.putExtras(bndl);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);//
        startActivity(intent);
    }

    @Override
    public void showFragmentChangePasswordV2(Bundle bundle) {
        edtPassword.setText(null);
        edtUserOrEmail.setText(null);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
       // transaction.addToBackStack(ChangePasswordViewImpl.TAG);
        ChangePasswordViewImpl changePasswordView = new ChangePasswordViewImpl();
        changePasswordView.setArguments(bundle);
        transaction.replace(R.id.login_container, changePasswordView).commit();
    }

    @Override
    public void setUserData(String user, String password) {
       // edtUserOrEmail.setText(user);
       // edtPassword.setText(password);
    }

    private void showFragmentRestorePassword() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentRestorePasswordV2.TAG);
        transaction.replace(R.id.login_container, new RecoveryPasswordViewImpl()).commit();
    }

    private void disabledTextInputAnimation(){
        textInputLayoutUser.setHintEnabled(false);
        textInputLayoutPass.setHintEnabled(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:

                presenter.validateDataToStartLoginRequest(edtUserOrEmail.getText().toString(), edtPassword.getText().toString());
                break;
            case R.id.txvForgetPassword:
                showFragmentRestorePassword();
                break;
        }
    }

}
