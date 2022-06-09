package com.pnla.onroadplus.z_version2.fragments.commands.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.fragments.commands.adapters.CommandsV2Adapter;
import com.pnla.onroadplus.z_version2.fragments.commands.interfaces.CommandsV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.commands.interfaces.CommandsV2View;
import com.pnla.onroadplus.z_version2.fragments.commands.interfaces.FragmentCommandV2Data;
import com.pnla.onroadplus.z_version2.fragments.commands.models.get.CommandV2;
import com.pnla.onroadplus.z_version2.fragments.commands.presenters.CommandsV2PresenterImpl;
import com.pnla.onroadplus.z_version2.fragments.mapV2.view.FragmentMapV2;
import com.phoenix2.top_driver_ui.tracking_app.ConstantsTrackingApp;
import com.phoenix2.top_driver_ui.tracking_app.DialogOkMessageTracking;
import com.phoenix2.top_driver_ui.tracking_app.DialogTrackingLoader;

import java.util.List;

public class FragmentCommandsV2 extends Fragment implements CommandsV2View, CommandsV2Adapter.OnClickCommandListener, DialogOkMessageTracking.OnTrackingAppSendCommandListener, DialogOkMessageTracking.OnTrackingAppOkMessageDialogListener {

    public static final String TAG = FragmentCommandsV2.class.getSimpleName();

    private Bundle bundle;
    private CommandsV2Presenter presenter;

    /**
     * Loader
     */
    private DialogTrackingLoader loader;

    /**
     * vehicleData
     */
    private String vehicleName;
    private int vehicleCve, deviceCveToSend, routineCveToSend, positionSelected;
    private String commandName;

    private List<CommandV2> commands;
    private RecyclerView recyclerViewCommands;
    private CommandsV2Adapter adapter;

    private DialogOkMessageTracking dialogOkMessageTracking;
    private FragmentCommandV2Data fragmentCommandV2Data;

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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentCommandV2Data = (FragmentCommandV2Data) context;
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
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setVehicleData(String vehicleName, int vehicleCve) {
        this.vehicleName = vehicleName;
        this.vehicleCve = vehicleCve;
        presenter.getCommands(vehicleCve, getContext());
    }

    @Override
    public void fillCommandsList(List<CommandV2> commands) {
        this.commands = commands;
        adapter = new CommandsV2Adapter(commands, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewCommands.setAdapter(adapter);
        recyclerViewCommands.setLayoutManager(layoutManager);
        adapter.setOnClickCommandListener(FragmentCommandsV2.this);
    }

    @Override
    public void sendCommand(String commandName, int deviceCveToSend, int routineCveToSend, int position) {
        this.commandName = commandName;
        this.deviceCveToSend = deviceCveToSend;
        this.routineCveToSend = routineCveToSend;
        this.positionSelected = position;
        dialogOkMessageTracking = new DialogOkMessageTracking();
        dialogOkMessageTracking.setOnTrackingAppSendCommandListener(this);
        dialogOkMessageTracking.setInfoCommandDialog(ConstantsTrackingApp.TYPE_SEND_COMMAND, commandName, vehicleName);
        dialogOkMessageTracking.show(getActivity().getSupportFragmentManager(), DialogOkMessageTracking.TAG);
    }

    @Override
    public void commandSent(String message) {
        showMessage(message);
    }

    @Override//enviamos comando
    public void onOkButton() {
        dialogOkMessageTracking.dismiss();
        dialogOkMessageTracking = null;
        presenter.sendCommand(deviceCveToSend, routineCveToSend, vehicleCve, getContext());
    }

    @Override
    public void successSendCommand() {
        commands.get(positionSelected).setSelected(true);
        adapter.updateItemSelected(positionSelected);
        deviceCveToSend = -1;
        routineCveToSend = -1;
        positionSelected = -1;
        dialogOkMessageTracking = new DialogOkMessageTracking();
        dialogOkMessageTracking.setOnTrackingAppOkMessageDialogListener(FragmentCommandsV2.this);
        dialogOkMessageTracking.setInfoNormalDialog(ConstantsTrackingApp.TYPE_OK_COMMAND, commandName, vehicleName);
        dialogOkMessageTracking.show(getActivity().getSupportFragmentManager(), DialogOkMessageTracking.TAG);
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
        FragmentMapV2.isShowedThisView = true;
        fragmentCommandV2Data.setCommandTitleToolbar(getString(R.string.textItemCommands));
        presenter = new CommandsV2PresenterImpl();
        presenter.setView(this);
        presenter.getVehicleData(bundle);
    }

    @Override
    public void onOkMessage() {
        dialogOkMessageTracking.dismiss();
        dialogOkMessageTracking = null;
    }

}
