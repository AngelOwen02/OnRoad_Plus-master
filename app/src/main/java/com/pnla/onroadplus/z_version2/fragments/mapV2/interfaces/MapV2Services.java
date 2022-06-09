package com.pnla.onroadplus.z_version2.fragments.mapV2.interfaces;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.TripByDayResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.tripsbyTimeRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.tripsbyTimeResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.tripsbydayRequest;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.VehicleV2MapRequest;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.VehicleV2MapResponse;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripsV2Request;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripsV2Response;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.vehicleDescription.VehicleDescriptionRequest;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.vehicleDescription.VehicleDescriptionResponse;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MapV2Services {
    /**
     * get vehicles
     *
     * @param request
     * @return
     */
    @POST(RetrofitEndPointsV2.GET_FULL_VEHICLES)
    Call<VehicleV2MapResponse> getFullVehicles(@Body VehicleV2MapRequest request);

    /**
     * Get vehicle description
     *
     * @param request
     * @return
     */
    @POST(RetrofitEndPointsV2.GET_VEHICLE_DESCRIPTION)
    Call<VehicleDescriptionResponse> getVehicleDescription(@Body VehicleDescriptionRequest request);

    /**
     * get Trips
     *
     * @param request
     * @return
     */
    @POST(RetrofitEndPointsV2.GET_VEHICLE_TRIP)
    Call<TripsV2Response> getTrips(@Body TripsV2Request request);
    /**
     * get trips by day
     *
     * @param request
     * @return
     */
    @POST(RetrofitEndPointsV2.GET_TRIPSBYDAY)
    Call<TripByDayResponse>gettripsbyday(@Body tripsbydayRequest request);
    /**
     * get trips by hour
     *
     * @param request
     * @return
     */
    @POST(RetrofitEndPointsV2.GET_TRIPSBYTIME)
    Call<tripsbyTimeResponse>gettripsbytime(@Body tripsbyTimeRequest request);
}
