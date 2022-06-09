package com.pnla.onroadplus.z_version2.Containers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Commands.view.CommandsViewImpl;

public class CommandsContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commands_container);
        initCommandsContainer();
    }

    private void initCommandsContainer(){
        Bundle bndl;
        bndl = getIntent().getExtras();
        int vehicleCve = bndl.getInt("vehicleCve");
        String vehicleName = bndl.getString("vehicleName");

        Bundle bndlCommands;
        bndlCommands = new Bundle();
        bndlCommands.putInt("vehicleCve", vehicleCve);
        bndlCommands.putString("vehicleName", vehicleName);

        showFragment(new CommandsViewImpl(), bndlCommands);
        //showCommandsViewImpl();
    }

    private void showCommandsViewImpl(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //transaction.addToBackStack(FragmentRestorePasswordV2.TAG);
        transaction.replace(R.id.commands_container, new CommandsViewImpl()).commit();
    }

    private void showFragment(Fragment fragment, Bundle bndlMap) {
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        fragment.setArguments(bndlMap);
        ft.replace(R.id.commands_container, fragment);
        ft.commit();
    }
}
