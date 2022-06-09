package com.pnla.onroadplus.z_version2.MenuFragments.Commands.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Commands.presenter.CommandsPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Commands.presenter.CommandsPresenterImpl;

import com.pnla.onroadplus.z_version2.MenuFragments.Commands.adapter.CommandsV2Adapter;
import  com.pnla.onroadplus.z_version2.MenuFragments.Commands.ainterface.FragmentCommandV2Data;
import com.pnla.onroadplus.z_version2.MenuFragments.Commands.aget.CommandV2;

import com.phoenix2.top_driver_ui.tracking_app.DialogOkMessageTracking;
import com.phoenix2.top_driver_ui.tracking_app.DialogTrackingLoader;

import java.util.List;

public class CommandsViewImpl extends Fragment implements CommandsView, CommandsV2Adapter.OnClickCommandListener, DialogOkMessageTracking.OnTrackingAppSendCommandListener, DialogOkMessageTracking.OnTrackingAppOkMessageDialogListener, View.OnClickListener {

    public static final String TAG = CommandsViewImpl.class.getSimpleName();

    private Bundle bundle;
    private CommandsPresenter presenter;

    /**
     * Loader
     */
    private DialogTrackingLoader loader;

    /**
     * vehicleData
     */
    private String vehicleName;
    private int vehicleCve, deviceCveToSend, routineCveToSend, positionSelected;
    private String commandName,imei;

    private List<CommandV2> commands;
    private RecyclerView recyclerViewCommands;
    private CommandsV2Adapter adapter;

    private DialogOkMessageTracking dialogOkMessageTracking;
    private FragmentCommandV2Data fragmentCommandV2Data;
    private ImageView imgBack;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_command, container, false);
        initCommandsView(view);
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
    public void sessionExpired(String message) {
        if (loader != null) {
            loader.dismiss();
        }
        showMessage(message);
        getActivity().finish();
    }

    @Override
    public void showMessage(String message) {
        if (commands.isEmpty()){
            /*AlertDialog.Builder logoutDialog = new AlertDialog.Builder(getContext(), R.style.AlertDialogCustom);
            logoutDialog.setMessage(message);
            logoutDialog.setCancelable(false);
            logoutDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            logoutDialog.show();*/
            Toast.makeText(getContext(), "No hay comandos para seleccionar.", Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setVehicleData(String vehicleName, int vehicleCve) {
        this.vehicleName = vehicleName;
        this.vehicleCve = vehicleCve;
        presenter.getCommands(vehicleCve, getContext());
    }

    @Override
    public void fillCommandsList(List<CommandV2> commands) {
        if (commands.isEmpty()){
            Toast.makeText(getContext(), "No hay comandos para seleccionar.", Toast.LENGTH_SHORT).show();
        }else {
            this.commands = commands;
            adapter = new CommandsV2Adapter(commands, getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerViewCommands.setAdapter(adapter);
            recyclerViewCommands.setLayoutManager(layoutManager);
            adapter.setOnClickCommandListener(CommandsViewImpl.this);
        }

    }

    @Override
    public void sendCommand(final String commandName, final String imei, int position) {
        this.commandName = commandName;
        //this.deviceCveToSend = deviceCveToSend;
        // this.routineCveToSend = routineCveToSend;
        this.imei=imei;
        this.positionSelected = position;

        AlertDialog.Builder logoutDialog = new AlertDialog.Builder(getContext(), R.style.AlertDialogCustom);
        logoutDialog.setMessage("¿Estas seguro de activar el comando " + "Desbloqueo" + " en " + vehicleName + "?");
        logoutDialog.setCancelable(false);
        logoutDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        logoutDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.sendCommand(commandName, imei, getContext());
            }
        });
        logoutDialog.show();


       /* dialogOkMessageTracking = new DialogOkMessageTracking();
        dialogOkMessageTracking.setOnTrackingAppSendCommandListener(this);
        dialogOkMessageTracking.setInfoCommandDialog(ConstantsTrackingApp.TYPE_SEND_COMMAND, commandName, vehicleName);
        dialogOkMessageTracking.show(getActivity().getSupportFragmentManager(), DialogOkMessageTracking.TAG);*/
    }



    @Override
    public void commandSent(String message) {
        showMessage(message + vehicleName);
    }

    @Override//enviamos comando
    public void onOkButton() {
        dialogOkMessageTracking.dismiss();
        dialogOkMessageTracking = null;
        presenter.sendCommand(commandName,imei, getContext());
    }

    @Override
    public void successSendCommand(String vehicleName, String commandName) {
        commands.get(positionSelected).setSelected(true);
        adapter.updateItemSelected(positionSelected);
        deviceCveToSend = -1;
        routineCveToSend = -1;
        positionSelected = -1;

        AlertDialog.Builder logoutDialog = new AlertDialog.Builder(getContext(), R.style.AlertDialogCustom);
        logoutDialog.setMessage("Se ha enviado correctamente el comando a " + vehicleName);//
        logoutDialog.setCancelable(false);
        logoutDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        logoutDialog.show();
        presenter.auditTrail("El "+vehicleName+" ha enviado el comando "+commandName);

        /*dialogOkMessageTracking = new DialogOkMessageTracking();
        dialogOkMessageTracking.setOnTrackingAppOkMessageDialogListener(CommandsViewImpl.this);
        dialogOkMessageTracking.setInfoNormalDialog(ConstantsTrackingApp.TYPE_OK_COMMAND, commandName, vehicleName);
        dialogOkMessageTracking.show(getActivity().getSupportFragmentManager(), DialogOkMessageTracking.TAG);*/
    }

    @Override
    public void onCancelButton() {
        this.deviceCveToSend = -1;
        this.routineCveToSend = -1;
        this.positionSelected = -1;
        dialogOkMessageTracking.dismiss();
        dialogOkMessageTracking = null;
    }

    private void initCommandsView(View view) {
        recyclerViewCommands = view.findViewById(R.id.recyclerViewCommands);
        imgBack = view.findViewById(R.id.toolbar_commands_back);
        imgBack.setOnClickListener(this);
        presenter = new CommandsPresenterImpl();
        presenter.setView(this);
        presenter.getVehicleData(bundle);
    }

    @Override
    public void onOkMessage() {
        dialogOkMessageTracking.dismiss();
        dialogOkMessageTracking = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_commands_back:
                getActivity().onBackPressed();
                break;
        }
    }
}
