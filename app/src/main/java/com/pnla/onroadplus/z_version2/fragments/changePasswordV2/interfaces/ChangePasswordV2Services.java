package com.pnla.onroadplus.z_version2.fragments.changePasswordV2.interfaces;

import com.pnla.onroadplus.z_version2.fragments.changePasswordV2.models.ChangePasswordV2Request;
import com.pnla.onroadplus.z_version2.fragments.changePasswordV2.models.ChangePasswordV2Response;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ChangePasswordV2Services {

    /**
     * Change password
     *
     * @param request
     * @return
     */
    @POST(RetrofitEndPointsV2.CHANGE_PASSWORD)
    Call<ChangePasswordV2Response> changePasswordService(@Body ChangePasswordV2Request request);


}
