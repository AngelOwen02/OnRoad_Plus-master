package com.pnla.onroadplus.z_version2.Containers.ModelVersion;

import com.pnla.onroadplus.z_version2.retrofit.RetrofitEndPointsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface VersionService {

    @POST(RetrofitEndPointsV2.GET_VERSION)
    Call<VersionResponse> getVersion(@Body VersionRequest versionRequest);
}
