package com.pnla.onroadplus.z_version2.fragments.mapV2.models.markersV2;

import com.google.android.gms.maps.model.Marker;

public class VehicleMarkerV2 {
    private int cveVehicle;
    private Marker marker;

    public VehicleMarkerV2(int cveVehicle, Marker marker) {
        this.cveVehicle = cveVehicle;
        this.marker = marker;
    }

    public int getCveVehicle() {
        return cveVehicle;
    }

    public void setCveVehicle(int cveVehicle) {
        this.cveVehicle = cveVehicle;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }
}
