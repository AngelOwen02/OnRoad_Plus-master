package com.pnla.onroadplus.z_version2.MenuFragments.Commands.view;

import com.pnla.onroadplus.z_version2.MenuFragments.Commands.aget.CommandV2;

import java.util.List;

public interface CommandsView {
    void showLoader();

    void hideLoader();

    void sessionExpired(String message);

    void showMessage(String message);

    void setVehicleData(String vehicleName, int vehicleCve);

    void fillCommandsList(List<CommandV2> commands);

    void successSendCommand(String vehicleName, String commandName);

}
