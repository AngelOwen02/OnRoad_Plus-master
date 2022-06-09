package com.pnla.onroadplus.z_version2.fragments.commands.interfaces;

import android.content.Context;
import android.os.Bundle;

public interface CommandsV2Interactor {
    void getVehicleData(Bundle bundle);
    void getCommands(int vehicleCve, Context context);
    void sendCommand(int cveDeviceToSend,int cveRoutineToSend, int vehicleCve,Context context);
}
