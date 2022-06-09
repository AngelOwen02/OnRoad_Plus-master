package com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class vehicleGroupData {

    @SerializedName("vehicleGroups")
    @Expose
    private List<VehicleGroup> vehicleGroups = null;

    public vehicleGroupData ( List<VehicleGroup> vehicleGroups)
    {
        this.vehicleGroups=vehicleGroups;

    }
    public List<VehicleGroup> getVehicleGroups() {
        return vehicleGroups;
    }

    public void setVehicleGroups(List<VehicleGroup> vehicleGroups) {
        this.vehicleGroups = vehicleGroups;
    }
}
