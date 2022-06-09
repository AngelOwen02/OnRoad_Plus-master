package com.pnla.onroadplus.z_version2.Containers.LoginContainer.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Help.Screens.FragmentHelp;
import com.pnla.onroadplus.z_version2.Containers.LoginContainer.presenter.LoginContainerPresenter;
import com.pnla.onroadplus.z_version2.Containers.LoginContainer.presenter.LoginContainerPresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Login.view.LoginViewImpl;
import com.pnla.onroadplus.z_version2.fragments.loginV2.view.FragmentLoginV2;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmConfig;

public class LoginContainerActivity extends AppCompatActivity implements LoginContainerView {

    LoginContainerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_container);

        initLoginContainerView();
    }

    public void initLoginContainerView(){
        RealmConfig.initRealm(getApplicationContext());
        Bundle bndl = getIntent().getExtras();
        presenter = new LoginContainerPresenterImpl();
        presenter.setView(this);
        presenter.existUsers(bndl);
    }

    @Override
    public void showLoginViewImpl() {/**    LOGIN VIEW*/
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentLoginV2.TAG);
        transaction.add(R.id.login_container, new LoginViewImpl()).commit();
    }

    @Override
    public void showHelpViewImpl() {/**    TUTORIAL VIEW*/

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentLoginV2.TAG);
        transaction.add(R.id.login_container, new FragmentHelp()).commit();

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
}
