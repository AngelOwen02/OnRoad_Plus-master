package com.pnla.onroadplus.z_version2.MenuFragments.Tracking.data;

import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.model.VehicleTracking;

import java.util.List;

public class TrackingData {
    private List<VehicleTracking> vehicles = null;

    public TrackingData(List<VehicleTracking> vehicles) {
        super();
        this.vehicles = vehicles;
    }

    public List<VehicleTracking> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleTracking> vehicles) {
        this.vehicles = vehicles;
    }
}
