package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.model;

import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.GroupvehicleInsideRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.GroupvehicleInsideResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SupportUnitRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SupportUnitResponse;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SupportService {
    @POST(RetrofitEndPointsV2.GET_VEHICLES_GROUP_LIST_VEHICLES)
    Call<GroupvehicleInsideResponse> getVehiclesInGroups(@Body GroupvehicleInsideRequest request);
    //Call<SupportUnitResponse> getVehiclesInGroups(@Body SupportUnitRequest request);
    //@POST(RetrofitEndPointsV2.GET_SUPPORTROUTES)
    //Call<SupportUnitResponse> getFullSupportUnits(@Body SupportUnitRequest request);
}
