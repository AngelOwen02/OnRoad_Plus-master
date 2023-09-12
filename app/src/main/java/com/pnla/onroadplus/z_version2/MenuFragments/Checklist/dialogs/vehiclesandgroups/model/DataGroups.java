package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataGroups {

    @SerializedName("vehicleGroups")
    private List<VehicleGroupv2> vehicleGroups;

    public DataGroups(List<VehicleGroupv2> vehicleGroups) {
        super();
        this.vehicleGroups = vehicleGroups;
    }

    public List<VehicleGroupv2> getVehicleGroups() {
        return vehicleGroups;
    }

    public void setVehicleGroups(List<VehicleGroupv2> vehicleGroups) {
        this.vehicleGroups = vehicleGroups;
    }
}