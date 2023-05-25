package com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.util;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitsLegacy.requestUnitsLegacy;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitsLegacy.responseUnitsLegacy;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface serviceUnitsLegacy {
    @POST(RetrofitEndPointsV2.GET_FULL_VEHICLES)
    Call<responseUnitsLegacy> getFullVehicles(@Body requestUnitsLegacy request);
}
