package com.pnla.onroadplus.z_version2.fragments.commands.models.get;

import java.util.List;

public class CommandV2Data {
    private List<CommandV2> routines = null;

    /**
     * @param routines
     */
    public CommandV2Data(List<CommandV2> routines) {
        super();
        this.routines = routines;
    }

    public List<CommandV2> getRoutines() {
        return routines;
    }

    public void setRoutines(List<CommandV2> routines) {
        this.routines = routines;
    }
}
