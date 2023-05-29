package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.util;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.RequestAPIMAP;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.ResponseAPIMAP;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.requestVehicleDescV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.responseVehicleDescV3;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface serviceVehicleDescription {

    @POST(RetrofitEndPointsV2.GET_VEHICLE_DESCRIPTION)
    Call<responseVehicleDescV3> getVehicleDescription(@Body requestVehicleDescV3 request);

}
