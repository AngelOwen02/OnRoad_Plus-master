package com.pnla.onroadplus.z_version2.activities.online.interfaces;

import com.pnla.onroadplus.z_version2.activities.online.models.CloseSessionV2Request;
import com.pnla.onroadplus.z_version2.activities.online.models.CloseSessionV2Response;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2Request;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2Response;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ActivityOnLineServices {
    /**
     * Close session
     *
     * @param request
     * @return
     */
    @POST(RetrofitEndPointsV2.CLOSE_SESSION)
    Call<CloseSessionV2Response> closeSessionService(@Body CloseSessionV2Request request);

    /**
     * Get groups
     *
     * @param
     * @return
     */
    @POST(RetrofitEndPointsV2.GET_VEHICLE_GROUP_LIST)
    Call<GroupV2Response> getGroups(@Body GroupV2Request request);

}
