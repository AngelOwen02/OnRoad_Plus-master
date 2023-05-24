package com.pnla.onroadplus.z_version2.MenuFragments.Login.util;

import com.pnla.onroadplus.z_version2.MenuFragments.Login.model.loginV3.origin.requestOrigin;
import com.pnla.onroadplus.z_version2.MenuFragments.Login.model.loginV3.origin.responseOrigin;
import com.pnla.onroadplus.z_version2.fragments.loginV2.models.LoginRequestV2;
import com.pnla.onroadplus.z_version2.fragments.loginV2.models.LoginResponseV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginServiceV3 {
    @POST(RetrofitEndPointsV2.LOGINV3)
    Call<responseOrigin> login(@Body requestOrigin requestOrigin);
}
