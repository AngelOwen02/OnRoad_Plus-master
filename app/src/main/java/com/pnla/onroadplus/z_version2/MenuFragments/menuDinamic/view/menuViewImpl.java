package com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.view.UnitsViewImpl;

public class menuViewImpl extends AppCompatActivity {

    public static float a,b,c,d,e,f;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dinamic_menu);
        a= .166f;
        b=.333f;
        c=.5f;
        d=.666f;
        e=.833f;
        f=1;
        showFragmentNavigationButtons();
        showFragmentDashboard();
    }

    private void showFragmentDashboard() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        UnitsViewImpl fragmentDashboard = new UnitsViewImpl();
        transaction.add(R.id.conteinerMainFragments, fragmentDashboard, UnitsViewImpl.TAG).commit();
    }
    private void showFragmentNavigationButtons() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        FragmentNavigationButtonsMenu fragmentNavigationButtonsMenu = new FragmentNavigationButtonsMenu();
        transaction.add(R.id.conteinerNavigationButtonsMenu, fragmentNavigationButtonsMenu, FragmentNavigationButtonsMenu.TAG).commit();
    }
}
