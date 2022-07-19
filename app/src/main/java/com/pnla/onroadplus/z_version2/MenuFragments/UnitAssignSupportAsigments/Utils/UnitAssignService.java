package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.Utils;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.Models.DeleteUnitAssignRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.Models.DeleteUnitAssignResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.Models.UnitAssignRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.Models.UnitAssignResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SingleSupportUnitRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SingleSupportUnitResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SupportUnitRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SupportUnitResponse;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UnitAssignService {

    @POST(RetrofitEndPointsV2.GET_SUPPORTROUTES)
    Call<SupportUnitResponse> getFullVehiclesSupport(@Body SupportUnitRequest request);

    @POST(RetrofitEndPointsV2.GET_SUPPORTVEHICLES)
    Call<SingleSupportUnitResponse> getFullSingleVehiclesSupport(@Body SingleSupportUnitRequest request);

    @POST(RetrofitEndPointsV2.SET_ASSIGNSUPPORT)
    Call<UnitAssignResponse> setUnitAssignSupport(@Body UnitAssignRequest request);

    @POST(RetrofitEndPointsV2.SET_CANCELSUPPORT)
    Call<DeleteUnitAssignResponse> deleteAssignSupport(@Body DeleteUnitAssignRequest request);
}
