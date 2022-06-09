package com.pnla.onroadplus.z_version2.MenuFragments.geocercas.util;

import com.pnla.onroadplus.z_version2.MenuFragments.geocercas.model.geoCercasModel;
import com.pnla.onroadplus.z_version2.MenuFragments.geocercas.model.geoCercasRequest;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface geCercasService {
    @POST(RetrofitEndPointsV2.GET_GEOCERCAS)
    Call<geoCercasModel> geoCercas(@Body geoCercasRequest request);

}
