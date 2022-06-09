package com.pnla.onroadplus.z_version2.fragments.commands.interfaces;

import com.pnla.onroadplus.z_version2.fragments.commands.models.get.CommandV2;

import java.util.List;

public interface CommandsV2View {
    void showLoader();

    void hideLoader();

    void sessionExpired(String message);

    void showMessage(String message);

    void setVehicleData(String vehicleName,int vehicleCve);

    void fillCommandsList(List<CommandV2> commands);

    void successSendCommand();
}
