package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.utils;

import com.pnla.onroadplus.z_version2.MenuFragments.Login.model.responseAuditTrail;
import com.pnla.onroadplus.z_version2.MenuFragments.Login.model.setAuditTrail;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.drivers.requestDrivers;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.drivers.responsDrivers;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.vehicles.requestUnits;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.vehicles.responseUnits;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model.asigmentRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model.asignmentResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model.requestUpdate;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDriversAsigmentsNewFlow.Model.responseUpdate;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface asignmentService {
    @POST(RetrofitEndPointsV2.GET_ASIGNMENTSNEW)
    Call<asignmentResponse> getAsignments(@Body asigmentRequest request);

    @POST(RetrofitEndPointsV2.GET_UNITS)
    Call<responseUnits> getUnisCatalog(@Body requestUnits request);
    @POST(RetrofitEndPointsV2.GET_DRIVERS)
    Call<responsDrivers> getDriversCatalog(@Body requestDrivers request);
//
    @POST(RetrofitEndPointsV2.POST_ASIGNMENTSNEW)
    Call<responseUpdate> postAsignments(@Body requestUpdate request);
    @POST(RetrofitEndPointsV2.POST_AUDIT_TRAIL)
    Call<responseAuditTrail> auditTrail(@Body setAuditTrail auditrequest);

}
