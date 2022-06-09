package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.util;



import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.arrayasignments.asigmentResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.arrayasignments.asignmentRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.asignment.asignment;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.asignment.asignmentResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.checkAsigment.requestAsigments;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.checkAsigment.responseAsigment;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.drivers.requestDrivers;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.drivers.responsDrivers;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.newUpdate.newUpdateAsigments;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.newUpdate.newUpdateResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.vehicles.requestUnits;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.vehicles.responseUnits;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface    asignmentService {
    @POST(RetrofitEndPointsV2.GET_ASIGNMENTS)/** pinta las zonas en el mapa*/
    Call<responseAsigment> getZonesAsignment(@Body requestAsigments request);
    @POST(RetrofitEndPointsV2.GET_ASIGNMENTSNEW)
    Call<asigmentResponse> getAsignments(@Body asignmentRequest request);
    @POST(RetrofitEndPointsV2.GET_UNITS)
    Call<responseUnits> getUnisCatalog(@Body requestUnits request);

    @POST(RetrofitEndPointsV2.GET_DRIVERS)
    Call<responsDrivers> getDriversCatalog(@Body requestDrivers request);

    @POST(RetrofitEndPointsV2.POST_ASIGNMENTS)
    Call<asignmentResponse> postAsignments(@Body asignment resquest);

    @POST(RetrofitEndPointsV2.POST_ASIGNMENTSNEW)
    Call<newUpdateResponse> postAsignmentsNew(@Body newUpdateAsigments request);
}
