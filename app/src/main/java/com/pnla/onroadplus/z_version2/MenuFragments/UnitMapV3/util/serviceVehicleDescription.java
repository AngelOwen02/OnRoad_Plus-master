package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.util;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.RequestAPIMAP;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.ResponseAPIMAP;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.tripsbyTimeRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.tripsbyTimeResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.TripsByTimeV3.tripsbyTimeResponse3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.requestVehicleDescV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.responseVehicleDescV3;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripsV2Request;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripsV2Response;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface serviceVehicleDescription {

    @POST(RetrofitEndPointsV2.GET_VEHICLE_DESCRIPTION)
    Call<responseVehicleDescV3> getVehicleDescription(@Body requestVehicleDescV3 request);
    @POST(RetrofitEndPointsV2.GET_VEHICLE_TRIP)
    Call<TripsV2Response> getTrips(@Body TripsV2Request request);
    @POST(RetrofitEndPointsV2.GET_TRIPSBYTIME)
    Call<tripsbyTimeResponse>gettripsbytime(@Body tripsbyTimeRequest request);
    @POST(RetrofitEndPointsV2.GET_TRIPSBYTIME3)
    Call<tripsbyTimeResponse3>gettripsbytime3(@Body tripsbyTimeRequest request);
}
