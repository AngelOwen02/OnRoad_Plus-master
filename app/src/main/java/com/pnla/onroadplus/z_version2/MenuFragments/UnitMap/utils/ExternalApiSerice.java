package com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.utils;

import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.RequestAPIMAP;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.ResponseAPIMAP;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ExternalApiSerice {



    @POST(RetrofitEndPointsV2.URL_MAP_API_ROUTE)
    Call<ResponseAPIMAP> closerPointsAPI(@Body RequestAPIMAP request);
}
