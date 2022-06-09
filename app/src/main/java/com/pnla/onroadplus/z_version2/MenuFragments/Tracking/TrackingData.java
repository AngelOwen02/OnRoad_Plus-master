package com.pnla.onroadplus.z_version2.MenuFragments.Tracking;

import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;

import java.util.List;

public class TrackingData {
    private List<Unit> vehicles = null;

    public TrackingData(List<Unit> vehicles) {
        super();
        this.vehicles = vehicles;
    }

    public List<Unit> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Unit> vehicles) {
        this.vehicles = vehicles;
    }
}
