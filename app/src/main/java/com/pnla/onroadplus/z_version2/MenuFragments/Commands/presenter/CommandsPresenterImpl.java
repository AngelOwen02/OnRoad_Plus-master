package com.pnla.onroadplus.z_version2.MenuFragments.Commands.presenter;

import android.content.Context;
import android.os.Bundle;

import com.pnla.onroadplus.z_version2.MenuFragments.Commands.view.CommandsView;
import com.pnla.onroadplus.z_version2.MenuFragments.Commands.interactor.CommandsInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.Commands.interactor.CommandsInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Commands.aget.CommandV2;

import java.util.List;

public class CommandsPresenterImpl implements CommandsPresenter {

    private CommandsView view;
    private CommandsInteractor interactor;

    public CommandsPresenterImpl() {
        interactor = new CommandsInteractorImpl(this);
    }

    @Override
    public void setView(CommandsView view) {
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
    public void sendCommand(String cveDeviceToSend, String cveRoutineToSend, Context context) {
        if (view != null) {
            view.showLoader();
            interactor.sendCommand(cveDeviceToSend, cveRoutineToSend, context);
        }
    }

    @Override
    public void auditTrail(String descripcion) {
        if (view != null) {
            interactor.newsetAuditTrail(descripcion);
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
    public void successSendCommand(String vehicleName, String commandName) {
        if (view != null) {
            view.hideLoader();
            view.successSendCommand(vehicleName,commandName);
        }
    }

}
