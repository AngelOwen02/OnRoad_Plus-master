package com.pnla.onroadplus.z_version2.MenuFragments.Commands.interactor;

import android.content.Context;
import android.os.Bundle;

public interface CommandsInteractor {
    void getVehicleData(Bundle bundle);
    void getCommands(int vehicleCve, Context context);
    void sendCommand(String msgRoutine, String uniqueId, Context context);
    void newsetAuditTrail(String descripcion);
}
