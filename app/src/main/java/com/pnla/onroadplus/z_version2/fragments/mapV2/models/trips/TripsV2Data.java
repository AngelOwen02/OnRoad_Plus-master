package com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips;

import java.util.List;

public class TripsV2Data {

    private List<TripV2> trips = null;

    /**
     * @param trips
     */
    public TripsV2Data(List<TripV2> trips) {
        super();
        this.trips = trips;
    }

    public List<TripV2> getTrips() {
        return trips;
    }

    public void setTrips(List<TripV2> trips) {
        this.trips = trips;
    }

}
