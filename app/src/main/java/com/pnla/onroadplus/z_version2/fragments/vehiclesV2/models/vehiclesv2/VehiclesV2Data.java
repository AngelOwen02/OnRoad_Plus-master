package com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2;

import java.util.List;

public class VehiclesV2Data {

    private List<VehicleV2> vehicles = null;

    /**
     * @param vehicles
     */
    public VehiclesV2Data(List<VehicleV2> vehicles) {
        super();
        this.vehicles = vehicles;
    }

    public List<VehicleV2> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleV2> vehicles) {
        this.vehicles = vehicles;
    }

}
