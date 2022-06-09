package com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.utils;

import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.model.unitsRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.model.unitsResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.model.vehicleGroupRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.model.vehicleGroupResponse;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface mapService {

    @POST(RetrofitEndPointsV2.GET_FULL_VEHICLES)
    Call<unitsResponse> getfullNogeoVehicles(@Body unitsRequest request);


    @POST(RetrofitEndPointsV2.GET_VEHICLE_GROUP_LIST)
    Call<vehicleGroupResponse> getGroupsR(@Body vehicleGroupRequest requestGroups);
}
