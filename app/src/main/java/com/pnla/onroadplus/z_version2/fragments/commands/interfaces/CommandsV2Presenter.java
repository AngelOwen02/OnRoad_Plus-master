package com.pnla.onroadplus.z_version2.fragments.commands.interfaces;

import android.content.Context;
import android.os.Bundle;

import com.pnla.onroadplus.z_version2.fragments.commands.models.get.CommandV2;

import java.util.List;

public interface CommandsV2Presenter {

    void setView(CommandsV2View view);

    //requestDataToInteractor
    void getVehicleData(Bundle bundle);

    void getCommands(int vehicleCve, Context context);

    void sendCommand(int cveDeviceToSend,int cveRoutineToSend, int vehicleCve,Context context);

    //setDataToView
    void setVehicleDataToView(String vehicleName, int cveVehicle);

    void setMessageToView(String message);

    void sessionExpired(String message);

    void setCommandsListToview(List<CommandV2> commands);

    void successSendCommand();
}
