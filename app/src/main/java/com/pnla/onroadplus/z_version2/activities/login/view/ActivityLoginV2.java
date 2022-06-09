package com.pnla.onroadplus.z_version2.activities.login.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.activities.login.interfaces.LoginV2Presenter;
import com.pnla.onroadplus.z_version2.activities.login.interfaces.LoginV2View;
import com.pnla.onroadplus.z_version2.activities.login.presenters.LoginV2PresenterImpl;
import com.pnla.onroadplus.z_version2.fragments.helpV2.FragmentHelpV2;
import com.pnla.onroadplus.z_version2.fragments.loginV2.view.FragmentLoginV2;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmConfig;

public class ActivityLoginV2 extends AppCompatActivity implements LoginV2View,FragmentHelpV2.OnHelpV2Listener {

    private LoginV2Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_v2);
        RealmConfig.initRealm(getApplicationContext());
        presenter = new LoginV2PresenterImpl();
        presenter.setView(this);
        presenter.existUsers();
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count > 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.setView(null);
    }

    @Override
    public void showLoginFragmentV2() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentLoginV2.TAG);
        transaction.add(R.id.containerFragmentsLoginV2, new FragmentLoginV2(), FragmentLoginV2.TAG).commit();
    }

    @Override
    public void showFragmentHelpV2() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(GeneralConstantsV2.INVOKED_LOGIN_SCREEN, GeneralConstantsV2.IS_FIRST_TIME);
        FragmentHelpV2 fragmentHelpV2 = new FragmentHelpV2();
        fragmentHelpV2.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.containerFragmentsLoginV2, fragmentHelpV2, FragmentHelpV2.TAG).commit();
    }

    @Override  //este metódo no se ocupa en este fragment pero se debe de implementar por la interfaz que contieneFragmentHelpV2
    public void showToolbar() {
    }
}
