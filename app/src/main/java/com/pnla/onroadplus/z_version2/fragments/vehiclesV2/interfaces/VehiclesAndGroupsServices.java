package com.pnla.onroadplus.z_version2.fragments.vehiclesV2.interfaces;

import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2Request;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2Response;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2Request;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2Response;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface VehiclesAndGroupsServices {


    /**
     * get vehicles
     *
     * @param request
     * @return
     */
    @POST(RetrofitEndPointsV2.GET_FULL_VEHICLES)
    Call<VehicleV2Response> getFullVehicles(@Body VehicleV2Request request);

    /**
     * Get groups
     *
     * @param
     * @return
     */
    @POST(RetrofitEndPointsV2.GET_VEHICLE_GROUP_LIST)
    Call<GroupV2Response> getGroups(@Body GroupV2Request request);


}
