package com.pnla.onroadplus.z_version2.MenuFragments.Tracking;

import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TrackingServices {

    @POST(RetrofitEndPointsV2.GET_FULL_VEHICLES)
    Call<TrackingResponse> getFullVehicles(@Body TrackingRequest request);

    // @POST(RetrofitEndPointsV2.GET_VEHICLE_GROUP_LIST)
    // Call<GroupV2Response> getGroups(@Body GroupV2Request request);
}
