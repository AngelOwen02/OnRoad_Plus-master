package com.pnla.onroadplus.z_version2.MenuFragments.Notifications.utils.clases;

import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.Vehicles;

import java.util.List;

public class VehiclesData {
    private List<Vehicles> vehicles = null;

    /**
     * @param vehicles
     */
    public VehiclesData(List<Vehicles> vehicles) {
        super();
        this.vehicles = vehicles;
    }

    public List<Vehicles> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicles> vehicles) {
        this.vehicles = vehicles;
    }

}
