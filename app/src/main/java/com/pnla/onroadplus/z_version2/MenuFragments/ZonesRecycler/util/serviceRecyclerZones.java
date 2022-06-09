package com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.util;

import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.pointsRequest1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.pointsResponse1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.visitedRequest1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.visitedResponse1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.zoneResponse1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.zonesRequest1;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface serviceRecyclerZones {
    @POST(RetrofitEndPointsV2.GET_ZONES)/** pinta las zonas en el mapa*/
    Call<zoneResponse1> getZones(@Body zonesRequest1 request);

    @POST(RetrofitEndPointsV2.GET_ZONEPOINTS)/** pinta los puntos de cada zona en el mapa*/
    Call<pointsResponse1> getZonepoints(@Body pointsRequest1 request);

    @POST(RetrofitEndPointsV2.GET_VISITINGPOITNS)/**muestra el porcentaje de puntos de cada zona en la vista del adapter del recyclerview*/
    Call<visitedResponse1> getPointsVisited(@Body visitedRequest1 request);
}
