package com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.util;

import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.UnitRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.UnitResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.requestUnitsmodelV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.responseUnitsV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitsLegacy.requestUnitsLegacy;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitsLegacy.responseUnitsLegacy;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface serviceUnitsV3 {
     @POST(RetrofitEndPointsV2.UNITSV3)
     Call<responseUnitsV3> getUnitsV3(@Body requestUnitsmodelV3 request);


}
