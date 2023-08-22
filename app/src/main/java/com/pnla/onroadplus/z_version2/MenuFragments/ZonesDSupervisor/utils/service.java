package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.utils;

import com.pnla.onroadplus.z_version2.MenuFragments.Login.model.responseAuditTrail;
import com.pnla.onroadplus.z_version2.MenuFragments.Login.model.setAuditTrail;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.model.requestDriversBypos;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.model.responseSetZones;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.model.setZone;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.drivers.requestDrivers;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.drivers.responsDrivers;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface service {
    @POST(RetrofitEndPointsV2.GET_DRIVERSBYPOSTION)
    Call<responsDrivers> getDriversCatalog(@Body requestDriversBypos request);
    @POST(RetrofitEndPointsV2.POST_SETZONES)
    Call<responseSetZones> setZonez(@Body setZone request);

    @POST(RetrofitEndPointsV2.POST_AUDIT_TRAIL)
    Call<responseAuditTrail> auditTrail(@Body setAuditTrail auditrequest);
}
