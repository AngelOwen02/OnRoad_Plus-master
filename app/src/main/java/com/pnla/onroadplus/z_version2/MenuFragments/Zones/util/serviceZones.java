package com.pnla.onroadplus.z_version2.MenuFragments.Zones.util;

import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.pointsRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.pointsResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.visitedRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.visitedResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.zoneResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.zonesRequest;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface serviceZones {



    @POST(RetrofitEndPointsV2.GET_ZONES)/** pinta las zonas en el mapa*/
    Call<zoneResponse> getZones(@Body zonesRequest request);

    @POST(RetrofitEndPointsV2.GET_ZONEPOINTS)/** pinta los puntos de cada zona en el mapa*/
    Call<pointsResponse> getZonepoints(@Body pointsRequest request);

    @POST(RetrofitEndPointsV2.GET_VISITINGPOITNS)/**muestra el porcentaje de puntos de cada zona en la vista del adapter del recyclerview*/
    Call<visitedResponse> getPointsVisited(@Body visitedRequest request);
}
