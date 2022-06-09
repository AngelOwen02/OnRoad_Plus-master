package com.pnla.onroadplus.z_version2.fragments.restorePasswordV2.interfaces;

import com.pnla.onroadplus.z_version2.fragments.restorePasswordV2.models.RestorePasswordV2Request;
import com.pnla.onroadplus.z_version2.fragments.restorePasswordV2.models.RestorePasswordV2Response;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RestorePasswordV2Services {

    /**
     * Rstore password
     *
     * @param request
     * @return
     */
    @POST(RetrofitEndPointsV2.RESTORE_PASSWORD)
    Call<RestorePasswordV2Response> restorePasswordService(@Body RestorePasswordV2Request request);


}
