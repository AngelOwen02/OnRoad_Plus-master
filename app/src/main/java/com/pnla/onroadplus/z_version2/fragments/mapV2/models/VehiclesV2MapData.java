package com.pnla.onroadplus.z_version2.fragments.mapV2.models;

import java.util.List;

public class VehiclesV2MapData {

    private List<VehicleV2Map> vehicles = null;

    /**
     * @param vehicles
     */
    public VehiclesV2MapData(List<VehicleV2Map> vehicles) {
        super();
        this.vehicles = vehicles;
    }

    public List<VehicleV2Map> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleV2Map> vehicles) {
        this.vehicles = vehicles;
    }

}
