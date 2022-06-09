package com.pnla.onroadplus.z_version2.fragments.commands.presenters;

import android.content.Context;
import android.os.Bundle;

import com.pnla.onroadplus.z_version2.fragments.commands.interactors.CommandsV2InteractorImpl;
import com.pnla.onroadplus.z_version2.fragments.commands.interfaces.CommandsV2Interactor;
import com.pnla.onroadplus.z_version2.fragments.commands.interfaces.CommandsV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.commands.interfaces.CommandsV2View;
import com.pnla.onroadplus.z_version2.fragments.commands.models.get.CommandV2;

import java.util.List;

public class CommandsV2PresenterImpl implements CommandsV2Presenter {

    private CommandsV2View view;
    private CommandsV2Interactor interactor;

    public CommandsV2PresenterImpl() {
        interactor = new CommandsV2InteractorImpl(this);
    }

    @Override
    public void setView(CommandsV2View view) {
        this.view = view;
    }

    @Override
    public void getVehicleData(Bundle bundle) {
        if (view != null) {
            interactor.getVehicleData(bundle);
        }
    }

    @Override
    public void getCommands(int vehicleCve, Context context) {
        if (view != null) {
            view.showLoader();
            interactor.getCommands(vehicleCve, context);
        }
    }

    @Override
    public void sendCommand(int cveDeviceToSend, int cveRoutineToSend, int vehicleCve, Context context) {
        if (view != null) {
            view.showLoader();
            interactor.sendCommand(cveDeviceToSend, cveRoutineToSend, vehicleCve, context);
        }
    }

    @Override
    public void setVehicleDataToView(String vehicleName, int cveVehicle) {
        if (view != null) {
            view.setVehicleData(vehicleName, cveVehicle);
        }
    }

    @Override
    public void setMessageToView(String message) {
        if (view != null) {
            view.showMessage(message);
        }
    }

    @Override
    public void sessionExpired(String message) {
        if (view != null) {
            view.sessionExpired(message);
        }
    }

    @Override
    public void setCommandsListToview(List<CommandV2> commands) {
        if (view != null) {
            view.hideLoader();
            view.fillCommandsList(commands);
        }
    }

    @Override
    public void successSendCommand() {
        if (view != null) {
            view.hideLoader();
            view.successSendCommand();
        }
    }

}
