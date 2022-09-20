package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.util;

import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.model.*;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceDialogs {
    @POST(RetrofitEndPointsV2.GET_UNIT)
    Call<DialogsResponse> getVehiclesDialog(@Body DialogsRequest request);
}
