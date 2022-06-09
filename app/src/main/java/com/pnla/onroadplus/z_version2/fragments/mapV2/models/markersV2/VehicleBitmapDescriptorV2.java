package com.pnla.onroadplus.z_version2.fragments.mapV2.models.markersV2;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.VehicleV2Map;

public class VehicleBitmapDescriptorV2 {

    private int cveVehicle;
    private BitmapDescriptor vehicleImage;
    private VehicleV2Map vehicle;

    public VehicleBitmapDescriptorV2(int cveVehicle, BitmapDescriptor vehicleImage, VehicleV2Map vehicle) {
        this.cveVehicle = cveVehicle;
        this.vehicleImage = vehicleImage;
        this.vehicle = vehicle;
    }

    public int getCveVehicle() {
        return cveVehicle;
    }

    public void setCveVehicle(int cveVehicle) {
        this.cveVehicle = cveVehicle;
    }

    public BitmapDescriptor getVehicleImage() {
        return vehicleImage;
    }

    public void setVehicleImage(BitmapDescriptor vehicleImage) {
        this.vehicleImage = vehicleImage;
    }

    public VehicleV2Map getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleV2Map vehicle) {
        this.vehicle = vehicle;
    }
}
