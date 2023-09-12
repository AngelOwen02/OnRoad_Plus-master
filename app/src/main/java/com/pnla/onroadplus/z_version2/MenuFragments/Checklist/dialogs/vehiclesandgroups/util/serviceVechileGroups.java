package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.util;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.model.responseGroups;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.GroupRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.GroupResponse;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface serviceVechileGroups {
    @POST(RetrofitEndPointsV2.GET_VEHICLE_GROUP_LIST)
    Call<responseGroups> getGroups(@Body GroupRequest request);
}
