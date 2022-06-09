package com.pnla.onroadplus.z_version2.fragments.contactV2.interfaces;

import com.pnla.onroadplus.z_version2.fragments.contactV2.models.ContactV2Request;
import com.pnla.onroadplus.z_version2.fragments.contactV2.models.ContactV2Response;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ContactV2Services {

    @POST(RetrofitEndPointsV2.SEND_EMAIL)
    Call<ContactV2Response> sendEmail(@Body ContactV2Request request);

}
