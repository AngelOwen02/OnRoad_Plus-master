package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.utils;

import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.model.responseSetZones;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.model.setZone;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.drivers.requestDrivers;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.drivers.responsDrivers;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface service {
    @POST(RetrofitEndPointsV2.GET_DRIVERS)
    Call<responsDrivers> getDriversCatalog(@Body requestDrivers request);
    @POST(RetrofitEndPointsV2. POST_SETZONES)
    Call<responseSetZones> setZonez(@Body setZone request);
}
