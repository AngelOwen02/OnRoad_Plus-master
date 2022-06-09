package com.pnla.onroadplus.z_version2.fragments.loginV2.interfaces;


import com.pnla.onroadplus.z_version2.MenuFragments.Login.model.responseAuditTrail;
import com.pnla.onroadplus.z_version2.MenuFragments.Login.model.setAuditTrail;
import com.pnla.onroadplus.z_version2.fragments.loginV2.models.LoginRequestV2;
import com.pnla.onroadplus.z_version2.fragments.loginV2.models.LoginResponseV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginServicesV2 {

    @POST(RetrofitEndPointsV2.LOGIN)
    Call<LoginResponseV2> login(@Body LoginRequestV2 loginRequest);
    @POST(RetrofitEndPointsV2.POST_AUDIT_TRAIL)
    Call<responseAuditTrail> auditTrail(@Body setAuditTrail auditrequest);
}
