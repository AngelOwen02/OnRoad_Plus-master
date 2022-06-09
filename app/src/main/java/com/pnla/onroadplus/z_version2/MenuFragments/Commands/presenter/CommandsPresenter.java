package com.pnla.onroadplus.z_version2.MenuFragments.Commands.presenter;

import android.content.Context;
import android.os.Bundle;

import com.pnla.onroadplus.z_version2.MenuFragments.Commands.view.CommandsView;
import com.pnla.onroadplus.z_version2.MenuFragments.Commands.aget.CommandV2;

import java.util.List;

public interface CommandsPresenter {

    void setView(CommandsView view);

    //requestDataToInteractor
    void getVehicleData(Bundle bundle);

    void getCommands(int vehicleCve, Context context);

    void sendCommand(String cveDeviceToSend, String cveRoutineToSend, Context context);

    //setDataToView
    void setVehicleDataToView(String vehicleName, int cveVehicle);

    void setMessageToView(String message);

    void sessionExpired(String message);

    void setCommandsListToview(List<CommandV2> commands);

    void auditTrail(String descripcion);

    void successSendCommand(String vehicleName, String commandName);
}
