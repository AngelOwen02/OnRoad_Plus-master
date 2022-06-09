package com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.mapTracking.models;

import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface laspositionmapservice {


    @POST(RetrofitEndPointsV2.GET_LASPOSITIOMITEM)
    Call<lastPositionItemResponse> getvehicles(@Body lasPositionItemRequest request);
}
