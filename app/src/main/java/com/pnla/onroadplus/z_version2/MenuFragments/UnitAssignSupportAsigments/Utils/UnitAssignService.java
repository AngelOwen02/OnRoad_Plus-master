package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.Utils;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.Models.UnitAssignRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.Models.UnitAssignResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SingleSupportUnitRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SingleSupportUnitResponse;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UnitAssignService {

    @POST(RetrofitEndPointsV2.SET_ASSIGNSUPPORT)
    Call<UnitAssignResponse> setUnitAssignSupport(@Body UnitAssignRequest request);
}
